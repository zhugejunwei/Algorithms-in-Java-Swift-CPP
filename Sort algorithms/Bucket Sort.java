/*
 1. Using Hashmap<letter or number, count>() to count the frequency, also record the letter or number as the key.
 
 2. Using an array, whose index is the count in former hashmap, and value is the letter or a list of letter.
 
 3. from the largest bound(size) to lower bound(0), check whether there is a valid value in array, if true, add it into the res[] or result list.
 
 Another way:
 
 1. Using an array to count the frequency like this question.
 
 2. Using a map<count, (list of) letters or numbers>, count is the frequency value in the former array, and map.value is the array's index, which may be char ((char)i) or number.
 
 3. Using StringBuilder to output string, or res[], or List res. Like mentioned before, iterate from the largest to lowest, if the question requires largest K elements. If it requires lowest k elements, vise versa.
 
*/

// E.g: 347. Top K Frequent Elements
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        int maxCount = 0;
        Map<Integer, Integer> map = new HashMap(); // num, count
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            maxCount = Math.max(map.get(n), maxCount);
        }
        List<Integer>[] hash = new List[maxCount + 1]; // index = count, value = num
        for (int i = 0; i <= maxCount; i++) hash[i] = new ArrayList<>();
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            hash[entry.getValue()].add(entry.getKey());
        }
        List<Integer> res = new ArrayList();
        for (int i = maxCount; i >= 0 && res.size() < k; i--) {
            if (hash[i] != null)
                res.addAll(hash[i]);
        }
        return res;
    }
}
////
