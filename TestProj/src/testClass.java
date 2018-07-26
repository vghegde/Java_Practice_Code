
public class testClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
//		String s = "VinayGHegde";
//		
//		String temp = s.toLowerCase();
//		int [] freqArray = new int[25];	
//		
//		for(int i=0;i<s.length();i++) {
//						
//			freqArray[((int)temp.charAt(i))-97]++;
//		}
//		
//		for(int i=0;i<temp.length();i++) {
//			System.out.println(s.charAt(i)+ "-"+freqArray[((int)temp.charAt(i))-97]);
//		}
		
		 int[][] problem = new int [5][5];
	        
	     for(int i=0;i<5;i++) {
	    	 for (int j=0;j<5;j++) {
	    		 problem[i][j] = (int)(Math.random()*20);
	    		 System.out.print(problem[i][j]+" ");
	    	 }
	    	 System.out.println("");
	     }
		 
		 
	     System.out.println("2D Peak = "+find2DPeak(problem, 0, -1));
		
		
//		int arr[] = new int[10];
//		for (int i = 0; i < 5; i++) {
//			arr[i] = i;
//		}
//		
////		for (int i = 5; i < arr.length-1 ; i++) {
////			arr[i] = 10-i;
////		}
//		
//		System.out.println("Input Array");
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//		}
//		System.out.println("Peak = "+ findPeak(arr, 0, arr.length-1));
//		
		
	}

	
	

static int countSetBits(int n){
	int count=0;
	while(n>0){
	
		n=n&(n-1);
		count++;
	
	}
	
	return count;


	}

	
static int findMax(int [][]problem,int col) {
	int maxIndex = 0;
	int max = 0;
	for(int row=0;row<problem.length;row++) {
		
		if(problem[row][col] > max) {
			maxIndex = row;
			max = problem[row][col];
		}
		
		
	}
	
	
	return maxIndex;
	
}

static int find2DPeak(int [][]problem,int left, int right) {
	//refer :  https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/MIT6_006F11_lec01.pdf
	
	int mid=0;
	
	if(problem.length<0)
		return 0;
	
	if(right==-1) {
		right = problem[0].length;
	}
	
	mid=(left+right)/2;
	
	int globalMax = findMax(problem,mid);
	
	if(
			(globalMax > 0 && problem[globalMax][mid] >= problem[globalMax-1][mid]) &&
			(globalMax+1 < problem.length && problem[globalMax][mid] >= problem[globalMax+1][mid]) &&
			(mid>0 && problem[globalMax][mid] >= problem[globalMax][mid-1]) &&
			(mid+1 < problem[globalMax].length && problem[globalMax][mid] >= problem[globalMax][mid+1]) 		
			) {
		return problem[globalMax][mid];
	}else if(mid > 0 && problem[globalMax][mid-1] > problem[globalMax][mid]) {
		find2DPeak(problem, left, mid);
	}else if(mid+1 < problem[globalMax].length && problem[globalMax][mid+1] > problem[globalMax][mid]) {
		find2DPeak(problem, mid, right);
	}
	
	return problem[globalMax][mid];
	
	
	
}

	static int findPeak(int []arr, int start, int end){
		int mid = start + (end-start)/2;
		
		if((mid==0 || arr[mid]>=arr[mid-1]) && (mid==arr.length-1 || arr[mid]>=arr[mid+1])) {
			return mid;
		}else if(mid>0 && arr[mid-1] > arr[mid] ) {
			return findPeak(arr, start, mid-1);
		}else {
			return findPeak(arr, mid+1, end);
		}
		
		
//		if(mid > 0 && arr[mid] < arr[mid-1]){
//			findPeak(arr,start,mid-1);
//			
//		}else if(mid<arr.length && arr[mid] < arr[mid+1]){
//			findPeak(arr, mid+1 ,end);
//		
//		}
//		
//			return arr[mid];
		

	}		
	
	
	
}
