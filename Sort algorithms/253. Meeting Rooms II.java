/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// using PriorityQueue
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> (a.start == b.start ? b.end - a.end : a.start - b.start));
        PriorityQueue<Integer> q = new PriorityQueue();
        q.offer(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            int curEnd = q.poll();
            if (intervals[i].start >= curEnd) {
                curEnd = intervals[i].end;
            } else {
                q.offer(intervals[i].end);
            }
            q.offer(curEnd);
        }
        return q.size();
    }
}


// another solution, using TreeMap
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> (a.start == b.start ? b.end - a.end : a.start - b.start));
        TreeMap<Integer, Integer> map = new TreeMap();
        for (Interval it : intervals) {
            Integer floor = map.floorKey(it.start);
            if (floor != null)
                map.compute(floor, (key, value) -> {
                    if (value == 1) return null;
                    return value - 1;
                });
            map.compute(it.end, (key, val) -> {
                if (val != null) return val + 1;
                return 1;
            });
        }
        int count = 0;
        for (int c : map.values()) count += c;
        return count;
    }
}
