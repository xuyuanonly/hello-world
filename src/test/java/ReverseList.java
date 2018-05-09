public class ReverseList {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        printList(n1);
        printList(reverseList(n1));

    }

    private static ListNode reverseList(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    private static void printList(ListNode head) {
        while(head != null){
            System.out.print(head.value);
            System.out.print(",");
            head = head.next;
        }
        System.out.println();
    }

}

class ListNode{
    int value;
    ListNode next;
    ListNode(int value){
        this.value = value;
    }
}