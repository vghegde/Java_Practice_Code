import java.time.Duration;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountImpl implements AccountInterfcace {
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
    
	
	// Account Number Counter
	private static int accountNumberCounter=1;
	//Account Number
	private int accountNumber ; 
	//Tracks LastWithdraw time, used to check daily withdraw limit.
	private LocalDateTime lastWithdrawTime=null;
	//Keeps cumulative sum of daily amount withdrawn.
	private double dailyLimitTracker;
	//String nameOfAccountholder;
	
	public LocalDateTime getLastWithdrawTime() {
		return lastWithdrawTime;
	}

	public void setLastWithdrawTime(LocalDateTime lastWithdrawTime) {
		this.lastWithdrawTime = lastWithdrawTime;
	}

	private double accountBalence;
	
	public AccountImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static int getAccountNumberCounter() {
		return accountNumberCounter;
	}

	public static void setAccountNumberCounter(int accountNumberCounter) {
		AccountImpl.accountNumberCounter = accountNumberCounter;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAccountBalence() {
		return accountBalence;
	}

	public void updateAccountBalence(double amount) {
		this.accountBalence = this.accountBalence+amount;
	}

	public AccountImpl(int accountNumber) {
		this.accountNumber = accountNumber;
		this.accountBalence=0.0;
	}

	
	//creates and account and returns account Number.
	@Override
	public int createAccount() {
		// TODO Auto-generated method stub
		
		LOGGER.setLevel(Level.INFO);
	    LOGGER.severe("Info Log - creating an Account");
	    LOGGER.warning("Info Log");
	    LOGGER.info("Info Log");
	    LOGGER.finest("Really not important");
		
		
		AccountInterfcace newAccount = new AccountImpl(accountNumberCounter++);
		accountData.put(((AccountImpl)newAccount).getAccountNumber() , newAccount);
				
		return ((AccountImpl)newAccount).getAccountNumber();
	}

	@Override
	public boolean depositMoney(int accountNumber,double money) {
		// TODO Auto-generated method stub
		
		if(accountNumber<1) {
			System.out.println("Account Number must be positive");
			return false;
		}
		
		if(money<0) {
			System.out.println("Cannot enter negetive Value");
			return false;
		}
		
		//Get Account from HashMap
		AccountInterfcace curAccount = accountData.remove(accountNumber);
		
		//validity check for existence of an account
		if(curAccount==null) {
			System.out.println("Account Does not exist");
			return false;
		}
		((AccountImpl)curAccount).updateAccountBalence(money);	
		accountData.put(accountNumber,curAccount);
		
		return true;
	}

	@Override
	public boolean withdrawMoney(int accountNumber, double money) {
		// TODO Auto-generated method stub
			
		if(accountNumber<1) {
			System.out.println("Account Number must be positive");
			return false;
		}
		
		if(money < 0) {
			System.out.println("Cannot Withdraw negetive amount");
			return false;
		}
		
		if(money > DAILY_WITHDRAW_LIMIT) {
			System.out.println("Cannot Withdraw more than Daily Limit - 10000.00");
			return false;
		}
				
					
		AccountInterfcace curAccount = accountData.get(accountNumber);
		
		//validity check for existence of an account
		if(curAccount==null) {
			System.out.println("Account Does not exist");
			return false;
		}
		
		if(money > curAccount.getBalence(accountNumber)) {
			System.out.println("Low Balance, Cannot withdraw amount = "+money);
			return false;
		}
		
		//update last withdrawTime.
		if(((AccountImpl)curAccount).getLastWithdrawTime()==null) {
			((AccountImpl)curAccount).setLastWithdrawTime(LocalDateTime.now());
		}
		
		// checking when did we last withdrew the money
		Duration duration = Duration.between(LocalDateTime.now(), ((AccountImpl)curAccount).getLastWithdrawTime() );
		long diff = Math.abs(duration.toDays());
		
		if(diff<1) {
			((AccountImpl)curAccount).dailyLimitTracker += money;
			if(((AccountImpl)curAccount).dailyLimitTracker > DAILY_WITHDRAW_LIMIT) {
				System.out.println("Cannot Withdraw Money. You have reached your Daily limit of withdrawal : 10000.00");
				return false;
			}
		}else {
			((AccountImpl)curAccount).dailyLimitTracker = 0.0;
			((AccountImpl)curAccount).dailyLimitTracker += money;
		}
		
		//updating account balance
		((AccountImpl)curAccount).updateAccountBalence((0.0 - money));
		((AccountImpl)curAccount).setLastWithdrawTime(LocalDateTime.now());	
		
		//update accountdata with latest balance.
		accountData.put(accountNumber,curAccount);
		
		return true;
	}

	// Display Current Balance
	@Override
	public double getBalence(int accountNumber) {
		
		//validity check for account number
		if(accountNumber<1) {
			System.out.println("Account Number must be positive");
			return Double.MIN_VALUE;
		}
		
		AccountInterfcace curAccount = accountData.get(accountNumber);
		
		//validity check for existence of an account
		if(curAccount==null) {
			System.out.println("Account Does not exist");
			return Double.MIN_VALUE;
		}
		return ((AccountImpl)curAccount).getAccountBalence();
	}
	
		
	
}
