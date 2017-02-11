/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    public class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            if (lists.length == 1) return lists[0];
            return helper(lists, 0, lists.length - 1);
        }
        
        private ListNode helper(ListNode[] lists, int l, int r) {
            if (l < r) {
                int mid = l + (r - l)/2;
                return merge(helper(lists, l, mid), helper(lists, mid + 1, r));
            }
            return lists[l];
        }
        
        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode res = new ListNode(0), tail = res;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    tail.next = new ListNode(l1.val);
                    l1 = l1.next;
                } else {
                    tail.next = new ListNode(l2.val);
                    l2 = l2.next;
                }
                tail = tail.next;
            }
            if (l1 != null) tail.next = l1;
            if (l2 != null) tail.next = l2;
            return res.next;
        }
    }
