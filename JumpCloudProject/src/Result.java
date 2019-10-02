/*
 * Results class will create and manipulate a hashMap of 
 * action, Counter object pairs - Counter being (totalTime, numberOfAdditions)
 * 
 * Put will be synchronized in order to allow for concurrent calls while maintaining consistency 
 */

import java.util.HashMap;
import java.util.Map;

public class Result {
	private Map<String, Counter> map = new HashMap<>();
	
	public synchronized void put(String action, int time) {
		
		//Call the convert method so all actions will be lowercase
		Counter counter = map.get(convertAction(action));
		
		//If the action is not already in the map, add it
		if(counter == null) {
			map.put(convertAction(action), new Counter(time, 1));
		
		//Otherwise add the new time to the running total, increment the counter
		} else {
			counter.increment();
			counter.add(time);
		}
		
	}

	//Get the map containing all key, value pairs
	public Map<String, Counter> getMap(){
		return map;
	}
	
	//Convert all incoming actions to lower case for consistency 
	private String convertAction(String action) {
		return action.toLowerCase();
	}
	

	
}
 