package arrayProblems.MovingAverage;

/*
Please use this Google doc to code during your interview. To free your hands for coding, we recommend that you use a headset or a phone with speaker option.


Q. When drawing a timeseries graph, you sometimes want to "smooth out" the line. Instead of plotting the Y-value of each data point, you want to plot the average of 
the last N Y-values. Your task is to design a class to handle the computation of a moving average. The number N of values to average is called the "window".
*/


import java.util.Deque;
import java.util.LinkedList;

class MovingAverage{

	int count;

	int SIZE;

	Double sum;

	Deque<Double> list;


	public MovingAverage(int size){

		if(size <= 0)

			throw new RuntimeException("can not create for size less than or equal to 0");

		count = 0;

		SIZE = size;

		sum = 0.0;

		list = new LinkedList<>();

	}


	public void addValue(Double value){ 

		if(value == null)

			throw new RuntimeException("Can not accept null");

		if(count < SIZE){

			list.addLast(value);

			count++;

		} else {

			Double last = list.removeFirst();

			list.add(value);

			sum -= last;

		}

		sum += value;

	}


	public Double getAverage(){

		if(count == 0)	

			throw new RuntimeException("No value in list");


		return sum / count;

}

}


