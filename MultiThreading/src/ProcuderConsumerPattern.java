import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProcuderConsumerPattern {
	public static void main(String[] args) {
		BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>();
		
		@SuppressWarnings("unused")
		Thread prodThread = new Thread(new Producer(sharedQueue));
		@SuppressWarnings("unused")
		Thread consumerThread = new Thread(new Consumer(sharedQueue));
		
		prodThread.start();
		consumerThread.start();
		
		
		
	}
	
}

	class Producer implements Runnable{
		private final BlockingQueue sharedQueue;

		public Producer(BlockingQueue sharedQueue) {
			// TODO Auto-generated constructor stub
			this.sharedQueue = sharedQueue;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			for(int i=0;i<10;i++) {
				try {
					System.out.println("produced = "+i);
					sharedQueue.put(i);
					
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	class Consumer implements Runnable{
		
		private final BlockingQueue sharedQueue;

		public Consumer(BlockingQueue sharedQueue) {
			// TODO Auto-generated constructor stub
			this.sharedQueue = sharedQueue;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(true) {
				
				try {
					System.out.println("Consumed = "+ sharedQueue.take());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				
			}
			
		}
		
	}	
	
	
	

