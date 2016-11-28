import com.training.senla.DataPacket;
import com.training.senla.invoker.MethodInvoker;

import java.io.*;
import java.net.Socket;

class ServerThread extends Thread {
    private Socket client;

    public ServerThread(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        ObjectInputStream in;
        ObjectOutputStream out;

        try {
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());

            while (true) {
                DataPacket request = (DataPacket) in.readObject();
                if(request != null) {
                    out.writeObject(MethodInvoker.invokeMethod(request.getHeader(), request.getBody()));
                    out.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}