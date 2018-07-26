import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BST {

	TreeNode root ;

	public BST() {
		// TODO Auto-generated constructor stub
		root=null;
	}

	void insert(int data) {
		root = insert(root, data);
	}
	
	TreeNode insert(TreeNode root, int data) {

		if(root==null) {
			root = new TreeNode(data);
			return root;
		}

		if(data<root.data) {
			root.left = insert(root.left, data);
		} else {
			root.right = insert(root.right,data);
		}	

		return root;
	}

	void inOrder() {
		inOrder(root);
	}
	void preOrder() {
		preOrder(root);
	}
	void postOrder() {
		postOrder(root);
	}


	void inOrder(TreeNode root) {

		if(root==null) {
			return;
		}

		inOrder(root.left);
		System.out.print(root.data+", ");
		inOrder(root.right);

	}

	void preOrder(TreeNode root) {

		if(root==null) {
			return;
		}
		
		System.out.print(root.data+", ");
		preOrder(root.left);		
		preOrder(root.right);

	}

	void postOrder(TreeNode root) {

		if(root==null) {
			return;
		}

		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data+", ");
	}


	void levelOrder() {

		if(root==null)
			return;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while(!queue.isEmpty()) {

			TreeNode curNode = queue.poll();
			System.out.print(curNode.data+", ");

			if(curNode.left!=null) {
				queue.offer(curNode.left);
			}

			if(curNode.right!=null) {
				queue.offer(curNode.right);
			}			

		}

	}
	
	
	
	
	int checkHeight(TreeNode root) {
		if(root==null)
			return -1;
		
		int leftHeight = checkHeight(root.left);
		if(leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		
		int rightHeight = checkHeight(root.right);
		if(rightHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		
		if(Math.abs(leftHeight - rightHeight) > 1) {
			return Integer.MIN_VALUE;
		}else {
			return Math.max(leftHeight, rightHeight)+1;
		}
		
	}
	
	boolean isBalanced() {
		return (checkHeight(root) != Integer.MIN_VALUE);
	}

	
	void copyBSTtoArray() {
		
		
		
	}
	
	
	
	boolean checkBST2() {
		return checkBST2(root,null,null);
		
	}
	
	boolean checkBST2(TreeNode n, Integer min, Integer max) {
		if(n == null)
			return true;
		
		if( (min!=null && n.data<=min) || (max!=null && n.data>max) )
			return false;
		
		if((!checkBST2(n.left,min,n.data)) || (!checkBST2(n.right, n.data, max))) {
			return false;
		}
		
		return true;
	}
	
	
	
	boolean checkBST() {
		ArrayList<Integer> array = new ArrayList<>();
		copyBST(root,array);
		
		for(int i =1; i<array.size();i++) {
			System.out.print(array.get(i)+" ");
			if(array.get(i-1) > array.get(i))
				return false;
		}
		
		return true;
		
	}
	
	void copyBST(TreeNode root, ArrayList<Integer> array) {
		if(root==null)
			return;
		
		copyBST(root.left, array);
		array.add(root.data);
		copyBST(root.right, array);
		
	}
	
	void delete(int k) {
		deleteRec(root , k);
	}
	
	TreeNode deleteRec(TreeNode root, int data) {
		
		if(root==null) {
			return root;
		}
		
		if(data < root.data) {
			root.left = deleteRec(root.left, data);
		}else if(data > root.data){
			root.right = deleteRec(root.right, data);
		}else {
			
			if(root.left==null) {
				//node with null subtree
				return root.right;
			}else if(root.right==null) {
				//node with null right tree
				return root.left;				
			}
			
			//Node with 2 childrens
			root.data = minValue(root.right);
			
			//Delete in order successor
			root.right = deleteRec(root.right,root.data);
			
			
		}
		
		return root;
				
	}
	
	
	int minValue(TreeNode root) {
		if(root!=null) {
		  while(root.left!=null) {
			  root=root.left;
			  
		  }
			
		  return root.data;
		}
		
		return -1;
	}
	





}
