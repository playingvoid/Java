package treeProblems;

public class sortedArrayToBST {
    private static binaryTreeNode arrayToBSTHelper(int[] inputArr, int left, int right){
        if(right < left)
            return null;
        int mid = left + (right - left)/2;
        binaryTreeNode curr = new binaryTreeNode(inputArr[mid]);
        curr.left = arrayToBSTHelper(inputArr, left, mid - 1);
        curr.right = arrayToBSTHelper(inputArr, mid + 1, right);
        return  curr;
    }

    private static binaryTreeNode arrayToBST(int[] inputArr){
        if(inputArr == null || inputArr.length == 0)
            return null;
        return  arrayToBSTHelper(inputArr, 0, inputArr.length - 1);
    }

    public static void main(String[] args){
        binaryTreeNode output = arrayToBST(new int[]{1,2,3,4,5,6,7,8});
    }
}
