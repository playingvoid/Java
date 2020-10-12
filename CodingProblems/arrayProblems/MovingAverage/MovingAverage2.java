package arrayProblems.MovingAverage;

class MovingAverage2{

    double[] array;

    int start;

    int currSize;

    int SIZE;

    double sum;

    public MovingAverage2(int size){

        array = new double[size];

        start = 0;

        SIZE = size;

        currSize = 0;

    }


    public double addValue(double newValue){

        if(currSize == SIZE){

            sum -= array[start];

        } else {

            currSize++;

        }

        array[start] = newValue;

        sum += newValue;

        start = (start + 1) % SIZE;

        return sum / currSize;

    }

}

