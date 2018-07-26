import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatClient {

	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	
	public static void main(String[] args) {
		ChatClient cc = new ChatClient();
		cc.go();
	}
	
	
	public void go() {
		
		JFrame frame = new JFrame("Simple Chat Client");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("Send");
		
		sendButton.addActionListener(new SendButtonListener());
		
		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		
		setupNetworking();
		
		Thread readerThread = new Thread (new IncomingReader());
		readerThread.start();
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400, 500);
		frame.setVisible(true);
	}
	
	
	
	private void setupNetworking() {
		try {
			sock = new Socket("127.0.0.1", 5000);
			InputStreamReader isr = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(isr);
			
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Neworking established !");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
	}
	
	
	public class SendButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				writer.println(outgoing.getText());
				writer.flush();
				
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
			
			
		}
		
	}
	
	
	public class IncomingReader implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String messages;
			
			try {
				while((messages = reader.readLine())!=null) {
					System.out.println("Read "+messages);
					incoming.append(messages+"\n");
								
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	
	
}
