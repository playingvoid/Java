package brproblems;
import java.io.File;
import java.util.Scanner;
import java.util.stream.LongStream;

public class StockGrants {
	private int numEmployees;
	private long[] empRatings;
	private long[] stockGrants;
	private static final int CLOSE = 2;
	
	private StockGrants(int numEmployees){
		this.numEmployees = numEmployees;
		empRatings = new long[this.numEmployees];
		stockGrants = new long[this.numEmployees];
	}
	
	private void initilaizeEmpRatings(Scanner sc){
		for(int i = 0; i< numEmployees; i++){
			empRatings[i] = sc.nextInt();
		}
	}
	
	private  void initializeMinStockGrats(Scanner sc){
		for(int i = 0; i< numEmployees; i++){
			stockGrants[i] = sc.nextInt();
		}
	}
	private long getLeftMin(int index){
		int leftIndex = Math.max(0, index - CLOSE);
		long resultStockGrant = stockGrants[index];
		long currentRating = empRatings[index];
		for(int i = leftIndex; i < index; i++){
			if(currentRating > empRatings[i]){
				resultStockGrant = Math.max(resultStockGrant, stockGrants[i] + 1);
			}
		}
		return resultStockGrant;
	}
	
	private long getRightMin(int index){
		int rightIndex = Math.min(numEmployees - 1, index + CLOSE);
		long resultStockGrant = stockGrants[index];
		long currentRating = empRatings[index];
		for(int i = index+1; i <= rightIndex; i++){
			if(currentRating > empRatings[i]){
				resultStockGrant = Math.max(resultStockGrant, stockGrants[i] + 1);
			}
		}
		return resultStockGrant;
	}
	
	private void calculateMinStock(){
		for(int i = 1; i < numEmployees; i++){
			stockGrants[i] = Math.max(stockGrants[i], getLeftMin(i));
		}
		
		for(int i = numEmployees-2; i >= 0; i--){
			stockGrants[i] = Math.max(stockGrants[i], getRightMin(i));
		}
	}
	private void printState(){
		System.out.println(numEmployees);
		
		for(long r : empRatings){
			System.out.print(r + ",");
		}
		System.out.println();
		for(long s : stockGrants){
			System.out.print(s + ",");
		}
		System.out.println();
		
		long sum = LongStream.of(stockGrants).sum();
		System.out.println(sum);
	}

	public static void main(String[] args) {
		try {
			File file = new File("stockgrant.txt");
			Scanner sc = new Scanner(file);
			int numEmployee = sc.nextInt();
			StockGrants stockGrants = new StockGrants(numEmployee);
			stockGrants.initilaizeEmpRatings(sc);
			stockGrants.initializeMinStockGrats(sc);
			
			stockGrants.printState();
			stockGrants.calculateMinStock();
			stockGrants.printState();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
