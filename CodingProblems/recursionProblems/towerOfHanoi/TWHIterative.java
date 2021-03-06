package recursionProblems.towerOfHanoi;

public class TWHIterative {
	
	private static void moveBetweenTower(Tower<Integer> source, Tower<Integer> destination){
		if(source == null || source.isEmpty())
			throw new RuntimeException("Invalid source tower");
		Integer elemToMove = source.pop();
		System.out.println(String.format("Moving %s from %s to %s", 
								elemToMove, source.getLabel(), destination.getLabel()));
		destination.push(elemToMove);	
	}
	
	private static void moveTopElementBetweenTower(Tower<Integer> tower1, Tower<Integer> tower2){
		if(tower1 == null || tower2 == null || (tower1.isEmpty() && tower2.isEmpty()))
			throw new RuntimeException("Invalid tower entries");
		
		if(tower1.isEmpty())
			moveBetweenTower(tower2, tower1);
		else if(tower2.isEmpty())
			moveBetweenTower(tower1,  tower2);
		else if(tower1.peek() > tower2.peek())
			moveBetweenTower(tower2, tower1);
		else if(tower2.peek() > tower1.peek())
			moveBetweenTower(tower1,  tower2);
		else
			throw new RuntimeException("Programming error - check the situation");
	}
	
	public static void moveAsTowerOfHanoi(int totalElements, 
			Tower<Integer> source,
			Tower<Integer> buffer,
			Tower<Integer> destination){
		
		int totalStepsRequiredToMoveElements = 
				(int) Math.pow(2, totalElements) - 1;
		//Swap destination and buffer
		if(totalElements % 2 == 0){
			Tower<Integer> temp = buffer;
			buffer = destination;
			destination = temp;
		} 
				
		for(int step = 1; step <= totalStepsRequiredToMoveElements; step ++){
			
			if(step % 3 == 1)
				moveTopElementBetweenTower(source, destination);
			else if (step % 3 == 2)
				moveTopElementBetweenTower(source, buffer);
			else // if (step % 3 == 0)
				moveTopElementBetweenTower(destination, buffer);	
		}
	}
}
