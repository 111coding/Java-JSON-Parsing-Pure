import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JSONParserTest {
    private final String json = """
            {
                "userId":1,
                "firstName":"AAAAA",
                "lastName":"as23",
                "phoneNumber":"123456",
                "emailAddress":"AAAAA@test.com",
                "homepage":"https://amogg.tistory.com/1",
                "escapeChar1":"Nietzche said,\n\t \\"He who has a 'why' to live for can bear almost any 'how'.\\"",
                "escapeChar2":"Json Array => [].\\b",
                "unicode1":"\u0125",
                "unicode2":"\u0125 This String contains a unicode",
            }
            """;

    @Test
    void toMap() {
        JSONParser parser = new JSONParser(json);
        Map<String, Object> map = parser.toMap();

        assertEquals(1, map.get("userId"));
        assertEquals("AAAAA", map.get("firstName"));
        assertEquals("as23", map.get("lastName"));
        assertEquals("123456", map.get("phoneNumber"));
        assertEquals("AAAAA@test.com", map.get("emailAddress"));
        assertEquals("https://amogg.tistory.com/1", map.get("homepage"));
        assertEquals("Nietzche said,\n\t \"He who has a 'why' to live for can bear almost any 'how'.\"", String.valueOf(map.get("escapeChar1")));
        assertEquals("Json Array => []", map.get("escapeChar2"));
        assertEquals("\u0125", map.get("unicode1"));
        assertEquals("\u0125 This String contains a unicode", map.get("unicode2"));
    }
}