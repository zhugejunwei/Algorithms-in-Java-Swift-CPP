public class ZigzagIterator {
    
    LinkedList<Iterator> list;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList();
        if (!v1.isEmpty()) list.add(v1.iterator());
        if (!v2.isEmpty()) list.add(v2.iterator());
    }
    
    public int next() {
        Iterator next = list.remove();
        int res = (Integer)next.next();
        if (next.hasNext()) list.add(next);
        return res;
    }
    
    public boolean hasNext() {
        return !list.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
