public class TwoSum {
    
    Map<Integer, Integer> map = new HashMap<>();
    // Add the number to an internal data structure.
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    
    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int tmp = value - (int)pair.getKey();
            if (tmp == (int)pair.getKey()) {
                if ((int)pair.getValue() >= 2) return true;
                else continue;
            } else {
                if (map.containsKey(tmp)) return true;
                else continue;
            }
        }
        return false;
    }
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
