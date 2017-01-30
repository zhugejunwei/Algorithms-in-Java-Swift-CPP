public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Arrays.asList(0);
        List<Set<Integer>> adjs = new ArrayList();
        for (int i = 0; i < n; i++) adjs.add(new HashSet());
        for (int[] e : edges) {
            adjs.get(e[0]).add(e[1]);
            adjs.get(e[1]).add(e[0]);
        }
        
        List<Integer> leaves = new ArrayList();
        for (int i = 0; i < n; i++) {
            if (adjs.get(i).size() == 1) leaves.add(i);
        }
        
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList();
            for (int oldleaf : leaves) {
                int newleaf = adjs.get(oldleaf).iterator().next(); // get new leaf
                adjs.get(newleaf).remove(oldleaf); // delete the old leaf
                if (adjs.get(newleaf).size() == 1) newLeaves.add(newleaf);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}
