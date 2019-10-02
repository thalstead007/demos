/*
 * This code is running with java 1.8 and java-json.jar in the buildPath.
 * Java-json.jar can be found at TODO add URL
 * 
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JumpCloud_v1 {

	private Result result = new Result();

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		
		new JumpCloud_v1().run();

	}
	
	private void run() {
		//hashmap that stores actions, total times, need counter (global to use in method addAction
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		String str = new String();

		//Input strings for testing (normally down outside main)
        String input1 = "{\"action\":jump,\"time\":100}";
        String input2 = "{\"action\":run,\"time\":75}";
        String input3 = "{\"action\":jump,\"time\":200}";
        
        //Other test Cases
        //String input4 = null;
        //String input5 = "{\"resting\":jump,\"time\":200}";
        //String input6 = "{\"resting\":jump,\"time\":}";

        //call addAction with input string
		try {
			addAction(input1);
		} catch (Exception e) {
			System.out.println("Error processing " + input1);
		}
		//included for testing
		try {
			addAction(input2);
		} catch (Exception e) {
			System.out.println("Error processing " + input2);
		}
		//included for testing
		try {
			addAction(input3);
		} catch (Exception e) {
			System.out.println("Error processing " + input3);
		}
		
		//call getStat to get each action with its average time and output in Json Array
		try {
			String json = getStat();
			System.out.println(json);
		} catch (Exception e) {
			System.out.println("Error calculating stat");
		}
		
	}
	
	//Read in actions and time and keep a running average
	public void addAction(String str) throws JSONException{
        
        JSONObject obj = new JSONObject(str);
		
		//this will get values from the JSONObject and put into hashMap
		String action = obj.getString("action");
		int time = obj.getInt("time");
		
		result.put(action, time);
					
			
	}
	
	//Output actions and average time in JSONArray
	public String getStat() throws Exception {
		
		//Get results and put in map
		Map<String, Counter> map = result.getMap();
		
		//Create JSONArray to return
		JSONArray jsonArray = new JSONArray();
		
		//Iterate through each key, value pair in the map
		for (Map.Entry<String, Counter> entry : map.entrySet()) {
			String key = entry.getKey();
			Float average = entry.getValue().getAverage();
			
			//Add each pair to the array to be returned
	        JSONObject obj = new JSONObject();
	        obj.append(key, average);
	        jsonArray.put(obj);
			
		}
		
		
		//System.out.println("output" + jsonArray.toString());
		
		//Return the jsonArray as a string
		return jsonArray.toString();

	}

}
