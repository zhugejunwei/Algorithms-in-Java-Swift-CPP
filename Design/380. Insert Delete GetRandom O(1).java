public class RandomizedSet {
    List<Integer> list; // val
    Map<Integer, Integer> map; // val:idx
    Random rand;
    int size;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList();
        map = new HashMap();
        rand = new Random();
        size = 0;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, ++size);
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int idx = map.get(val);
        list.set(idx - 1, list.get(size - 1));
        map.put(list.get(size - 1), idx);
        list.remove(--size);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int randIdx = rand.nextInt(size);
        return list.get(randIdx);
    }
}
