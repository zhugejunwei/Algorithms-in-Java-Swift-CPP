public class LRUCache {
    Map<Integer, Node> map;
    Node head = null, tail = null;
    int cap;
    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap();
    }
    
    private void setHead(Node node) {
        if (head == node) return;
        if (tail == node) {
            tail = tail.pre;
        }
        node.pre.next = node.next;
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        node.next = head;
        head.pre = node;
        head = node;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            setHead(map.get(key));
            return head.val;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (cap == 0) return;
        if (map.containsKey(key)) {
            setHead(map.get(key));
            head.val = value;
        } else {
            Node tmp = new Node(key, value);
            tmp.next = head;
            if (head != null) head.pre = tmp;
            head = tmp;
            if (map.size() < cap) {
                if (tail == null) tail = tmp;
            } else {
                map.remove(tail.key);
                tail = tail.pre;
                tail.next = null;
            }
            map.put(key, tmp);
        }
    }
    
    class Node {
        int key, val;
        Node pre, next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
