public class SummaryRanges {
    TreeMap<Integer, Interval> tree;
    
    public SummaryRanges() {
        tree = new TreeMap();
    }
    
    public void addNum(int val) {
        if (tree.containsKey(val)) return;
        Integer l = tree.lowerKey(val);
        Integer r = tree.higherKey(val);
        if (l != null && r != null && tree.get(l).end + 1 == val && val +  1 == r) {
            tree.get(l).end = tree.get(r).end;
            tree.remove(r);
        } else if (l != null && tree.get(l).end + 1 >= val) {
            tree.get(l).end = Math.max(tree.get(l).end, val);
        } else if (r != null && val + 1 == r) {
            tree.put(val, new Interval(val, tree.get(r).end));
            tree.remove(r);
        } else {
            tree.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList(tree.values());
    }
}
