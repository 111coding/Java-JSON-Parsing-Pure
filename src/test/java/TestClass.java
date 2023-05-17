import org.junit.jupiter.api.Test;

public class TestClass {
    @Test
    void printHexCodeOfChar() {
        System.out.println("\n<<< A-Z Hex Code >>>\n");
        for (int i = 'A'; i <= 'Z'; i++) {
            System.out.printf("%c : 0x%02X\n", (char) i, i);
        }

        System.out.println("\n<<< a-z Hex Code >>>\n");
        for (int i = 'a'; i <= 'z'; i++) {
            System.out.printf("%c : 0x%02X\n", (char) i, i);
        }

        System.out.println("\n<<< 0-9 Hex Code >>>\n");
        for (int i = '0'; i <= '9'; i++) {
            System.out.printf("%c : 0x%02X\n", (char) i, i);
        }
    }

    @Test
    void printEscapeChar() {
        System.out.println("\n<<< Print '\\n' >>>\n");
        System.out.println("This is \nnew line check");

        System.out.println("\n<<< Print '\\t' >>>\n");
        System.out.println("This is \ttab check");

        System.out.println("\n<<< Print '\\r' >>>\n");
        System.out.println("This is \rreturn check");

        System.out.println("\n<<< Print '\\b' >>>\n");
        System.out.println("This is \breturn check");

        System.out.println("\n<<< Print '\\f' >>>\n");
        System.out.println("This is \freturn check");

        System.out.println("\n<<< Print '\\u0123' >>>\n");
        System.out.println("This is \u0123 check");

        System.out.println("\n<<< Print '\\uffff' >>>\n");
        System.out.println("This is \uffff check");
    }

    @Test
    void testHexTextToChar() {
        System.out.printf("\n<<< Print \\u%04X >>>\n", 0x0123);
        System.out.println('\u0125');
        StringBuffer sb = new StringBuffer();
        sb.append('0');
        sb.append('1');
        sb.append('2');
        sb.append('5');
        System.out.println(sb);
        System.out.println((char)Integer.parseInt(sb.toString(), 16));
        StringBuffer value = new StringBuffer();
        value.append((char)Integer.parseInt(sb.toString(), 16));
        System.out.println(value);
    }
}
