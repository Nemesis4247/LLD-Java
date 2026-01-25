package DesignWordProcessor.command;

import DesignWordProcessor.Document;

public interface ICommand {
    void execute(Document document);
    void undo(Document document);
}
