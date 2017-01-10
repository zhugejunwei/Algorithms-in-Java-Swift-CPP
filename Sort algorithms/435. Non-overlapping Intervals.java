public class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> (a.end == b.end ? b.start - a.start : a.end - b.end));
        int end = Integer.MIN_VALUE, count = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i].start >= end) end = intervals[i].end;
            else count++;
        }
        return count;
    }
}
