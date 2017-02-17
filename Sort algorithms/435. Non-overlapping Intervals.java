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

// another way
public class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> (a.end == b.end ? b.start - a.start : b.end - a.end));
        int res = 0, start = intervals[0].start;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].end > start) {
                if (intervals[i].start > start) start = intervals[i].start;
                res++;
            } else start = intervals[i].start;
        }
        return res;
    }
}
