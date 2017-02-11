public class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap();
        String[] arr = str.split(" ");
        Set<String> set = new HashSet();
        if (arr.length != pattern.length()) return false;
        int i = 0;
        while (i < pattern.length()) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (set.contains(arr[i])) return false;
                map.put(pattern.charAt(i), arr[i]);
                set.add(arr[i]);
            } else {
                if (!map.get(pattern.charAt(i)).equals(arr[i])) return false;
            }
            i++;
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
