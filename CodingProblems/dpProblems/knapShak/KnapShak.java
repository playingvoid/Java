package CodingProblems.dpProblems.knapShak;

public class KnapShak {
	
	public static void printSelectedItems(int[][] values, int[] value, int[] weight, int maxWeight){
		int itemAt = value.length;
		int weightAt = maxWeight;
		
		while(weightAt > 0 && itemAt > 0){
			if(values[itemAt][weightAt] != values[itemAt-1][weightAt]){ //itemAt is excluded
				//itemAt is included
				System.out.println("Item At: "+(itemAt-1)+" with weight="+weight[itemAt-1] + " and value="+value[itemAt-1]);
				weightAt -= weight[itemAt-1]; //Remaining weight to be found
			}
			itemAt --;
		}
	}
	
	public static int knapShack(int[] value, int[] weight, int maxWeight){
		if(value.length != weight.length)
			throw new RuntimeException("Length of value and weight array shold be equal");
		int itemNums = value.length;
		int[][] values = new int[itemNums + 1][maxWeight + 1];
		
		for(int item = 0; item <= itemNums; item++){
			for(int wt = 0; wt <= maxWeight; wt++){
				if(wt == 0 || item == 0)
					values[item][wt] = 0;
				else if(weight[item-1] > wt)
					values[item][wt] = values[item-1][wt];
				else
					values[item][wt] = Math.max(values[item-1][wt], value[item-1] + values[item-1][wt - weight[item-1]]);			                    
				
			}
		}
		printSelectedItems(values, value, weight, maxWeight);
		return values[itemNums][maxWeight];
	}
	
	public static void main(String[] args){
		int value[] = {7, 15, 5, 1};
		int weight[] = {8, 9, 10, 11};
		int maxWeight = 7;
		System.out.println(knapShack(value, weight, maxWeight));	
	}

}
