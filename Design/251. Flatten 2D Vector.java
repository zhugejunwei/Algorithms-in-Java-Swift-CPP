public class Vector2D implements Iterator<Integer> {
    Deque<Iterator<Integer>> stack;
    public Vector2D(List<List<Integer>> vec2d) {
        stack = new ArrayDeque();
        for (int i = vec2d.size() - 1; i >= 0; i--) {
            List<Integer> list = vec2d.get(i);
            if (list.iterator().hasNext()) {
                stack.push(list.iterator());
            }
        }
    }
    
    @Override
    public Integer next() {
        Iterator<Integer> i = stack.pop();
        int res = i.next();
        if (i.hasNext())
            stack.push(i);
        return res;
    }
    
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
