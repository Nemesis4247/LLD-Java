package DesignWordProcessor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DocumentCharacter implements ILetter {
    char c;
    FontType fontType;
    int fontSize;
    boolean isBold;
    boolean isUnderline;
    boolean isItalic;

    @Override
    public void display() {
        System.out.print(c);
    }
}
