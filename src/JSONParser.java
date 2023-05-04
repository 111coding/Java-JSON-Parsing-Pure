import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class JSONParser {

    public JSONParser(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    private String jsonStr;
    private int cursor = -1;

    public HashMap<String, Object> toMap() {
        cursor = -1;
        return parseObject();
    }

    private boolean next() {
        if (cursor == jsonStr.length() - 1) {
            return false;
        }
        cursor++;
        return true;
    }

    private boolean previous() {
        if (cursor == 0) {
            return false;
        }
        cursor--;
        return true;
    }

    private char current() {
        return jsonStr.charAt(cursor);
    }

    private Number parseNumber() {
        StringBuffer numValue = new StringBuffer();
        char c = current();
        boolean hasDecimals = false;
        while (isNumber(c) || c == '.') {
            if (c == '.') {
                hasDecimals = true;
            }
            numValue.append(c);
            next();
            c = current();
        }
        previous();
        if (hasDecimals) {
            return Float.parseFloat(numValue.toString());
        } else {
            return Integer.parseInt(numValue.toString());
        }
    }

    private String parseString() {
        StringBuffer value = new StringBuffer();
        while (next()) {
            char c = current();
            if (c == '"') {
                break;
            }
            value.append(c);
        }
        return value.toString();
    }

    private Object parseList() {
        List<Object> values =  new ArrayList<>();
        
        while (next()) {
            char c = current();
            if(c == '"'){
                values.add(parseString());
            }else if(isNumber(c)){
                values.add(parseNumber());
            }else if(c == '{'){
                previous();
                values.add(parseObject());
            }else if(c == '['){
                values.add(parseList());
            }else if(c == ']'){
                return values;
            }
            
        }
        return null;
    }

    private HashMap<String, Object> parseObject() {
        HashMap<String, Object> hashMap = new HashMap<>();
        StringBuffer key = null;
        int braceCnt = 0;
        boolean isParsingValue = false;
        boolean isParsingKey = false;

        while (next()) {
            char c = current();
            if (isParsingValue) {
                if (c == ' ') {
                    continue;
                }
                if (isNumber(c)) {
                    // NumberString numberString = parseNumber();
                    hashMap.put(key.toString(), parseNumber());
                    isParsingValue = false;
                    continue;
                } else if (c == '"') {
                    hashMap.put(key.toString(), parseString());
                    isParsingValue = false;
                    continue;
                } else if (c == '[') {
                    hashMap.put(key.toString(), parseList());
                    isParsingValue = false;
                    continue;
                } else if (c == '{') {
                    previous();
                    hashMap.put(key.toString(), parseObject());
                    isParsingValue = false;
                    continue;
                }
            }

            if (c == '"') {
                isParsingKey = !isParsingKey;
                if (isParsingKey) {
                    key = new StringBuffer();
                }
                continue;
            }

            if (!isParsingKey) {
                if (c == '{') {
                    // isParsingValue = false : braceCnt++
                    braceCnt++;
                    continue;
                } else if (c == '}') {
                    if (--braceCnt == 0) {
                        return hashMap;
                    }
                } else if (c == ':') {
                    isParsingValue = true;
                    continue;
                }
            }

            if (c == '"') {
                isParsingKey = !isParsingKey;
                if (isParsingKey) {
                    key = new StringBuffer();
                }
                continue;
            }

            if (isParsingKey) {
                key.append(c);
            }

        }

        return hashMap;

    }

    private boolean isNumber(char c) {
        return 0x31 <= c && c <= 0x39;
    }

}
