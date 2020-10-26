package treeProblems;

import java.util.LinkedList;
import java.util.Queue;

public class levelOrderSerializeDeserialize {

    public static String serialize(binaryTreeNode root){
        if(root == null)
            return null;
        StringBuilder sb = new StringBuilder();
        Queue<binaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            binaryTreeNode btNode = queue.poll();
            if(btNode != null){
                sb.append(btNode.value);
                queue.add(btNode.left);
                queue.add(btNode.right);
            } else {
                sb.append("#");
            }
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


    public static binaryTreeNode deSerialize(String serialized){
        if(serialized == null || serialized.length() == 0 || serialized == "#")
            return null;

        String[] splittedSerialized = serialized.split(" ");

        binaryTreeNode root = new binaryTreeNode(Integer.parseInt(splittedSerialized[0]));

        Queue<binaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int index = 1;
        while(!queue.isEmpty()){
            binaryTreeNode btNode = queue.poll();
            if(btNode == null)
                continue;
            if(!splittedSerialized[index].equals("#"))
                btNode.left = new binaryTreeNode(Integer.parseInt(splittedSerialized[index]));
            else
                btNode.left = null;
            queue.add(btNode.left);
            index++;
            if(!splittedSerialized[index].equals("#"))
                btNode.right = new binaryTreeNode(Integer.parseInt(splittedSerialized[index]));
            else
                btNode.right = null;
            queue.add(btNode.right);
            index++;
        }

        return root;
    }

    public static void main(String[] args){
        System.out.println("This is serialize desrialize");
        binaryTreeNode btNode = binaryTreeNode.createBinaryTree(new Integer[]{
                1, 2, 5,null,null,6, null,null,3,null,4,null,null
        });
        String serialized = serialize(btNode);
        System.out.println(serialized);

        binaryTreeNode btNode2 = deSerialize(serialized);
        serialized = serialize(btNode2);
        System.out.println(serialized);
    }
}
