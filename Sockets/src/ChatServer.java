import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatServer {

	ArrayList clientOutputStreams;
	
	public class ClientHandler implements Runnable{
		BufferedReader reader;
		Socket sock;
		
		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSocket;
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String message;
			try {
				while((message=reader.readLine())!=null) {
					System.out.println("Read "+message);
					tellEveryone(message);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
				
		
	}
	
	public void tellEveryone(String message) {
		Iterator it = clientOutputStreams.iterator();
		while(it.hasNext()) {
			try {
				PrintWriter wr = (PrintWriter)it.next();
				wr.println(message);
				wr.flush();
							
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}	
	}
	
	
	public void go() {
	
		clientOutputStreams = new ArrayList<>();
		try {
			ServerSocket serverSock = new ServerSocket(5000);
			while(true) {
				Socket clientSocket = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				clientOutputStreams.add(writer);
				
				Thread t = new Thread(new ClientHandler(clientSocket));
				t.start();
				System.out.println("Got A Connection !");
				
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void main(String[] args) {
		new ChatServer().go();
	}
}
