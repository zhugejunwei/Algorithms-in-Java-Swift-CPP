public class RandomizedCollection {
    List<Integer> list = new ArrayList(); // values
    Map<Integer, Set<Integer>> map = new HashMap(); // value:index
    Random rand = new Random();
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        
        if (!contain) map.put(val, new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);
        
        return contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        
        int idx = map.get(val).iterator().next();
        map.get(val).remove(idx);
        
        if (idx < list.size() - 1) {
            int lastVal = list.get(list.size() - 1);
            map.get(lastVal).remove(list.size() - 1);
            map.get(lastVal).add(idx);
            list.set(idx, lastVal);
        }
        list.remove(list.size() - 1);
        
        if (map.get(val).isEmpty()) map.remove(val);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
