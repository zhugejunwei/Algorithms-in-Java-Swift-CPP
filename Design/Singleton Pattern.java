public class LFUCache {
    Node head = null;
    Map<Integer, Node> nodeMap;
    Map<Integer, Integer> valMap;
    int size;
    public LFUCache(int capacity) {
        nodeMap = new HashMap();
        valMap = new HashMap();
        size = capacity;
    }
    
    private void increaseFreq(int key) {
        Node node = nodeMap.get(key);
        node.keys.remove(key);
        if (node.next == null) {
            node.next = new Node(node.freq + 1);
            node.next.pre = node;
        } else if (node.next.freq != node.freq + 1) {
            Node tmp = new Node(node.freq + 1);
            tmp.next = node.next;
            node.next.pre = tmp;
            node.next = tmp;
            tmp.pre = node;
        }
        node.next.keys.add(key);
        nodeMap.put(key, node.next);
        if (node.keys.size() == 0) remove(node);
    }
    
    private void remove(Node node) {
        if (node == head) {
            head = head.next;
        } else if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
    }
    
    private void removeOld() {
        if (head == null) return;
        int key = head.keys.iterator().next();
        head.keys.remove(key);
        valMap.remove(key);
        nodeMap.remove(key);
        if (head.keys.isEmpty()) remove(head);
    }
    
    public int get(int key) {
        if (valMap.containsKey(key)) {
            increaseFreq(key);
            return valMap.get(key);
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (size == 0) return;
        if (valMap.put(key, value) != null) {
            increaseFreq(key);
        } else { // new key
            if (valMap.size() > size) removeOld();
            setHead(key); // set head, freq == 1
        }
    }
    
    private void setHead(int key) {
        if (head == null) {
            head = new Node(1);
        } else if (head.freq != 1) {
            Node tmp = new Node(1);
            tmp.next = head;
            head.pre = tmp;
            head = tmp;
        }
        head.keys.add(key);
        nodeMap.put(key, head);
    }
    
    class Node {
        int freq;
        Node pre, next;
        LinkedHashSet<Integer> keys;
        public Node(int freq) {
            this.freq = freq;
            keys = new LinkedHashSet();
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */
