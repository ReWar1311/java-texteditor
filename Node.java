package texteditor;

public class Node {
    char value;
    Node next;
    Node prev;
    public Node(char value) {
        this.value = value;
    }
    public Node(){
        
    }

    public void remove(){
            if (next != null) {
                next.prev = this.prev;
            }
            if (prev != null) {
                prev.next = this.next;
            }
        
    }

}
