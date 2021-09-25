package Misc;
import java.util.*;

public class MinMaxOccurences {

    HashMap<String, Integer> stringToCount;
    SortedMap<Integer, HashSet<String>> countToStrings;


    /** Initialize your data structure here. */
    public void AllOne() {
        stringToCount = new HashMap<>();
        countToStrings = new TreeMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int count = stringToCount.getOrDefault(key, 0);
        RemoveOldCount(count, key);
        AddNewCount(count+1, key);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        int count = stringToCount.get(key);
        RemoveOldCount(count, key);
        AddNewCount(count-1, key);
    }

    private void RemoveOldCount(int oldCount, String key)
    {
        if(oldCount == 0)
            return;

        HashSet<String> oldSet = countToStrings.get(oldCount);
        oldSet.remove(key);
        if(oldSet.isEmpty())
            countToStrings.remove(oldCount);
    }

    private void AddNewCount(int count, String key)
    {
        if(count == 0)
        {
            stringToCount.remove(key);
        }
        else
        {
            stringToCount.put(key, count);
        }

        if(count != 0)
        {
            HashSet<String> newSet =  countToStrings.getOrDefault(count, new HashSet<String>());
            newSet.add(key);
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return countToStrings.get(countToStrings.lastKey()).iterator().next();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return countToStrings.get(countToStrings.firstKey()).iterator().next();
    }

    public static void main(String[] args)
    {
        System.out.println("Checking .... ");
    }
}
