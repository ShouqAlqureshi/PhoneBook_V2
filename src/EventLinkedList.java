public class EventLinkedList {
    Node head;

    Node current;

    public EventLinkedList() {
        this.head = null;
        this.current = null;
    }
    public void insertToSortedList(Event toBeSorted) {
        Node unsortedElement = new Node(toBeSorted);
        if (this.head == null || this.head.data.comparing(unsortedElement.data) == 1 ||  this.head.data.comparing(unsortedElement.data) == 0) {
            unsortedElement.next = this.head;
            this.current = this.head;
            this.head = unsortedElement;
        }
        else {
            this.current = this.head;
            while (this.current.next != null && this.head.data.comparing(unsortedElement.data) == -1 ){
                this.current = this.current.next;
            }
            unsortedElement.next = this.current.next;
            this.current.next = unsortedElement;
            this.current = this.current.next;
        }
    }

}//class