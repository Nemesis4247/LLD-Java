package DesignWordProcessor;

import java.util.HashMap;
import java.util.Map;

public class DocumentCharacterFactory {
    Map<String, DocumentCharacter> flyWeightMap;

    public DocumentCharacterFactory() {
        flyWeightMap = new HashMap<>();
    }

    public DocumentCharacter getDocChar(char c, FontType fontType, int fontSize,
                                               boolean isBold, boolean isUnderline, boolean isItalic) {
        String key = getKey(c, fontType, fontSize, isBold, isUnderline, isItalic);
        flyWeightMap.putIfAbsent(key, new DocumentCharacter(c, fontType, fontSize, isBold, isUnderline, isItalic));
        return flyWeightMap.get(key);
    }

    private String getKey(char c, FontType fontType, int fontSize,
                          boolean isBold, boolean isUnderline, boolean isItalic) {
        String ans = c + "-" + fontType + "-" + fontSize;
        if (isBold)
            ans += "-" + "b";
        if (isUnderline)
            ans += "-" + "u";
        if (isItalic)
            ans += "-" + "i";
        return ans;
    }
}
