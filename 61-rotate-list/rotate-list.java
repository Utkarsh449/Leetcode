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
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null || k==0){
            return head;
        }
        ListNode dummy=new ListNode(0);
        ListNode temp=head;
        int count=1;
        dummy.next=head;
        ListNode tail=head;
        while(tail.next!=null){
            tail=tail.next;
            count++;
        }
        if(k%count==0){
            return head;
        }
        ListNode slow=dummy;
        ListNode fast=dummy;
        for(int i=0;i<k%count;i++){
            fast=fast.next;
        }
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
        ListNode newhead=slow.next;
        tail.next=temp;
        slow.next=null;
        return newhead;
    }
}