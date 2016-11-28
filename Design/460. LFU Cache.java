public class LFUCache {
    class Node {
        int count;
        Node pre, next;
        LinkedHashSet<Integer> keys;
        public Node(int count) {
            this.count = count;
            keys = new LinkedHashSet();
            pre = next = null;
        }
    }
    
    int capacity; // capacity
    Map<Integer, Node> nodeMap;
    Map<Integer, Integer> valMap;
    Node head = null; // smallest count
    
    public LFUCache(int capacity) {
        nodeMap = new HashMap();
        valMap = new HashMap();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (valMap.containsKey(key)) {
            increaseCount(key);
            return valMap.get(key);
        }
        return -1;
    }
    
    private void increaseCount(int key) {
        Node node = nodeMap.get(key);
        node.keys.remove(key);
        if (node.next == null) { // there is no node with larger count, then create one
            node.next = new Node(node.count + 1);
            node.next.pre = node;
            node.next.keys.add(key);
        } else if (node.next.count == node.count + 1) {
            node.next.keys.add(key);
        } else {
            Node tmp = new Node(node.count + 1);
            tmp.keys.add(key);
            tmp.next = node.next;
            node.next.pre = tmp;
            node.next = tmp;
            tmp.pre = node;
        }
        nodeMap.put(key, node.next);
        if (node.keys.size() == 0) remove(node);
    }
    
    private void remove(Node node) {
        if (head == node) {
            head = node.next;
        } else {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
    }
    
    public void set(int key, int value) {
        if (capacity == 0) return;
        if (valMap.put(key, value) != null) // if it's not a new key, set the new value
            increaseCount(key); // and we just need to increase it's count
        else {
            // if it's a new key
            if (valMap.size() == capacity + 1) // if the size is full, remove old one
                removeOld();
            // and then add the new key set to the head
            addToHead(key);
        }
    }
    
    private void addToHead(int key) {
        if (head == null) {
            head = new Node(1);
            head.keys.add(key);
        } else if (head.count == 1) {
            head.keys.add(key);
        } else {
            Node node = new Node(1);
            node.keys.add(key);
            head.pre = node;
            node.next = head;
            head = node;
        }
        nodeMap.put(key, head);
    }
    
    private void removeOld() {
        if (head == null) return;
        int oldKey = head.keys.iterator().next(); // the first(old) key int the head node
        head.keys.remove(oldKey);
        if (head.keys.isEmpty()) remove(head);
        nodeMap.remove(oldKey);
        valMap.remove(oldKey);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */
