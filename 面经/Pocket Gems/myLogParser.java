import java.io.*;
import java.util.*;
import java.text.*;

public class myLogParser {
	public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "test3.txt";
        if (args.length > 0) {
        	filename = args[0];
        }

        String answer = parseFile(filename);
        System.out.println("myLogParser");
        System.out.println(answer);
    }

    static String parseFile(String filename)
            throws FileNotFoundException, IOException {
        /*
    	 *  Don't modify this function
    	 */
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
            allLines.add(line);
        }
        input.close();

        return parseLines(allLines.toArray(new String[allLines.size()]));
    }
    
    static String parseLines(String[] lines) {
    	Set<String> set = new HashSet<>(Arrays.asList("START", "CONNECTED", "DISCONNECTED", "SHUTDOWN"));
    	List<Date> times = new ArrayList<>();
    	List<String> events = new ArrayList<>();
    	for (String line : lines) {
    		String[] timeAndEvent = line.split(" :: ");
    		String event = timeAndEvent[1];
    		if (set.contains(event)) {
    			String timeString = timeAndEvent[0].substring(1, timeAndEvent[0].length() - 1);
    			DateFormat df = new SimpleDateFormat("MM/dd/yyyy-hh:mm:ss");
    			Date time = new Date();
    			try {
    				time = df.parse(timeString);
    			} catch (ParseException ignored) {}
    			times.add(time);
    			events.add(event);
    		}
    	}
    	long totalTime = times.get(times.size() - 1).getTime() - times.get(0).getTime();
    	long connectedTime = 0;
    	long mark = times.get(1).getTime();
    	boolean isConnected = false;
    	for (int i = 1; i < times.size(); i++) {
    		String currentEvent = events.get(i);
    		long currentTime = times.get(i).getTime();
    		if (currentEvent.equals("CONNECTED")) {
    			isConnected = true;
    			mark = currentTime;
    		} else {
    			if (isConnected) {
    				connectedTime += currentTime - mark;
    			}
    			isConnected = false;
    		}
    	}
    	double ratio = (double) connectedTime / totalTime * 100;
        return String.format("%d%s", (int) ratio, "%");
    }
    
}
