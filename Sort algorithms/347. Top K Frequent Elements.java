public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for (int key : map.keySet()) {
            int cnt = map.get(key);
            if (bucket[cnt] == null)
                bucket[cnt] = new ArrayList<>();
            bucket[cnt].add(key);
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = nums.length; i > 0 && res.size() < k; i--) {
            if (bucket[i] == null) continue;
            res.addAll(bucket[i]);
        }
        return res;
    }
}

// if {1, 2, 3}, k = 2, should return 2 elements
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap();  // val : count
        int maxCount = 0;
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            if (count.get(num) > maxCount)
                maxCount = count.get(num);
        }
        if (k > count.size()) k = count.size();
        
        Map<Integer, Set<Integer>> countVal = new HashMap(); // count : val
        for (int val : count.keySet()) {
            int freq = count.get(val);
            if (!countVal.containsKey(freq)) countVal.put(freq, new HashSet());
            countVal.get(freq).add(val);
        }
        
        List<Integer> res = new ArrayList();
        for (int i = maxCount; i > 0 && k > 0; i--) {
            if (countVal.containsKey(i)) {
                while (k > 0 && !countVal.get(i).isEmpty()) {
                    res.add((int)countVal.get(i).iterator().next());
                    countVal.get(i).remove(res.get(res.size() - 1));
                    k--;
                }
            }
        }
        return res;
    }
}
