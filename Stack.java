package texteditor;

public class Stack {
    StackNode top;
    public Stack(){
        top = null;
    }
    public void clear(){
        top = null;
    }
    public void push(String value){
        StackNode newNode = new StackNode(value);
        if (top == null){
            top = newNode;
        }
        else{
            newNode.next = top;
            top = newNode;
        }
    }
    public String pop(){
        if (top == null){
            return null;
        }
        else{
            String value = top.value;
            top = top.next;
            return value;
        }
    }
    public class StackNode{
        String value;
        StackNode next;
        public StackNode(String value){
            this.value = value;
        }
    }
}
