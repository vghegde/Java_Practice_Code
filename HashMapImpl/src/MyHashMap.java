import java.util.LinkedHashSet;
import java.util.Set;

public class MyHashMap<K, V> {

	class Entry<K, V>{
		final K Key;
		V value;
		Entry next;

		Entry(K key, V val){
			this.Key = key;
			this.value = val;
		}

		public V getValue() {
			return this.value;
		}

		public K getKey() {
			return this.Key;
		}

		public K getKey(K key) {
			return this.Key;
		}		

	}

	private static final int MIN_CAPACITY=4;

	private Entry<K,V> [] buckets = new Entry[MIN_CAPACITY];
	

	public void put(K key, V value) {
		if(key==null || value==null) {
			System.out.println("Error");
			return;
		}
		
		int hash = key.hashCode() % MIN_CAPACITY;
		Entry<K, V> curValue = buckets[hash];

		if(curValue == null) {

			buckets[hash] = new Entry<K, V>(key, value);
		}else {
			if((curValue.Key.equals(key)) || (curValue.getKey() == key)) {
				//Duplicate Key, update old value with new one.
				curValue.value = value;
			}else {
				//Collision
				Entry<K,V> temp=curValue;

				while(temp.next!=null) {
					temp=temp.next; 
				}

				temp.next = new Entry<K, V>(key, value);

			}
		}

	}

	public Entry<K,V> get(K key){
		Entry<K, V> result=null;
		
		if(key==null) {
			return null;
		}
		
		//calculate hash
		int hash = key.hashCode() % MIN_CAPACITY;
		
		Entry<K, V> curVal = buckets[hash];
		while(curVal!=null) {
			
			if(curVal.Key == key || curVal.getKey().equals(key)) {
				return curVal;
			}
			
			curVal=curVal.next;
		}
		
		return result;
	}
	
	public Set<K> getKeyset(){
		Set<K> keyList= null;	

		for (Entry<K, V> elem : buckets) {
			
			if(keyList == null) {
				keyList = new LinkedHashSet<K>();
			}
			
			while(elem!=null) {
				keyList.add(elem.Key);
				elem=elem.next;
			}
		}
		
		
		return keyList;
	}

	public static void main(String[] args) {
		MyHashMap<Integer, String > myMap = new MyHashMap<>();
		myMap.put(1, "vghegde");
		myMap.put(1, "vghegde2");
		System.out.println(myMap.getKeyset());
		myMap.put(3, "vghegde3");
		myMap.put(4, "vghegde4");
		myMap.put(5, "vghegde5");
		myMap.put(6, "vghegde6");
		myMap.put(7, "vghegde7");
		myMap.put(8, "vghegde8");
		
		System.out.println(myMap.getKeyset());
		System.out.println(myMap.get(1).getValue());
		myMap.diaplayMap();
	}
	
	
	public void diaplayMap() {
		for(Entry<K, V> e : buckets) {
			
			while(e!=null) {
				
				System.out.print("("+e.getKey()+","+e.getValue()+")");
				e=e.next;
				
				if(e!=null) {
					System.out.print("->");
				}else {
					System.out.println("");
				}
			}
			
		}
	}

}
