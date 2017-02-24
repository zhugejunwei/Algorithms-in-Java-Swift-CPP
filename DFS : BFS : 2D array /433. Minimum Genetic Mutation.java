public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> dict = new HashSet(Arrays.asList(bank));
        if (!dict.contains(end)) return -1;
        
        Set<String> set1 = new HashSet(), set2 = new HashSet();
        set1.add(start); set2.add(end);
        
        int step = 0;
        while (!set1.isEmpty() && !set2.isEmpty()) {
            if (set1.size() > set2.size()) {
                Set<String> tmp = set1;
                set1 = set2;
                set2 = tmp;
            }
            step++;
            Set<String> nextLevel = new HashSet();
            for (String gene : set1) {
                for (int i = 0; i < gene.length(); i++) {
                    StringBuilder sb = new StringBuilder(gene);
                    for (char c : "ACGT".toCharArray()) {
                        sb.setCharAt(i, c);
                        String newGene = sb.toString();
                        
                        if (set2.contains(newGene))
                            return step;
                        
                        if (!dict.contains(newGene)) continue;
                        
                        nextLevel.add(newGene);
                    }
                }
            }
            dict.removeAll(nextLevel);
            set1 = nextLevel;
        }
        return -1;
    }
}
