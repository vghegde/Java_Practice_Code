import java.util.LinkedList;

public class ProducerConsumer {

	LinkedList<Integer> list = new LinkedList<>();
	int capacity = 2;

	public void produce() throws InterruptedException{
		int value=0;
		synchronized (this)
		{
			while(value<10) {

				while(list.size() == capacity) 
					wait();

				System.out.println("producer produced = "+ value);

				list.add(value++);

				notify();

				Thread.sleep(1000);

			}
		}
	}

	public void consume() throws InterruptedException{
		synchronized (this)
		{
			while(true) {

				while(list.isEmpty()) 
					wait();				

				System.out.println("Consuming "+ list.removeFirst());

				notify();

				Thread.sleep(1000);

			}

		}
	}

}
