/*
 * Counter is a public class that will keep a running total of
 * time, as well as a counter of additions, and a running average
 * 
 * Sum will be the running total of time
 * Count is the number of additions to the total time
 */
 
public class Counter {
	
	int sum = 0;
	int count = 0;

	public Counter(int sum, int count) {
		this.sum = sum;
		this.count = count;
	}

	public void increment() {
		count++;
	}

	public void add(int value) {
		sum += value;
	}

	public float getAverage() {
		return sum / count;
	}
}
