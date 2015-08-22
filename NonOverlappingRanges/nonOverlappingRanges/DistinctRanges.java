package nonOverlappingRanges;

import java.util.*;

public class DistinctRanges
{
     static List<Range> findNonOverlappingRanges(Range[] inputRanges, Range inputOverlapRange)
     {
         List<Range> outputRanges = new ArrayList<Range>();
         Arrays.sort(inputRanges);
         RangeSplit rSplit = null;
         for(Range r : inputRanges)
         {
            //System.out.println("Processing Range = "+r);
            if(inputOverlapRange != null && r.isOverlap(inputOverlapRange))
            {
                rSplit = Range.spiltRange(r, inputOverlapRange);
                if(rSplit != null)
                {
                    if(rSplit.leftRange != null)
                    {
                        //System.out.println("Output = " + rSplit.leftRange);
                        outputRanges.add(rSplit.leftRange);
                    }
                    if(rSplit.rightRange != null)
                    {
                        //System.out.println("Next Overlap = " + rSplit.rightRange);
                    }
                    inputOverlapRange = rSplit.rightRange;
                }
            }
         }
         if(inputOverlapRange != null)
         {
            //System.out.println("Output = " + inputOverlapRange);
            outputRanges.add(inputOverlapRange);
         }
         return outputRanges;
     }
     public static void main(String []args)
     {
        Range[] inputRanges = new Range[]{new Range(15,17), new Range(10,12), new Range(20, 25)};
        System.out.println("Input Ranges: " + Arrays.asList(inputRanges)); 
        Range inputOverlapRange = new Range(0,30);
        System.out.println("Input overlap Range: " + inputOverlapRange);
        List<Range> nonOverlappingRanges = findNonOverlappingRanges(inputRanges, inputOverlapRange);
        System.out.println("Non overlapping ranges: " + nonOverlappingRanges);
     }
}
