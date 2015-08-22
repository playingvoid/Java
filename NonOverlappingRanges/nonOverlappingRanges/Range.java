package nonOverlappingRanges;

//Abstracts a range class
//Implements comparable so two ranges could be compare based on min value of the range
public class Range implements Comparable<Range>
{
    public int min;
    public int max;
    
    public Range(int min, int max)
    {
        if(max < min)
            throw new RuntimeException("Range(): Invalid initialization of range");
        this.min = min;
        this.max = max;
    }
    
    boolean isOverlap(Range range)
    {
        if(range == null)
            throw new RuntimeException("isOverlap(): Expected non null range");
        return !(range.max < min || range.min > max) ;
    }
    
    @Override
    public int compareTo(Range range)
    {
        return Integer.valueOf(min).compareTo(Integer.valueOf(range.min));
    }
    
    @Override
    public String toString()
    {
        return "["+min+","+max+"]";
    }
    
    public static RangeSplit spiltRange(Range input, Range overlapRange)
    {
        if(input == null || overlapRange == null)
            return null;
            
        RangeSplit rangeSplit = new RangeSplit();
        if(input.isOverlap(overlapRange))
        {
            if(overlapRange.min < input.min)
            {
                rangeSplit.leftRange = new Range(overlapRange.min, input.min - 1);
            }
            if(overlapRange.max > input.max)
            {
                rangeSplit.rightRange = new Range(input.max + 1, overlapRange.max);
            }
        }
        return rangeSplit;
    }
}

//Just a wrapper class over the split instance of a range
class RangeSplit
{
    public Range leftRange;
    public Range rightRange;
    
    public RangeSplit()
    {
        this.leftRange = null;
        this.rightRange = null;
    }
    
    public boolean isEmptyRangeSplit()
    {
        return null == this.leftRange && null == this.rightRange;
    }
}

 
