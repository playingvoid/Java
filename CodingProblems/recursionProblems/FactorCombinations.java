package recursionProblems;
import java.util.*;

public class FactorCombinations {

    public static void main(String[] args){
        FactorCombinations fc = new FactorCombinations();

        List<List<Integer>> r = fc.getFactors(12);

        System.out.println(r);

    }

    //private List<List<Integer>> result;
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 1) {
            return result;
        }
        dfs(new ArrayList<>(), n, 2,  result);
        return result;
    }

    private void dfs(List<Integer> path, int currentValue, int start,  List<List<Integer>> result) {
        if (currentValue == 1) {
            if (path.size() > 1) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        //int maxValue = Math.max(2, (int)Math.sqrt(currentValue));
        for (int factor = start; factor <= currentValue; factor++) {
            if (currentValue % factor == 0) {
                path.add(factor);
                dfs(path, currentValue / factor, factor, result);
                path.remove(path.size() - 1);
            }
        }
        /*if(currentValue > 2) {
            path.add(currentValue);
            dfs(path, 1, currentValue, originalValue, result);
            path.remove(path.size() - 1);
        }
        */
    }

}
