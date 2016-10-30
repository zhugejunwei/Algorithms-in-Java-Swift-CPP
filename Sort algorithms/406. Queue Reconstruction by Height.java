public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                return x[0] == y[0] ? x[1] - y[1] : y[0] - x[0];
            }
        });
        List<int[]> res = new LinkedList<>();
        for(int[] pair : people) {
            res.add(pair[1], pair);
        }
        return res.toArray(new int[people.length][2]);
    }
}
