# TextEditor

## Overview
The `TextEditor` package provides a simple text editor implementation in Java. It supports basic text editing operations like writing, deleting, moving the cursor, undo/redo functionality, and saving files.

## Features
- **Create a file**: Initializes a new file.
- **Write text**: Adds text to the editor.
- **Delete text**: Removes the last `k` characters.
- **Move cursor**: Moves the cursor left or right.
- **Print text**: Displays the current content of the text editor.
- **Save file**: Saves the current text to a versioned file.
- **Undo/Redo**: Supports undoing and redoing operations.

## Installation & Setup
1. Clone or download the repository.
2. Navigate to the project directory.
3. Compile the Java files using:
   ```sh
   javac texteditor/*.java
   ```
4. Run the program using:
   ```sh
   java texteditor.TextEditor
   ```

## Usage
1. When prompted, enter the file name.
2. Uncomment the `// editor.Print()`  to print the text with the cursor position after each operation.
3. Use the following commands to interact with the editor:
   
   | Command       | Description |
   |--------------|-------------|
   | `Write <text>` | Adds text to the editor. |
   | `Delete <k>` | Deletes the last `k` characters. |
   | `MoveCursor <k>` | Moves the cursor by `k` positions (positive for right, negative for left). |
   | `Print` | Displays the text with the cursor position. |
   | `Save` | Saves the current content to a file. |
   | `Undo` | Undoes the last operation. |
   | `Redo` | Redoes the last undone operation. |
   | `Exit` | Exits the text editor. |

## Example Usage
```sh
Enter the file name: myfile
Write Hello
Write World
MoveCursor -5
Delete 3
Save
Exit
```

## Implementation Details
### Classes and Methods
- **TextEditor**
  - `Create(String filename)`: Initializes a new file.
  - `Write(String txt)`: Writes text to the editor.
  - `Delete(int k)`: Deletes `k` characters.
  - `MoveCursor(int k)`: Moves the cursor.
  - `Print()`: Prints the current content.
  - `Save()`: Saves the file with versioning.
  - `Top()`: Returns the character at the cursor.
  - `handler(TextEditor editor, Stack undoStack, String line)`: Processes user commands.

## Notes
- Each save creates a new versioned file: `<filename>--v<version>.txt`
- Undo and redo functionality is implemented using stacks.
- Cursor movement and deletion update the linked list structure dynamically.



