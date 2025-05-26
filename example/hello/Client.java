package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");

            String response = stub.sayHello();
            System.out.println("Response from sayHello: " + response);

            int somaResult = stub.soma(100, 200);
            System.out.println("Response from soma: " + somaResult);

            double lnResult = stub.ln(10);
            System.out.println("Response from ln(10): " + lnResult);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
