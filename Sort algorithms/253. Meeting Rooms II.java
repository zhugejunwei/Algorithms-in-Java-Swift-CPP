/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;
        Arrays.sort(intervals, (a, b) -> (a.start == b.start ? b.end - a.end : a.start - b.start));
        
        PriorityQueue<Interval> q = new PriorityQueue(new Comparator<Interval>(){
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });
        q.offer(intervals[0]);
        for (int i = 1; i < n; i++) {
            Interval cur = q.poll();
            if (intervals[i].start >= cur.end) {
                cur.end = intervals[i].end;
            } else {
                q.offer(intervals[i]);
            }
            q.offer(cur);
        }
        return q.size();
    }
}
