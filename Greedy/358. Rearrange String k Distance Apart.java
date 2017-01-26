public class Solution {
    public String rearrangeString(String str, int k) {
        int len = str.length();
        int[] count = new int[128]; // count of each character
        int[] pos = new int[128]; // the valid position of each character
        
        for (char c : str.toCharArray()) count[c]++;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int validChar = findPos(count, pos, i);
            if (validChar == -1) return "";
            count[validChar]--;
            pos[validChar] = i + k; // move this char k steps ahead
            sb.append((char)validChar);
        }
        return sb.toString();
    }
    
    private int findPos(int[] count, int[] pos, int candidatePos) {
        int maxCount = Integer.MIN_VALUE;
        int validChar = -1;
        for (int c = 0; c < 128; c++) {
            // 1. the count of c is largest
            // 2. current position of c is no more than candidatePos
            if (count[c] > 0 && count[c] > maxCount && candidatePos >= pos[c]) {
                maxCount = count[c];
                validChar = c;
            }
        }
        return validChar;
    }
}

// another solution

public class Solution {
    public String rearrangeString(String str, int k) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap();
        for (char c : str.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        
        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<> (new Comparator<Map.Entry<Character, Integer>>(){
            @Override
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        maxHeap.addAll(map.entrySet());
        
        Queue<Map.Entry<Character, Integer>> waitHeap = new ArrayDeque<>();
        
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> cur = maxHeap.poll();
            sb.append(cur.getKey());
            cur.setValue(cur.getValue() - 1);
            waitHeap.offer(cur);
            // if the gap is less than k, keep going
            if (waitHeap.size() < k) continue;
            
            Map.Entry<Character, Integer> next = waitHeap.poll();
            if (next.getValue() > 0)
                maxHeap.offer(next);
        }
        
        return sb.length() == str.length() ? sb.toString() : "";
    }
}
