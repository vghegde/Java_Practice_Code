
public class HeapSort {
	
	
	public static void main(String[] args) {
		int [] arr= {3,2,5,42,32,1,6,6};
		
		HeapSort hs = new HeapSort();
		hs.sort(arr);
		
		hs.printarray(arr);
		
		
	}
	
	void printarray(int [] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	
	void sort(int [] arr){
		int n = arr.length;
		//build Heap, n/2-1 because that points to last non leaf node.
		for (int i = n/2 - 1; i>=0; i--) {
			heapify(arr,n,i);
		}
		
		// Extract element from heap
		for (int i = n-1; i >=0 ; i--) {
			
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i]=temp;
			
			heapify(arr,i,0);
		}
		
		
		
	}

	
	
	void heapify(int []arr, int n,int i) {
		int largest =i;
		
		int l = i*2+1;
		int r = l+1;
		
		if(l<n && arr[l]>arr[largest])
			largest = l;
		if(r<n && arr[r]>arr[largest])
			largest = r;
		
		if(largest!=i) {
			int temp = arr[i];
			arr[i]=arr[largest];
			arr[largest] = temp;
			heapify(arr,n,largest);
		}
		
	}
	
	
	
	
}
