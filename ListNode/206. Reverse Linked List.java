// iteration
public class Solution {
    public ListNode reverseList(ListNode head) {
        // 31.9
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}

// recursion
public class Solution {
    public ListNode reverseList(ListNode head) {
        return reverseListInt(head, null);
    }
    
    private ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }
}
