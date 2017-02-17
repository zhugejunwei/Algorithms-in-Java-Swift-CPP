public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> dict = new HashSet(Arrays.asList(bank));
        if (!dict.contains(end)) return -1;
        Set<String> set1 = new HashSet();
        set1.add(start);
        Set<String> set2 = new HashSet();
        set2.add(end);
        int step = 1;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            if (set1.size() > set2.size()) {
                Set<String> tmp = set1;
                set1 = set2;
                set2 = tmp;
            }
            Set<String> nextLevel = new HashSet();
            for (String word : set1) {
                for (int i = 0; i < word.length(); i++) {
                    for (char c : "ACGT".toCharArray()) {
                        StringBuilder sb = new StringBuilder(word);
                        sb.setCharAt(i, c);
                        String newWord = sb.toString();
                        if (set2.contains(newWord)) return step;
                        
                        if (!dict.contains(newWord)) continue;
                        dict.remove(newWord);
                        
                        nextLevel.add(newWord);
                        
                    }
                }
            }
            step++;
            set1 = nextLevel;
        }
        return -1;
    }
}
