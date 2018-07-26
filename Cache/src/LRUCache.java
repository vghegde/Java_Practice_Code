import java.util.HashMap;

public class LRUCache {

	
	//DLL Node
	class Node{
		int key;
		int value;
		Node prev=null;
		Node next=null;
		
		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}
	
	
	int capacity;
	HashMap<Integer, Node> map = new HashMap<>();
	
	Node head = null;
	Node tail = null;
	
	public LRUCache(int cap) {
		capacity  = cap;
	}
	
		
	public int get(int key) {
		
		if(map.containsKey(key)) {
			Node n  = map.get(key);
			remove(n);
			setHead(n);
			return n.value;
		}
		
		return -1;
		
	}
	
	
	
	
	void remove(Node n) {
		if(n.prev!=null) {
			n.prev.next = n.next;
			
		}else {
			head = n.next; 
		}
		
		if(n.next!=null) {
			n.next.prev = n.prev;
		}else {
			tail = n.prev;
		}
		
		
		
	}
	
	void setHead(Node n) {
		
		n.next = head;
		n.prev = null;
		
		if(head!=null) {
			head.prev = n;
		}
		
		head =n;
		
		if(tail==null) {
			tail = head;
		}
		
	}
	
	

	public void set(int key, int value) {
		
		if(map.containsKey(key)) {
			//Node already in cache
			Node old = map.get(key);
			old.value = value;
			remove(old);
			setHead(old);
		}else {
			//New node not in cache
			
			Node newNode = new Node(key, value);
			if(map.size()>=capacity) {
				map.remove(tail.key);
				remove(tail);
			}
			
			setHead(newNode);				
			map.put(key, newNode);
			
		}
		
	}

}
