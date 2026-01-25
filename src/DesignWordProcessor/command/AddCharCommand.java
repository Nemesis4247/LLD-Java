package DesignWordProcessor.command;

import DesignWordProcessor.Document;
import DesignWordProcessor.DocumentCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCharCommand implements ICommand {
    int row;
    int column;
    DocumentCharacter character;
    @Override
    public void execute(Document document) {

    }

    @Override
    public void undo(Document document) {

    }
}
