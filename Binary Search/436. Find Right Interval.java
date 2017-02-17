public class Solution {
    TreeMap<Integer, Integer> map = new TreeMap(); // start : idx
    public int[] findRightInterval(Interval[] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
        }
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(intervals[i].end);
            res[i] = (entry != null) ? entry.getValue() : -1;
        }
        return res;
    }
}
