package MergingIntervals;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 
 Calendar Days (variant of Skyline Problem)
 # of days where at least one person worked

 
              1  2  3  4  5  6   7   … M
Varun            x  x  x
Clara               x  x  x
Bob          x                   x

TOTAL CALENDAR DAY = 6

Intervals = [[2,4],[3,5],[1,1],[7,7]], N = number of intervals

 
 Given a long parent list of letters, and a short child list of letters. Return the shortest sub list of the long list that contains the short list.
 
 
parentList = [‘abcdaefg’] size n
childList = [‘afg’] size m
Return [‘aefg’]

problem('abcdaefg', 'afg') => min(problem('bcdaefg', fg), problem('efg', fg))
 
 a = 0, 5
 
 child[a] = 3;
 child[f] = 0;
 child[g] = 
 
 
 
 */

class MergeIntervals {
  
  static class Interval implements Comparable<Interval>{
    public int min;
    public int max;
    Interval(int min, int max){
      if(min <=0 )
        throw new RuntimeException("min can not be less than 1");
      if(max < min)
        throw new RuntimeException("min can not be less than 1");
      this.min = min;
      this.max = max;
    }
    
    @Override
    public int compareTo(Interval i){
      return Integer.valueOf(this.min).compareTo(Integer.valueOf(i.min));
    }
    
    public boolean isOverlap(Interval other){
      return !((this.max < other.min) || (this.min > other.max));
    }
    
    public int getDays(){
      return max - min + 1;
    }
  }
  
  private static Interval getMergedInterval(Interval one, Interval two){
    //return new  Interval(0,0);
    return new Interval(Math.min(one.min, two.min), Math.max(one.max,two.max));
  }
  
  
  public static Integer getAnswer(Interval[] intervals){
     if(intervals == null)
       return null;
     if(intervals.length == 0)
       return 0;
    
    Arrays.sort(intervals);
    
    Stack<Interval> mergedIntervals = new Stack<Interval>();
    
    for(Interval i :intervals ){
      
      if(mergedIntervals.isEmpty()){
        mergedIntervals.push(i);
      }else{
        
        if(i.isOverlap(mergedIntervals.peek())){
          Interval top = mergedIntervals.pop();
          mergedIntervals.push( getMergedInterval(top, i));
        }
        else{
          mergedIntervals.push(i);
        }
      }
    }
    
    int c = 0;
    while(!mergedIntervals.isEmpty()){
      Interval top = mergedIntervals.pop();
      c += top.getDays();
    }
    
    return c;
    
  }
  
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java 8.");
    
    Interval[] intervals = null;
    
    
    
    System.out.println(getAnswer(intervals));
    System.out.println(getAnswer(new Interval[]{}));
    
  }
}
