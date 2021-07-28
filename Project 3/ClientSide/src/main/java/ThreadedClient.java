import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class ThreadedClient extends Thread {
    Socket socketClient;

    ObjectOutputStream out;
    ObjectInputStream in;
    String ip;


    private Consumer<Serializable> callback;

    ThreadedClient(Consumer<Serializable> call, String inputAdd){

        callback = call;
        ip = inputAdd;
    }

    public void run() {

        try {
            socketClient= new Socket("127.0.0.1",5555);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
        }
        catch(Exception e) {}

        while(true) {

            try {
                String message = in.readObject().toString();
                callback.accept(message);
            }
            catch(Exception e) {}
        }

    }

    public void send(String data) {

        try {
            out.writeObject(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
