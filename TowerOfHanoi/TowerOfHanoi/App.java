package TowerOfHanoi;

public class App {
	
	public static void testRecursive(){
		
		System.out.println("Testing Recursive");
		
		Tower<Integer> source = new Tower<Integer>("source");
		Tower<Integer> buffer = new Tower<Integer>("buffer");
		Tower<Integer> destination = new Tower<Integer>("destination");
		
		source.push(4);
		source.push(3);
		source.push(2);
		source.push(1);
		
		TWHRecursive.moveAsTowerOfHanoi(4, source, buffer, destination);
	}
	
	public static void testIterative(){
		
		System.out.println("Testing Iterative");
		
		Tower<Integer> source = new Tower<Integer>("source");
		Tower<Integer> buffer = new Tower<Integer>("buffer");
		Tower<Integer> destination = new Tower<Integer>("destination");
		
		source.push(4);
		source.push(3);
		source.push(2);
		source.push(1);
		
		TWHIterative.moveAsTowerOfHanoi(4, source, buffer, destination);
	}
	public static void main(String[] args){
		testRecursive();
		testIterative();
	}

}
