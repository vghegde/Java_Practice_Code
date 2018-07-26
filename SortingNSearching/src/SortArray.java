import java.time.format.TextStyle;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 
 * 
 * 
 * 
 */
public class SortArray {
	public static final int SIZE=10000;
	public int [] testArray = new int[SortArray.SIZE];
	
	public SortArray(int [] test) {
		this.testArray = test;
	}
	
	public static int[] Sort0s1s2s(int[] arr) {

		int low=0,mid=0,high = arr.length-1;

		while(mid<high) {

			switch(arr[mid]) {

			case 0: {
				int temp = arr[mid];
				arr[mid]=arr[low];
				arr[low]=temp;
				low++;
				mid++;
				break;
			}

			case 1:{

				mid++;
				break;
			}
			case 2 :{
				int temp = arr[mid];
				arr[mid]=arr[high];
				arr[high]=temp;
				high--;
				break;			

			}

			default: System.out.println("In default");
			break;


			}


		}

		return arr;
	}

	public static int [] insertionSort_v3(int [] inpArray) {
		//sort by inserting current element at sorted position by using swaps. 


		for(int key=1;key<inpArray.length;key++) {

			int j = key-1;
			boolean isDone = false;
			
			while(j>=0 && !isDone) {
				
				
				if(inpArray[j] > inpArray[j+1]) {
			
					int temp = inpArray[j];
					inpArray[j] = inpArray[j+1];
					inpArray[j+1] = temp;
				
				}else {
					isDone = true;
				}
				
				j--;
			}


		}

		return inpArray;

	}



	public static int [] insertionSort_v2(int [] inpArray) {
		//sort by inserting current element at sorted position by using swaps. 


		for(int key=1;key<inpArray.length;key++) {
			for(int j=0;j<key;j++){

				if(inpArray[key]<inpArray[j]){

					int temp = inpArray[key];
					inpArray[key] = inpArray[j];
					inpArray[j] = temp;

				}			
			}
		}

		return inpArray;

	}	


	public static int [] insertionSort_v1(int [] inpArray) {
		//sort by inserting current element at sorted position by using swaps. 


		for(int k = 1;k<inpArray.length;k++){

			int key = inpArray[k];
			int j = k-1;

			while(j>=0 && inpArray[j]>key){

				inpArray[j+1]=inpArray[j];

				j--;
			}

			inpArray[j+1] = key;

		}

		return inpArray;

	}
	
	
	private  void merge( int low, int mid, int high ){
		
		int n1 = mid-low+1;
		int n2 = high - mid;
		
		int L[] = new int[n1];
		int R[]  = new int [n2];
		
		for(int i=0;i<n1;++i) {
			L[i]  = testArray[low+i];
		}
		
		for(int j=0;j<n2;++j) {
			R[j] = testArray[mid+j+1];
		}
		
		int l = 0;
		int r = 0;
		int k = low;
		
		while(l<n1 && r<n2) {
			
			if(L[l] <= R[r]) {
				testArray[k] = L[l];
				
				l++;
			}else {				
				testArray[k] = R[r];			
				r++;				
				
			}
			
			k++;
		}
		
		
		while(l<n1) {
			testArray[k] = L[l];
			k++;
			l++;
		}
		
		while(r<n2) {
			testArray[k] = R[r];
			k++;
			r++;
		}
		
	}

	public  void mergeSort( int low, int high) {

		if(low<high) {

			int mid = (low+high) / 2;

			mergeSort( low, mid);
			mergeSort( mid+1, high);
			merge( low, mid, high);
		}

	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		int [] arr = {1,2,0,0,0,1,2,1,2,1,1};
		//		int [] arr2 = Sort0s1s2s(arr);
		//		
		//		for (int i = 0; i < arr2.length; i++) {
		//			System.out.println(arr[i]);
		//		}



		int SIZE = SortArray.SIZE;

		int [] arr= new int[SIZE];
		int [] arr2 = new int[SIZE];
		int [] arr3 = new int[SIZE];

		for(int i=0;i<arr.length;i++) {

			arr[i] = (int)(Math.random()*SIZE);
			arr2[i] = arr[i];
			arr3[i] = arr[i]; 
		}
		
		

		//long t1,t2,t3;
		
				
		//int [] sorted2;
		//int [] sorted3;
//		ExecutorService es  = Executors.newSingleThreadExecutor();
//
//		es.submit(()-> {
//
//			long start1 = System.currentTimeMillis();		
//			int []sorted1 = insertionSort_v1(arr);
//			long end1 = System.currentTimeMillis();		
//			long t1 = end1-start1;
//			System.out.println("Time to run V1 = "+t1);
//		});
//
//		es.submit(()-> {
//
//			long start2 = System.currentTimeMillis();
//			int []sorted2 = insertionSort_v2(arr2);
//			long end2 = System.currentTimeMillis();
//			long t2 = end2-start2;
//			System.out.println("Time to run V2 = "+t2);
//		});
//		es.submit(()-> {
//
//			long start3 = System.currentTimeMillis();
//			int []sorted3 = insertionSort_v3(arr3);
//			long end3 = System.currentTimeMillis();
//			long t3 = end3-start3;
//			System.out.println("Time to run V3 = "+t3);
//		});

		
		
		long start1 = System.currentTimeMillis();		
		int []sorted1 = insertionSort_v1(arr);
		long end1 = System.currentTimeMillis();		
		long t1 = end1-start1;
		System.out.println("Time to run V1 = "+t1);

		
		long start2 = System.currentTimeMillis();
		int []sorted2 = insertionSort_v2(arr2);
		long end2 = System.currentTimeMillis();
		long t2 = end2-start2;
		System.out.println("Time to run V2 = "+t2);

		long start3 = System.currentTimeMillis();
		SortArray sa = new SortArray(arr3);
		sa.mergeSort(0, arr.length-1);
		int []sorted3 = sa.testArray;
		long end3 = System.currentTimeMillis();
		long t3 = end3-start3;
		System.out.println("Time to run V3 = "+t3);



		for (int i = 0; i < sorted2.length; i++) {
			if(sorted1[i] - sorted2[i] !=0 || sorted1[i] - sorted3[i] !=0) {
				System.out.println("Arrays not equal");
			}
		}

		System.out.println("Time for V1 = "+t1+" ,time for V2 = "+t2+" ,time for V3 = "+t3);


		System.out.println();
	}

}
