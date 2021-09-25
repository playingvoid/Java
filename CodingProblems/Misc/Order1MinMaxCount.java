package Misc;

import java.util.*;

class DLLNode
{
    private int count;
    private HashSet<String> values;

    public DLLNode prev, next;

    public DLLNode(int count, String value)
    {
        this.count = count;
        this.values = new HashSet<>();
        this.values.add(value);
    }

    public void addValue(String value)
    {
        values.add(value);
    }

    public void removeValue(String value)
    {
        values.remove(value);
    }

    @Override
    public String toString() {
        return "[ Count:" + count + " -> Values:  (" + values + ")]";
    }

    public String getValuesString(){
        return values.toString();
    }

    public int getCount(){
        return count;
    }

    public HashSet<String> getValues(){
        return values;
    }
}

class DLLDeque
{
    private int count;
    private DLLNode first;
    private DLLNode last;

    public DLLDeque()
    {
        count = 0;
        first = null;
        last = null;
    }

    public void addFirst(DLLNode curr){
        if(first == null){
            first = curr;
            last = curr;
        }
        else {
            curr.next = first;
            first.prev = curr;
            first = curr;
        }
        count++;
    }
    public void remove(DLLNode curr) {
        DLLNode prev = curr.prev;
        DLLNode next = curr.next;

        if(prev != null)
            prev.next = next;
        else
            first = next;

        if(next != null)
            next.prev = prev;
        else
            last = prev;

        curr.next = null;
        curr.prev = null;

        count--;

    }

    public void addAfter(DLLNode node, DLLNode toAddNode)
    {
        DLLNode nextNode = node.next;
        node.next = toAddNode;
        toAddNode.prev = node;
        toAddNode.next = nextNode;
        if(nextNode != null)
            nextNode.prev = toAddNode;
        if(last.next != null)
            last = last.next;
        count++;
    }

    public void addBefore(DLLNode node, DLLNode toAddNode)
    {
        DLLNode prevNode = node.prev;
        toAddNode.next = node;
        node.prev = toAddNode;
        toAddNode.prev = prevNode;
        if(prevNode != null)
            prevNode.next = toAddNode;
        if(first.prev != null)
            first = first.prev;
        count++;
    }

    public boolean isEmpty(){
        return count == 0;
    }
    public DLLNode getFirst(){
        return first;
    }
    public DLLNode getLast(){
        return last;
    }

    public int getCount(){
        return count;
    }

    @Override
    public String toString() {
        if(first == null)
            return "NULL";
        else{
            String values = "";
            DLLNode curr = first;
            while(curr != null) {
                values += curr.toString();
                curr = curr.next;
            }
            return values;
        }
    }
}

public class Order1MinMaxCount {
    private HashMap<String, DLLNode> valueToNode;
    private DLLDeque dllDeque;

    public Order1MinMaxCount()
    {
        valueToNode = new HashMap<>();
        dllDeque = new DLLDeque();
    }

    public void Add(String value)
    {
        DLLNode newNode = null;
        if(valueToNode.containsKey(value))
        {
            DLLNode currCountNode = valueToNode.get(value);
            DLLNode nextNode = currCountNode.next;
            if(nextNode == null || nextNode.getCount() != currCountNode.getCount() + 1)
            {
                newNode = new DLLNode(currCountNode.getCount() + 1, value);
                dllDeque.addAfter(currCountNode, newNode);
            }
            else //if(nextNode.count == currCountNode.count + 1)
            {
                newNode = nextNode;
                nextNode.addValue(value);
            }
            currCountNode.removeValue(value);
            if(currCountNode.getValues().isEmpty())
            {
                dllDeque.remove(currCountNode);
            }
        }
        else
        {
            if(dllDeque.isEmpty())
            {
                newNode = new DLLNode(1, value);
                dllDeque.addFirst(newNode);
            }
            else
            {
                if(dllDeque.getFirst().getCount() == 1) {
                    newNode = dllDeque.getFirst();
                    dllDeque.getFirst().addValue(value);
                }
                else {
                    newNode = new DLLNode(1, value);
                    dllDeque.addFirst(newNode);
                }
            }
        }
        valueToNode.put(value, newNode);
    }

    public void remove(String value)
    {

        if(valueToNode.containsKey(value))
        {
            DLLNode currCountNode = valueToNode.get(value);
            DLLNode prevNode = currCountNode.prev;
            if(currCountNode.getCount() - 1 >= 1) {
                DLLNode newNode = null;
                if (prevNode == null || prevNode.getCount() != currCountNode.getCount() - 1) {
                    newNode = new DLLNode(currCountNode.getCount() - 1, value);
                    dllDeque.addBefore(currCountNode, newNode);
                }
                else {
                    //if (prevNode.count == currCountNode.count - 1)
                    prevNode.addValue(value);
                    newNode = prevNode;
                }
                valueToNode.put(value, newNode);
            } else {
                valueToNode.remove(value);
            }
            currCountNode.removeValue(value);
            if(currCountNode.getValues().isEmpty())
            {
                dllDeque.remove(currCountNode);
            }
        }
    }

    @Override
    public String toString(){
        return "valueToNode: " + valueToNode.toString() + "   dllDeque: " + dllDeque.toString();
    }

    public String getMin(){
        return dllDeque.getFirst().getValuesString();
    }
    public String getMax(){
        return dllDeque.getLast().getValuesString();
    }

    public int getCount(String value){
        return valueToNode.get(value).getCount();
    }

    public static void main(String[] args){
        Order1MinMaxCount countHash = new Order1MinMaxCount();

        String[] inputs = new String[]{"A:abc", "A:abc", "A:abc", "A:abc", "A:pqr", "A:pqr", "A:xyz", "R:abc", "GM", "gm" };

        for(String input : inputs){
            String[] splitValue = input.split(":");
            switch (splitValue[0]){
                case "A":
                    countHash.Add(splitValue[1]);
                    System.out.println(input + " -> " + countHash.toString());
                    break;
                case "R":
                    countHash.remove(splitValue[1]);
                    System.out.println(input + " -> " + countHash.toString());
                    break;
                case "GM":
                    System.out.println(countHash.getMax());
                    break;
                case "gm":
                    System.out.println(countHash.getMin());
                    break;
            }
        }
    }
}
