/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = head.next;
        ListNode curr = even.next;

        while(curr != null){
            odd.next = curr;
            curr = curr.next;
            even.next = curr;

            if(curr != null)
            curr = curr.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}