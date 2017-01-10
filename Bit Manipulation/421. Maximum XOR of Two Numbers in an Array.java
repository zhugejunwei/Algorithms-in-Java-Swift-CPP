public class Solution {
    public int findMaximumXOR(int[] nums) {
        int mask = 0, max = 0;
        for (int i = 31; i >= 0; i--) {
            mask |= (1<<i);
            Set<Integer> set = new HashSet();
            for (int n : nums) {
                set.add(n & mask);
            }
            int tmp = (max | (1<<i));
            for (int prefix : set) {
                if (set.contains(prefix ^ tmp)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}

// Trie
public class Solution {
    class Trie {
        Trie[] next;
        public Trie() {
            next = new Trie[2];
        }
    }
    
    Trie root = new Trie();
    
    private void buildTrie(int val) {
        Trie node = root;
        for (int i = 31; i >= 0; i--) {
            int cur = (val >>> i) & 1;
            if (node.next[cur] == null) node.next[cur] = new Trie();
            node = node.next[cur];
        }
    }
    
    public int findMaximumXOR(int[] nums) {
        for (int num : nums) {
            buildTrie(num);
        }
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            Trie node = root;
            int curMax = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                // to see in that number's route down, whether there is always exist a bit that is thisbit ^ thatBit = 1
                // if exist, go one; if not,
                if (node.next[bit ^ 1] != null) {
                    node = node.next[bit ^ 1]; // if exist
                    curMax += (1 << i); // MSB
                } else {
                    // if not exist, it means their bit is same, so bit1 ^ bit2 == 0, we wont add this bit into the curMax
                    node = node.next[bit];
                }
            }
            max = Math.max(max, curMax);
        }
        return max;
    }
}
