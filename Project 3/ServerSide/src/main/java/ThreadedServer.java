import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.function.Consumer;

public class ThreadedServer {

	int count = 1;
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	Categories c = new Categories();
	int numGuesses = 6;
	boolean finalTorF = false;
	int ctr = 0;
	boolean flag;

	ThreadedServer(Consumer<Serializable> call, String text){

		callback = call;
		server = new TheServer();
		server.start();
	}

	public class TheServer extends Thread{

		public void run() {

			try(ServerSocket mysocket = new ServerSocket(5555);){
				System.out.println("Server is waiting for a client!");


				while(true) {

					ClientThread c = new ClientThread(mysocket.accept(), count);
					callback.accept("client has connected to server: " + "client #" + count);
					clients.add(c);
					c.start();

					count++;

				}
			}  // end of try
			catch(Exception e) {
				callback.accept("Server socket did not launch");
			}
		}  // end of while
	}


	class ClientThread extends Thread{


		Socket connection;
		int count;
		ObjectInputStream in;
		ObjectOutputStream out;
		String Category;

		ClientThread(Socket s, int count){
			this.connection = s;
			this.count = count;
		}

		public void updateClients(String message) {
			for (ClientThread t : clients) {
				try {
					t.out.writeObject(message);
				} catch (Exception e) {
				}
			}
		}

		public void run(){

			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}

			updateClients("new client on server: client #" + count);

			while(true) {
				try {
					String data = in.readObject().toString();
					if (data.equals("Animals") || data.equals("U.S States") || data.equals("Food Cuisines")) {
						Category = data;
						callback.accept("client: " + count + " chose " + data + " category");
						if (Category.equals("Animals")) {
							finalTorF = false;
//							ctr = 0;
							c.init1();
						} else if (Category.equals("U.S States")) {
							finalTorF = false;
//							ctr = 0;
							c.init2();
						} else {
							finalTorF = false;
//							ctr = 0;
							c.init3();
						}
					} else {
						callback.accept("client: " + count + " sent: " + data);
						char a = data.charAt(0);
//						updateClients("client #" + count + " said: " + data);
						flag = false;
						Boolean wh = this.checkIfPresent(a, Category);
						System.out.println(wh);
						out.writeObject(finalTorF);
					}

				}
				catch(Exception e) {
					callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					updateClients("Client #" + count + " has left the server!");
					clients.remove(this);
					break;
				}
			}
		}  // end of run

		public boolean checkIfPresent(char s, String which) {
			flag = false;
			String str;
			switch (which) {
				case "Animals":
					str = c.getCatOneWord();
					break;
				case "U.S States":
					str = c.getCatTwoWord();
					break;
				case "Food Cuisines":
					str = c.getCatThreeWord();
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + which);
			}
			System.out.println(str);
			for (int i = 0; i < str.length(); i++) {
				if (s == str.charAt(i)) {
					flag = true;
					ctr++;
					break;
				}
			}
			if (!flag) {
				numGuesses--;
			}
			if (numGuesses == 0 || ctr == str.length()) {
				switch (which) {
					case "Animals":
						c.removeCatOneWord();
						break;
					case "U.S States":
						c.removeCatTwoWord();
						break;
					case "Food Cuisines":
						c.removeCatThreeWord();
						break;
				}
//				ctr = 0;
			}
			if (ctr == str.length()) {
				finalTorF = true;
			}
			System.out.println("This is ctr " + ctr);
			return flag;
		}

	} // end of client thread
}