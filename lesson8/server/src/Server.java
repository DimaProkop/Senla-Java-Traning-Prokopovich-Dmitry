import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dmitry on 24.11.16.
 */
public class Server {
    public static void main(String[] args) {
        System.out.println("Welcome to Server side");
        BufferedReader in = null;
        PrintWriter out = null;

        ServerSocket servers = null;
        Socket fromClient = null;

        try {
            servers = new ServerSocket(3000);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromClient = servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

        try {
            in = new BufferedReader(new InputStreamReader(fromClient.getInputStream()));
            out = new PrintWriter(fromClient.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String input, output;

        System.out.println("Wait for messages");
        try {
            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) break;
                out.println("S ::: " + input);
                System.out.println(input);
            }

            out.close();
            in.close();
            fromClient.close();
            servers.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
