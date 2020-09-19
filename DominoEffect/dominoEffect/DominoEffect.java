package dominoEffect;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;

class Memory{
	static class Pair implements Comparable<Pair>{
		public int v1;
		public int v2;
		public Pair(int v1, int v2){
			this.v1 = v1;
			this.v2 = v2;
		}
		
		@Override
		public boolean equals(Object other){
			if(other == null || !(other instanceof Pair))
				return false;
			if(this == other)
				return true;
			Pair o = (Pair) other;
			return o.v1 == this.v1 && o.v2 == this.v2;
		}
		
		@Override 
		public int hashCode(){
			return Objects.hash(this.v1, this.v2);
		}
		
		@Override
		public String toString(){
			return "[" + v1 + ","+v2 + "]";
		}

		@Override
		public int compareTo(Pair o) {
			if(this.v1 == o.v1){
				return this.v2 - o.v2;
			}
			return this.v1 - o.v1;
		}
	}
	HashMap<Pair, Integer> resultMem;
	public Memory(){
		resultMem = new HashMap<>();
	}
	
	public void set(int index1, int index2, int value){
		resultMem.put(new Pair(index1, index2), value);
	}
	public int get(int index1, int index2){
		return resultMem.getOrDefault(new Pair(index1, index2), -1);
	}
	public int size(){
		return resultMem.size();
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		TreeMap<Pair, Integer> sortedMap = new TreeMap<>(resultMem);
		for(Map.Entry<Pair, Integer> entry : sortedMap.entrySet()){
			sb.append(entry.getKey() + "=>" + entry.getValue() + "\n");
		}
		return sb.toString();
	}
	
}

public class DominoEffect {
	

	private static int[] getSumArray(int[] distanceArray){
		int[] sumArray = new int[distanceArray.length];
		sumArray[0] = distanceArray[0];
		for(int i = 1 ; i < distanceArray.length; i++){
			sumArray[i] = sumArray[i-1] + distanceArray[i];
		}
		return sumArray;
	}
	
	public static int numMoves(int count, int height, int[] distanceArray){
		
		int[] sumArray = getSumArray(distanceArray);
		Memory memory = new Memory();
		memory.set(1,  0,  0);
		for(int i = 2; i <= count; i++){ //for number of dominos
			//System.out.println("i = " + i + "      height * (i-1) = " + height * (i-1) + "      height * (i-2) = " + height * (i-2));
			for(int d = 0 ; d <= height * (i-1); d++){
				//System.out.println("d = " + d);
				memory.set(i,  d,  (int)1e9);
			}	
			for(int d = 0 ; d <= height * (i-1); d++){
				for(int e = 1 ; e <= height; e++){
					//System.out.println("d = " + d + "\t e = " + e);
					if((d - e) > (height * (i-2))) continue;
					if((d - e) < 0 ) continue;
					int idValue = memory.get(i, d);
					int im1dm1Value = memory.get(i-1, d-e);
					if(sumArray[i-2] == d){
						memory.set(i, d, Math.min(idValue, im1dm1Value));
					} else {
						memory.set(i, d, Math.min(idValue, im1dm1Value + 1));
					}
				}
			}
			
		}
		//System.out.println(memory);
		System.out.println(memory.get(count, sumArray[count-2]));
		//System.out.println(memory);
		return -1;
	}
	
	public static void main(String[] args){
		numMoves(6, 2, new int[]{1,2,2,2, 3});
		//numMoves(8, 3, new int[]{2,4,4,1,4,3,2});
	}
}
