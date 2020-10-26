package treeProblems;

public class dLLToBST {

    private static binaryTreeNode head;

    public static binaryTreeNode buildBSTFromSortedList(binaryTreeNode L){
        head = L;
        int length = getDLLLength(L);
        return buildSortedListHelper(0, length);
    }

    private static binaryTreeNode buildSortedListHelper(int start, int end){
        if(start >= end)
            return null;

        int mid = start + ((end - start) / 2);

        binaryTreeNode left = buildSortedListHelper(start, mid);
        binaryTreeNode curr = head; //new binaryTreeNode(head.value);
        head = head.right;
        curr.left = left;
        curr.right = buildSortedListHelper(mid + 1, end);
        return curr;
    }

    private static int getDLLLength(binaryTreeNode first){
        if(first == null)
            return 0;
        int count = 0;
        while(first != null){
            count ++;
            first = first.right;
        }
        return count;
    }
    public static void main(String[] args) {
        binaryTreeNode btNode1 = new binaryTreeNode(1);
        binaryTreeNode btNode2 = new binaryTreeNode(2);
        binaryTreeNode btNode3 = new binaryTreeNode(3);
        binaryTreeNode btNode4 = new binaryTreeNode(4);
        binaryTreeNode btNode5 = new binaryTreeNode(5);
        binaryTreeNode btNode6 = new binaryTreeNode(6);
        binaryTreeNode btNode7 = new binaryTreeNode(7);
        //binaryTreeNode btNode6 = btNode1;

        btNode1.right = btNode2; btNode2.left = btNode1;
        btNode2.right = btNode3; btNode3.left = btNode2;
        btNode3.right = btNode4; btNode4.left = btNode3;
        btNode4.right = btNode5; btNode5.left = btNode4;
        btNode5.right = btNode6; btNode6.left = btNode5;
        btNode6.right = btNode7; btNode7.left = btNode6;

        binaryTreeNode root = buildBSTFromSortedList(btNode1);


        System.out.println("1 ->" + Integer.toHexString(System.identityHashCode(btNode1)));
        System.out.println("2 ->" + Integer.toHexString(System.identityHashCode(btNode2)));
        System.out.println("3 ->" + Integer.toHexString(System.identityHashCode(btNode3)));
        System.out.println("4 ->" + Integer.toHexString(System.identityHashCode(btNode4)));
        System.out.println("5 ->" + Integer.toHexString(System.identityHashCode(btNode5)));
        System.out.println("6 ->" + Integer.toHexString(System.identityHashCode(btNode6)));
        System.out.println("7 ->" + Integer.toHexString(System.identityHashCode(btNode7)));


        System.out.println("Root 4 ->" + Integer.toHexString(System.identityHashCode(root)));
        System.out.println("Root.left ->" + Integer.toHexString(System.identityHashCode(root.left)));
        System.out.println("Root.Right ->" + Integer.toHexString(System.identityHashCode(root.right)));
        System.out.println("Root.left.left ->" + Integer.toHexString(System.identityHashCode(root.left.left)));
        System.out.println("Root.left.right ->" + Integer.toHexString(System.identityHashCode(root.left.right)));
        System.out.println("Root.right.left ->" + Integer.toHexString(System.identityHashCode(root.right.left)));
        System.out.println("Root.right.right ->" + Integer.toHexString(System.identityHashCode(root.right.right)));
    }
}
