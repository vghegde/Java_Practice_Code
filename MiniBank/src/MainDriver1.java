
public class MainDriver1 {
	
	public static void main(String[] args) {
		
		AccountInterfcace acct = new AccountImpl();
		
		int accountNumber = acct.createAccount();
		acct.depositMoney(accountNumber, 20000.00);
		System.out.println("Current Balence = "+ acct.getBalence(accountNumber));
		acct.withdrawMoney(accountNumber, 1000.00);
		System.out.println("Current Balence = "+acct.getBalence(accountNumber));
		acct.withdrawMoney(accountNumber, 1.00);
		System.out.println("Current Balence = "+acct.getBalence(accountNumber));
		
System.out.println("Account 2");		
		
AccountInterfcace acct2= new AccountImpl();
		
		int accountNumber2 = acct2.createAccount();
		//accountNumber2 = 100;
		acct.depositMoney(accountNumber2, 20000.00);
		System.out.println("Current Balence = "+ acct2.getBalence(accountNumber2));
		acct.withdrawMoney(accountNumber2, 10000.00);
		System.out.println("Current Balence = "+acct2.getBalence(accountNumber2));
		acct.withdrawMoney(accountNumber2, 1.00);
		System.out.println("Current Balence = "+acct2.getBalence(accountNumber2));
		
		acct.withdrawMoney(accountNumber, 1.00);
		System.out.println("Current Balence = "+acct.getBalence(accountNumber));
		
		
	}

}
