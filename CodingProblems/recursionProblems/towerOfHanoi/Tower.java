package recursionProblems.towerOfHanoi;

import java.util.Stack;

public class Tower<T> extends Stack<T> {
	private static final long serialVersionUID = 1L;
	private String label;
	public Tower(String label){
		super();
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
}
