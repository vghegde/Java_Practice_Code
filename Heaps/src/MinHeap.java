import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>>{

	private ArrayList<T> items;

	public MinHeap() {
		items = new ArrayList<T>();
	}

	void insert(T item) {
		items.add(item);
		shiftUP();
	}

	public T delete() throws NoSuchElementException {
		T retVal=null;
		if(items.size()==0) {
			throw new NoSuchElementException();
		}

		if(items.size()==1) {
			return items.remove(0);

		}

		retVal = items.get(0);
		items.set(0, items.remove(items.size()-1));
		shiftDown();

		return retVal;
	}


	private void shiftUP() {
		int newItemIndex = items.size()-1;

		while( newItemIndex > 0 ) {

			T item = items.get(newItemIndex);
			T parent = items.get(getParentIndex(newItemIndex));

			if(item.compareTo(parent) < 0) {
				items.set(newItemIndex, parent);
				items.set(getParentIndex(newItemIndex), item);
				newItemIndex = getParentIndex(newItemIndex);
			}else {
				break;
			}


		}

		System.out.println(items);
	}


	private void shiftDown() {
		int curItemIndex = 0;

		while(curItemIndex < items.size() && getleftChildIndex(curItemIndex) <= (items.size()-1) ) {

			T item = items.get(curItemIndex);
			T left = items.get(getleftChildIndex(curItemIndex));
			
			if(getRightChildIndex(curItemIndex)<=(items.size()-1)) {
				
				T right = items.get(getRightChildIndex(curItemIndex));
				
				if(left.compareTo(right) < 0) {
					if(left.compareTo(item) < 0) {
						items.set(curItemIndex, left);
						items.set(getleftChildIndex(curItemIndex), item);
						curItemIndex = getleftChildIndex(curItemIndex);
					}else {
						break;
					}
				}else {
					
					if(right.compareTo(item) < 0) {
						items.set(curItemIndex, right);
						items.set(getRightChildIndex(curItemIndex), item);
						curItemIndex = getRightChildIndex(curItemIndex);
					}else {
						break;
					}

				}

			} else {
				if(left.compareTo(item) < 0) {
					items.set(curItemIndex, left);
					items.set(getleftChildIndex(curItemIndex), item);
					curItemIndex = getleftChildIndex(curItemIndex);
				}else {
					break;
				}
			
			}
		}
		
		System.out.println(items);
	}

	private int getParentIndex(int curIndex) {
		return (int)((curIndex-1)/2);
	}

	private int getleftChildIndex(int curIndex) {
		return (2*curIndex)+1;
	}

	private int getRightChildIndex(int curIndex) {
		return (2*curIndex)+2;
	}



	public static void main(String[] args) {
		
		MinHeap<Integer> hp = new MinHeap<>();
		hp.insert(15);
		hp.insert(10);
		hp.insert(12);
		hp.insert(11);
		hp.insert(9);
		hp.insert(8);
		hp.insert(6);
		hp.insert(4);
		hp.insert(1);
		
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
		System.out.println("max elem = "+ hp.delete());
	
		
	}





}
