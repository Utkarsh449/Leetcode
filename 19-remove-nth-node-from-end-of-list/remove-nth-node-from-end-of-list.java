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


    private int getSize(ListNode curr)
    {
        int count = 0;

        while(curr != null)
        {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        int size = getSize(head);
        ListNode curr = head;

        //Edge case
        if(size - n == 0)
            return curr.next;

        for(int i = 1;i<size-n;i++)
        {
            curr = curr.next;
        }

        //curr is at (size-n)th node
        ListNode temp = curr.next;
        curr.next = temp.next;
        temp = null;

        return head;
    }
}