public class Main {
    Map<String, TreeMap<Integer, String>> map;
    public void TimeTravelingHashTable() {
        this.map = new HashMap();
    }

    public void insert(String key, String value, int time) {
        if (!map.containsKey(key))
            map.put(key, new TreeMap());

        map.get(key).put(time, value);
    }
    
    // 对于key，获得某一时刻t之前（或相等）离t时间上最近的value
    public String get(String key, int time) {
        TreeMap<Integer, String> treemap = map.get(key);
        int t = treemap.floorKey(time);
        return treemap.get(t);
    }
    
    // returns value associated with key at latest time
    public String get(String key) {
        TreeMap<Integer, String> treemap = map.get(key);
        int t = treemap.lastKey();
        return treemap.get(t);
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        m.TimeTravelingHashTable();
        m.insert("k1", "v1", 10);
        System.out.println(m.get("k1")); // v1
        System.out.println(m.get("k1", 11)); // v1
        m.insert("k1", "v2", 20);
        System.out.println(m.get("k1", 15)); // v1
        System.out.println(m.get("k1", 11)); // v1
        System.out.println(m.get("k1", 21)); // v2
    }
}
