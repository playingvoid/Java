package arrayProblems.DutchNationalFlg;

public class DuchNationalFlag {

    public static void duchNationalFlag(char[] input){
        if(input == null || input.length <= 1)
            return;

        int left = 0, mid = 0, right = input.length - 1;
        while(mid <= right) {
            if(input[mid] == 'G'){
                mid++;
            } else if(input[mid] == 'R') {
                input[left] = 'R';
                input[mid] = 'G';
                left++;
                mid++;
            } else {
                char temp = input[right];
                input[right] = 'B';
                input[mid] = temp;
                right--;
            }
        }
    }

    public static void mergeTwoSortedArray(int[] arr1, int[] arr2){
        int dest = arr2.length-1;
        int first =  arr1.length-1;
        int second = first;

        while(first >= 0 || second >= 0){
            if(second < 0 || (first >= 0 && arr1[first] > arr2[second])){
                arr2[dest] = arr1[first];
                first--;
            } else {
                arr2[dest] = arr2[second];
                second--;
            }
            dest--;
        }
        /*
        while(first >= 0 || second >= 0){
            if(first < 0 || (second >= 0 && arr2[second] > arr1[first])){
                arr2[dest] = arr2[second];
                second--;
            } else {
                arr2[dest] = arr1[first];
                first--;
            }
            dest--;
        }

         */


    }

    public static void main(String[] args){
        int[] arr1 = new int[]{1,3,5};
        int[] arr2 = new int[]{2,4,6,0,0,0};
        mergeTwoSortedArray(arr1, arr2);
        System.out.println(arr2);


    }
    public static void main1(String[] args){
        char[] input = new char[]{'B', 'R', 'G'};
        duchNationalFlag(input);
        System.out.println(input);
        input = new char[]{'B', 'R', 'G'};
        duchNationalFlag(input);
        System.out.println(input);

        input = new char[]{'B', 'B', 'B', 'B', 'B', 'B','R', 'G', 'G', 'B', 'B'};
        duchNationalFlag(input);
        System.out.println(input);

        input = new char[]{'B', 'R', 'G'};
        duchNationalFlag(input);
        System.out.println(input);

        input = new char[]{'B', 'R', 'G'};
        duchNationalFlag(input);
        System.out.println(input);

        input = new char[]{'B', 'R', 'G'};
        duchNationalFlag(input);
        System.out.println(input);

        input = new char[]{'B', 'R', 'G'};
        duchNationalFlag(input);
        System.out.println(input);
        System.out.println("This is a dline");
    }

}
