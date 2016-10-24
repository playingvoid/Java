package recursionProblems.towerOfHanoi;

public class TWHRecursive {
	
	private static void moveBetweenTower(Tower<Integer> source, Tower<Integer> destination){
		if(source == null || source.isEmpty())
			throw new RuntimeException("Invalid source tower");
		Integer elemToMove = source.pop();
		System.out.println(String.format("Moving %s from %s to %s", 
								elemToMove, source.getLabel(), destination.getLabel()));
		destination.push(elemToMove);	
	}
	
	public static void moveAsTowerOfHanoi(int totalElements, 
							Tower<Integer> source,
							Tower<Integer> buffer,
							Tower<Integer> destination){
		if(totalElements > 0){
			//1. Move n-1 elements from source to buffer
			moveAsTowerOfHanoi(totalElements - 1, source, destination, buffer);
			//2. Move last element from source to destination
			moveBetweenTower(source, destination);
			//3. Now move elements collected buffer in the first step from buffer to destination
			moveAsTowerOfHanoi(totalElements - 1, buffer, source, destination);
		}	
	}
}
