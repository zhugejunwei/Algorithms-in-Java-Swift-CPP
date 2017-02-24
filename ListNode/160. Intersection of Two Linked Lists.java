public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode n1 = headA, n2 = headB;
        while (n1 != null && n2 != null && n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
            if (n1 == n2) break;
            if (n1 == null) n1 = headB;
            if (n2 == null) n2 = headA;
        }
        return n1;
    }
}

// solution_2
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode n1 = headA, n2 = headB;
        while (n1 != null && n2 != null) {
            if (n1 == n2) return n1;
            n1 = n1.next;
            n2 = n2.next;
            if (n1 == null && n2 == null) return null;
            if (n1 == null) n1 = headB;
            if (n2 == null) n2 = headA;
        }
        return null;
    }
}
