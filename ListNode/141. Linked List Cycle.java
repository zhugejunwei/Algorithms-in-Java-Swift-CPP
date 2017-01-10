/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return false;
        ListNode first = head.next.next;
        ListNode second = head.next;
        while (first != null && first.next != null) {
            first = first.next.next;
            second = second.next;
            if (first == second) return true;
        }
        return false;
    }
}
