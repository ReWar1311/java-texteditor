package texteditor;
import java.util.*;
import java.io.*;


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
        for (int i = 0; i < txt.length(); i++){
            this.text.next = new Node(txt.charAt(i));
            this.text.next.prev = this.text;
            this.text = this.text.next;
        }
        if(temp != null){
            this.text.next = temp;
        }
    }
    public void Delete(int k){
        Node temp = this.text.next;
        while(k >= 0){
            if (this.text.prev == null){
                break;
            }
            this.text = this.text.prev;
            this.text.next.remove();
            k--;
        }
        if (temp != null){
            this.text.next = temp;
        }
    }
    public void MoveCursor(int k){
        if (k > 0){
            while(k > 0){
                if (this.text.next != null){
                    this.text = this.text.next;
                    k--;
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
                }
                else{
                    break;
                }
            }
        }
    }
    public void Print(){
        Node temp = this.text;
        while(temp.prev != null){
            temp = temp.prev;
        }
        while(temp.next != null){
            System.out.print(temp.value);
            temp = temp.next;
        }
        System.out.print(temp.value);
        System.out.println();
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
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            if (parts[0].equals("Create")) {
                editor.Create(parts[1]);
            } else if (parts[0].equals("Write")) {
                editor.Write(line.substring(6));
            } else if (parts[0].equals("Delete")) {
                editor.Delete(Integer.parseInt(parts[1]));
            } else if (parts[0].equals("MoveCursor")) {
                editor.MoveCursor(Integer.parseInt(parts[1]));
            } else if (parts[0].equals("Print")) {
                editor.Print();
            } else if (parts[0].equals("Save")) {
                editor.Save();
            } else if (parts[0].equals("Exit")) {
                break;
            }
        }
    }


}

