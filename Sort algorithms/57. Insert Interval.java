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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList();
        int i = 0, len = intervals.size();
        while (i < len && intervals.get(i).end < newInterval.start) { // new.start > pre.end
            res.add(intervals.get(i++));
        }
        while (i < len && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start), Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        res.add(newInterval);
        while(i < len) res.add(intervals.get(i++));
        return res;
    }
}
