public class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap();
        Set<String> vis = new HashSet();
        String[] arr = str.split(" ");
        if (pattern.length() != arr.length) return false;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (map.containsKey(pattern.charAt(i)) && !map.get(pattern.charAt(i)).equals(arr[i]))
                return false;
            if (!map.containsKey(pattern.charAt(i)) && vis.contains(arr[i]))
                return false;
            vis.add(arr[i]);
            map.put(pattern.charAt(i), arr[i]);
        }
        return true;
    }
}

// more concise solution
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        if (arr.length != pattern.length()) return false;
        Map map = new HashMap();
        for (Integer i = 0; i < pattern.length(); i++) // here we have to use "Integer" as an object in hashmap
            if (map.put(pattern.charAt(i), i) != map.put(arr[i], i))
                return false;
        return true;
    }
}

