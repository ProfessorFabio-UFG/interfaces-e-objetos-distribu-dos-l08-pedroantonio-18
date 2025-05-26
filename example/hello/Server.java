package example.hello;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {

    public Server() {}

    @Override
    public String sayHello() {
        return "Hello, world!";
    }

    @Override
    public int soma(int a, int b) {
        return a + b;
    }

    @Override
    public double ln(double x) throws RemoteException {
        if (x <= 0) {
            throw new IllegalArgumentException("x deve ser positivo e maior que 0");
        }

        int k = 0;
        while (x > 2) {
            x /= Math.E;
            k++;
        }
        while (x < 0.5) {
            x *= Math.E;
            k--;
        }

        double y = (x - 1) / (x + 1);
        double y2 = y * y;
        double term = y;
        double sum = 0.0;

        for (int n = 1; n <= 50; n += 2) {
            sum += term / n;
            term *= y2;
        }

        return 2 * sum + k;
    }

    public static void main(String[] args) {
        try {
            Server obj = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
