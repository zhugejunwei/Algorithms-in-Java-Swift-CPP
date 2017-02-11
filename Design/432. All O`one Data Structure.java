public class AllOne {
    Map<String, Node> map;
    Node head, tail;
    // head - min
    // tail - max
    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap();
        head = tail = null;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (map.containsKey(key)) {
            // get old node
            Node node = map.get(key);
            // get old val
            int val = node.val;
            // remove old key in the old node
            node.keys.remove(key);
            // add new node with new val
            if (node.next == null || node.next.val != val + 1) {
                Node newNode = new Node(val + 1);
                newNode.pre = node;
                newNode.next = node.next;
                node.next = newNode;
                if (newNode.next != null) newNode.next.pre = newNode;
            }
            // add new key to the next node
            node.next.keys.add(key);
            // update node
            map.put(key, node.next);
            
            // udate head
            if (node.keys.isEmpty()) {
                if (node == head) head = node.next;
                node.next.pre = node.pre;
                if (node.pre != null) node.pre.next = node.next;
            }
            // update tail
            if (tail == node) tail = node.next;
        } else {
            if (head == null || head.val > 1) {
                Node node = new Node(1);
                node.next = head;
                if (head != null) head.pre = node;
                head = node;
                if (tail == null) tail = node;
            }
            head.keys.add(key);
            map.put(key, head);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (!map.containsKey(key)) return;
        // get old node
        Node node = map.get(key);
        // get old val
        int val = node.val;
        // remove old val from old node
        node.keys.remove(key);
        // remove old key
        map.remove(key);
        // if it's head, update head and tail
        if (val == 1) {
            if (node.keys.isEmpty()) {
                if (head == node) head = node.next;
                if (head != null) head.pre = null;
                if (tail == node) tail = null;
            }
        } else {
            // add new node at pre position
            if (node.pre == null || node.pre.val != val - 1) {
                Node newNode = new Node(val - 1);
                newNode.next = node;
                newNode.pre = node.pre;
                node.pre = newNode;
                if (newNode.pre != null) newNode.pre.next = newNode;
            }
            // add new key to the newNode or pre node
            node.pre.keys.add(key);
            // update node
            map.put(key, node.pre);
            
            // update tail
            if (node.keys.isEmpty()) {
                if (tail == node) tail = node.pre;
                node.pre.next = node.next;
                if (node.next != null) node.next.pre = node.pre;
            }
            // update head
            if (head == node) head = node.pre;
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail == null ? "" : tail.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head == null ? "" : head.keys.iterator().next();
    }
    
    class Node {
        int val;
        LinkedHashSet<String> keys;
        Node next, pre;
        public Node(int val) {
            this.val = val;
            keys = new LinkedHashSet();
            next = pre = null;
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
