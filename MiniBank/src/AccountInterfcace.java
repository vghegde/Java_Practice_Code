import java.util.HashMap;
import java.util.logging.Logger;

public interface AccountInterfcace {

	
	
	//Daily limit of withdrawal.
	public static final double DAILY_WITHDRAW_LIMIT = 10000.00;
	
	
	//creates an Account and returns account number.
	public int createAccount();

	//deposit the money 
	// accountNumber - account number in which money needs to be deposited.
	// money - amout of money to be deposited.
	public boolean depositMoney(int accountNumber, double money);
	
	//withdraw Money
	// accountNumber - account number in which money needs to be withdrawn.
	// money - amount of money to be withdrawn.
	public boolean withdrawMoney(int accountNumber, double money);

	//get balance of account with account number
	//accountNumber - Account of which balence needs to be displayed.
	public double getBalence(int accountNumber);	
	
	
	public static HashMap<Integer, AccountInterfcace> accountData = new HashMap<>();
	
    

	
}
