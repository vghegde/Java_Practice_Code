import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class chatBot {
	
	public void go() {
		try {
			
			Socket clientSocket = new Socket("127.0.0.1", 4242);
			
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			
			String advice = bufReader.readLine();
			bufReader.close();
			
			System.out.println("Advice is : "+advice);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new chatBot().go();
	}
	
	

}
