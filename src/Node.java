public class Node {

     Event data;

    Node next;

    public Node(Event data) {
        this.data = data;
        this.next = null;
    }

    public Event retrieve () {
        return data;
    }

}//class}