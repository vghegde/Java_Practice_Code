
public class MainDriver_PC {

	public static void main(String[] args) throws InterruptedException{

		ProducerConsumer PC = new ProducerConsumer();
	
			
		Thread t1 = new Thread(()->{
			try {
				PC.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
//				new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					
//					PC.produce();
//					
//				}catch(Exception ex){
//					ex.printStackTrace();
//				}
//			}
//		}
				);
		
		Thread t2 = new Thread( ()->{
			try {
				PC.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
//				new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					
//					PC.consume();
//					
//				}catch(Exception ex){
//					ex.printStackTrace();
//				}
//			}
//		}
//				
				);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		

		



	}
}
