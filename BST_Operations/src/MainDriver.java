

public class MainDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BST bst = new BST();


		//Tree with min height.
		bst.insert(6);
		bst.insert( 3);
		bst.insert( 1);
		bst.insert( 2);
		bst.insert( 4);
		bst.insert( 5);
		bst.insert( 9);
		bst.insert( 7);
		bst.insert( 8);
		bst.insert( 10);
		bst.insert( 11);
		
		bst.inOrder();
		System.out.println("Level order");
		bst.levelOrder();
		bst.delete(9);
		bst.levelOrder();
		
	/*	System.out.println(bst.checkBST());
		System.out.println(bst.checkBST2());*/
/*		
		//System.out.println(bst.checkHeight(root));
		System.out.println(bst.isBalanced());
		
		
		
		bst.inOrder();
		System.out.println("");
		bst.levelOrder();
		System.out.println("");
		bst.preOrder();
		System.out.println("");
		bst.postOrder();
		System.out.println("");
	*/	
	}
	
	
	
	

}
