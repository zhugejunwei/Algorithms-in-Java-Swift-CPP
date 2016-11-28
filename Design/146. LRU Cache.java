class Node {
    int key;
    int val;
    Node pre;
    Node next;
    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
public class LRUCache {
    int count;
    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap();
        head = null;
        tail = null;
        this.count = 0;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            setHead(cur);
            return cur.val;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (!map.containsKey(key)) {
            Node tmp = new Node(key, value);
            map.put(key, tmp);
            tmp.next = head;
            if (head != null) head.pre = tmp;
            head = tmp;
            if (count < capacity) {
                if (tail == null) tail = tmp;
                count++;
            } else {
                map.remove(tail.key);
                tail = tail.pre;
                tail.next = null;
            }
        } else {
            setHead(map.get(key));
            head.val = value;
        }
    }
    
    private void setHead(Node node) {
        if (count == 1) return;
        if (node == head) return;
        if (node == tail) {
            tail = node.pre;
        }
        node.pre.next = node.next;
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        node.next = head;
        node.pre = null;
        head.pre = node;
        head = node;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */
