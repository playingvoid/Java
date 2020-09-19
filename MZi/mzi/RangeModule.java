package mzInterview;


public class RangeModule {

    private RangeNode root;
    
    private class Range{
    	public int lower;
    	public int upper;
    	public Range(int lower, int upper){
    		this.lower = lower;
    		this.upper = upper;
    	}
    }

    private class RangeNode {    
        int lower;
        int upper;
        int maxEnd;
        RangeNode right;
        RangeNode left; 

        public RangeNode(int start, int end, int maxEnd, RangeNode left, RangeNode right) {     
            this.lower = start;
            this.upper = end;
            this.maxEnd = maxEnd;
            this.left = left;
            this.right = right;
        }
        
        public boolean overlap(int lower, int upper, Range[] outsideRange){
        	if(this.upper < lower || this.lower > upper)
        		return false;
        	//Else overlap detected, update the input outside range with left and right intervals
        	//which is not part of current interval
        	if(lower < this.lower){
        		outsideRange[0] = new Range(lower, this.lower -1);
        	}
        	if(upper > this.upper){
        		outsideRange[1] = new Range(this.upper + 1, upper);
        	}	
        	return true;
        }
        
        @Override
        public String toString(){
        	return "[" + lower + ", "+upper+", "+maxEnd+"]";
        }
    }


    public void AddRange (int lower, int upper) {
        if (lower >= upper) throw new IllegalArgumentException("The end " + upper + " should be greater than start " + lower);

        RangeNode inode = root;
        while (inode != null) {
            inode.maxEnd = (upper > inode.maxEnd) ? upper : inode.maxEnd;
            if (lower < inode.lower) {
                if (inode.left == null) {
                    inode.left = new RangeNode(lower, upper, upper, null, null);
                    return;
                }
                inode = inode.left;
            } else {
                if (inode.right == null) {
                    inode.right = new RangeNode(lower, upper, upper, null, null);
                    return;
                }
                inode = inode.right;
            }
        }
        root =  new RangeNode(lower, upper, upper, null, null);
    }

    
    public boolean QueryRange(int lower, int upper){
    	if (lower > upper) 
    		throw new IllegalArgumentException("The upper " + upper + " should be greater than equal to lower " + lower);
    	
    	return QueryRange(root, lower, upper);
    }
    
    private static boolean QueryRange(RangeNode root, int lower, int upper){
    	if(root == null)
    		return false;
    	Range[] outSideRange = new Range[2];
    	if(root.overlap(lower, upper, outSideRange)){
    		boolean outSiderOverlaps = true;
    		if(outSideRange[0] != null)
    			outSiderOverlaps = outSiderOverlaps && QueryRange(root.left, outSideRange[0].lower, outSideRange[0].upper);
    		if(outSideRange[1] != null)
    			outSiderOverlaps = outSiderOverlaps && QueryRange(root.right, outSideRange[1].lower, outSideRange[1].upper);
    		return outSiderOverlaps;
    		
    	}
    	else {
    		if (shouldGoLeft(root.left, lower, upper)) {
                return QueryRange(root.left, lower, upper);
            } else {
                return QueryRange(root.right, lower, upper);
            }
    	}		
    }
    
    private static boolean shouldGoLeft(RangeNode leftChildNode, int start, int end) {
        return leftChildNode != null && leftChildNode.maxEnd > start;
    }

    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.AddRange(10, 20);
        rangeModule.AddRange(10, 30);
        rangeModule.AddRange(20, 30);

        System.out.println("Expected true,   Actual: " + rangeModule.QueryRange(15,25));
        System.out.println("Expected true,   Actual: " + rangeModule.QueryRange(12,18));
        System.out.println("Expected false,   Actual: " + rangeModule.QueryRange(-5,10));

        /*
        System.out.println("Expected true,   Actual: " + rangeModule.overlap(23, 25));
        System.out.println("Expected false,  Actual: " + rangeModule.overlap(12, 14));
        System.out.println("Expected true,   Actual: " + rangeModule.overlap(21, 23));
        // testing adjoint
        System.out.println("Expected false,  Actual: " + rangeModule.overlap(10, 15));
        System.out.println("Expected false,  Actual: " + rangeModule.overlap(10, 14));
        System.out.println("Expected false,  Actual: " + rangeModule.overlap(11, 15));
        */
    }
}