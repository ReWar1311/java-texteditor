package texteditor;

import java.io.*;
import java.util.Scanner;

class TextEditor {
    private Node text;
    public String filename;
    public int version = 1;

    public TextEditor() {
        text = new Node();
    }
    public void Create(String filename){
        this.filename = filename;
        System.out.println("Created file: " + filename);
    }
    public void Write(String txt){
        Node temp = this.text.next;
        Node current = this.text;
        for (int i = 0; i < txt.length(); i++){
            Node newNode = new Node(txt.charAt(i));
            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }
        if(temp != null){
            current.next = temp;
            temp.prev = current;
        }
        this.text = current;
    }
    public char Top(){
        return this.text.value;
    }
    public String Delete(int k) {
        String deleted = "";
        while (k>0){
            if (this.text.prev == null){
                break;
            }
            deleted = this.text.value + deleted;
            this.text = this.text.prev;
            this.text.next.remove();
            k--;
        }
        return deleted;
    }
    public int MoveCursor(int k){
        int moved = 0;
        if (k > 0){
            while(k > 0){
                if (this.text.next != null){
                    this.text = this.text.next;
                    k--;
                    moved++;
                }
                else{
                    break;
                }
            }
        }
        else{
            while(k < 0){
                if (this.text.prev != null){
                    this.text = this.text.prev;
                    k++;
                    moved--;
                }
                else{
                    break;
                }
            }
        }
        return moved;
    }
    public void Print(){
        Node temp = this.text;
        Node thisText = this.text;
        while(temp.prev != null){
            temp = temp.prev;
        }
        while(temp.next != null){
            System.out.print(temp.value);
            if (temp == thisText){
                System.out.print("|");
            }
            temp = temp.next;
        }
        System.out.print(temp.value);
        if (temp == thisText){
            System.out.print("|");
        }
        // System.out.println();
        // System.out.println("Reverse: ");
        // while(temp.prev != null){
        //     System.out.print(temp.value);
        //     temp = temp.prev;
        // }
        // System.out.println();
    }
    public void Save(){
        File file = new File(this.filename+"--v"+version+".txt");
        try {
            FileWriter writer = new FileWriter(file);
            Node temp = this.text;
            if (temp != null) {
                while(temp.prev != null){
                    temp = temp.prev;
                }
                while(temp.next != null){
                    if (temp.value != '\0') {
                        writer.write(temp.value);
                    }
                    temp = temp.next;
                }
                if (temp.value != '\0') {
                    writer.write(temp.value);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved file: " + this.filename+"--v"+version+".txt");
        version++;
    }
    public static void handler(TextEditor editor, Stack undoStack, String line) {
        String[] parts = line.split(" ");
        if (parts[0].equals("Write")) {
            String text = line.substring(6);
            editor.Write(text);
            undoStack.push("Delete " + String.valueOf(text.length()));
        } else if (parts[0].equals("Delete")) {
            int k = Integer.parseInt(parts[1]);
            String deleted = editor.Delete(k);
            undoStack.push("Write " + deleted);
        } else if (parts[0].equals("MoveCursor")) {
            int k = Integer.parseInt(parts[1]);
            int moved = editor.MoveCursor(k);
            if (moved != 0) {  // Only push to stack if cursor actually moved
                undoStack.push("MoveCursor " + String.valueOf(-moved));
            }
        } else if (parts[0].equals("Print")) {
            editor.Print();
        } else if (parts[0].equals("Save")) {
            editor.Save();
        }
        else if (parts[0].equals("Top")) {
            System.out.println(editor.Top());
        }
    }
    
    
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Stack undoStack = new Stack();
        Stack redoStack = new Stack();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file name: ");
        String fileString = sc.nextLine();
        editor.Create(fileString);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("Undo")) {
                String command = undoStack.pop();
                System.out.println("Undoing, Processing Command: " + command);
                if (command != null) {
                    handler(editor, redoStack, command);
                }
            }
            
            else if (line.equals("Redo")) {
                String command = redoStack.pop();
                System.out.println("Redoing, Processing Command: " + command);
                if (command != null) {
                    handler(editor, undoStack, command);
                }
            }
            else if (line.equals("Exit")) {
                sc.close();
                break;
            }else{
                redoStack.clear();
                handler(editor, undoStack, line);
            }
            // editor.Print();
            System.out.println();
        }
    }


}

