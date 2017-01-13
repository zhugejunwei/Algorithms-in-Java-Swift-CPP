import java.io.*;
import java.util.*;

public class myPathFinder {
	public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "test2.txt";
        if (args.length > 0) {
        	filename = args[0];
        }
        
        
        System.out.println("myPathFinder");
        List<String> answer = parseFile(filename);
        System.out.println(answer);
    }
    
    static List<String> parseFile(String filename)
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

        return parseLines(allLines);    	
    }
    
    static List<String> parseLines(List<String> lines) {
    	String[] oriAndDes = lines.get(0).split(" ");
    	String ori = oriAndDes[0];
    	String des = oriAndDes[1];
    	Map<String, List<String>> map = new HashMap<>();
    	for (int i = 1; i < lines.size(); i++) {
    		String[] startAndEnds = lines.get(i).split(":");
    		String start = startAndEnds[0].trim();
    		String[] endsString = startAndEnds[1].trim().split(" ");
    		List<String> ends = Arrays.asList(endsString);
    		map.put(start, ends);
    	}
    	Set<String> visited = new HashSet<>();
    	List<String> res = new ArrayList<>();
    	traverse(ori, des, map, visited, res, "");
    	return res;
    }
    
    private static void traverse(String ori, String des, Map<String, List<String>> map, Set<String> visited, List<String> res, String path) {
    	String curPath = path;
    	curPath += ori;
    	visited.add(ori);
    	if (ori.equals(des)) {
    		res.add(curPath);
    	} else {
    		List<String> next = map.get(ori);
    		if (next != null) {
    			for (String node: next) {
    				if (!visited.contains(node)) {
    					traverse(node, des, map, visited, res, curPath);
    				}
    			}
    		}
    	}
    	visited.remove(ori);
    }
}
