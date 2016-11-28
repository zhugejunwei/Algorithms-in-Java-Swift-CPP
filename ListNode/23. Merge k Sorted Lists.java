/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }
    
    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (start < end) {
            int mid = start + (end - start) / 2;
            ListNode left = mergeKLists(lists, start, mid);
            ListNode right = mergeKLists(lists, mid + 1, end);
            return mergeTwoLists(left, right);
        } else {
            return null;
        }
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode head = null;
        ListNode tail = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (tail == null) tail = l1; else tail.next = l1;
                if (head == null) head = tail; else tail = tail.next;
                l1 = l1.next;
            } else {
                if (tail == null) tail = l2; else tail.next = l2;
                if (head == null) head = tail; else tail = tail.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) tail.next = l2;
        else tail.next = l1;
        return head;
    }
}
