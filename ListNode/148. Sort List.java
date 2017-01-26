/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode h2 = sortList(slow.next);
        slow.next = null;
        
        return merge(sortList(head), h2);
    }
    
    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        ListNode n = newHead;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                n.next = h1;
                h1 = h1.next;
            } else {
                n.next = h2;
                h2 = h2.next;
            }
            n = n.next;
        }
        if (h1 != null) n.next = h1;
        if (h2 != null) n.next = h2;
        return newHead.next;
    }
}
