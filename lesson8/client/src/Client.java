import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by dmitry on 24.11.16.
 */
public class Client {

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to Client side");

        Socket fromServer = null;

        if (args.length == 0) {
            System.out.println("use: client hostname");
            System.exit(-1);
        }
        System.out.println("Connecting to... " + args[0]);

        fromServer = new Socket(args[0], 3000);
        BufferedReader in = new
                BufferedReader(new
                InputStreamReader(fromServer.getInputStream()));
        PrintWriter out = new
                PrintWriter(fromServer.getOutputStream(), true);
        BufferedReader inu = new
                BufferedReader(new InputStreamReader(System.in));

        String fuser, fserver;

        while ((fuser = inu.readLine()) != null) {
            out.println(fuser);
            fserver = in.readLine();
            System.out.println(fserver);
            if (fuser.equalsIgnoreCase("close")) break;
            if (fuser.equalsIgnoreCase("exit")) break;
        }

        out.close();
        in.close();
        inu.close();
        fromServer.close();
    }
}
