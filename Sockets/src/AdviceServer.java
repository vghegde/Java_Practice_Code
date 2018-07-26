import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public class AdviceServer {

	//String [] AdviceList = {"Vinay", "Hegde", "Kavya", "Bhat", "Sirsi"};
	
	public void go() {
		
		try {
			
			ServerSocket serverSock = new ServerSocket(4242);
			System.out.println("Starting Server");
			while(true) {
				Socket sock = serverSock.accept();
				
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String advice = getAdvice(); 
				writer.println(advice);
				writer.close();
				System.out.println(advice);
							
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	private String getAdvice() {
	//	int random = (int)(Math.random() * AdviceList.length);
	//	return AdviceList[random];
		
		Connection connect =null;
		Statement st = null;
		PreparedStatement pst = null;
		ResultSet rset = null;
		ArrayList<String> results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb" ,"root","root");
			//st = connect.createStatement();
			//rset = st.executeQuery("slect * from Advices;");
			
			System.out.println("Querying DB");
			pst = connect.prepareStatement("select * from Advices;");
			rset = pst.executeQuery();
			 results = new ArrayList<>();
			
			 System.out.println("Checking resultset");
			while(rset.next()) {
				String temp = rset.getString("advice_string");
				System.out.println("returning "+temp);
				results.add(temp);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(results!=null) {
			return results.get((int)(Math.random() * results.size()));
		}else
			return "Empty Results";
		
	}
	
	
	public static void main(String[] args) {
		AdviceServer server = new AdviceServer();
		server.go();
	}
}
