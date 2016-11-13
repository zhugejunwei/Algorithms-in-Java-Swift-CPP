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
