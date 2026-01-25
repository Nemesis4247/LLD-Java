package DesignWordProcessor;

import DesignWordProcessor.command.AddCharCommand;
import DesignWordProcessor.command.ICommand;

import java.util.Deque;

public class DocumentManager {
    Deque<ICommand> commandHistory;
    Deque<ICommand> redoStack;
    int historySize;
    Document document;

    public void addCharacter(int row, int column, DocumentCharacter documentCharacter) {
        AddCharCommand command = new AddCharCommand(row, column, documentCharacter);
        command.execute(document);
        commandHistory.addLast(command);
        while (commandHistory.size() >= historySize) {
            commandHistory.pollFirst();
        }
    }

    public void undo() {
        if (!commandHistory.isEmpty()) {
            ICommand command = commandHistory.pollLast();
            command.undo(document);
            redoStack.addLast(command);
            while (redoStack.size() >= historySize) {
                redoStack.pollFirst();
            }
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            ICommand command = redoStack.pollLast();
            command.execute(document);
            commandHistory.addLast(command);
            while (commandHistory.size() >= historySize) {
                commandHistory.pollFirst();
            }
        }
    }
}
