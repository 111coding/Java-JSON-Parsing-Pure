import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        for (int num = 1; num <= 7; num++) {
            String pathStr = "src/s" + num + ".json";

            Path filePath = Path.of(pathStr);
            byte[] bytes = Files.readAllBytes(Paths.get(filePath.toUri()));
            String jsonString = new String(bytes);

            JSONParser parse = new JSONParser(jsonString);
            HashMap<String, Object> result = parse.toMap();

            System.out.println("\n<<< " + pathStr + " >>>\n");
            printHashMap(result, 0);
            System.out.println();
        }
    }

    static void printHashMap(HashMap<String, ?> hashMap, int depth) {
        for (String key : hashMap.keySet()) {
            Object value = hashMap.get(key);
            if (value instanceof HashMap) {
                System.out.println("\t".repeat(depth) + key + " :");
                printHashMap((HashMap<String, ?>) value, depth + 1);
            } else if (value instanceof List) {
                System.out.println("\t".repeat(depth) + key + " :");
                List<?> list = (List<?>) value;
                printList(list, depth + 1);
            } else {
                System.out.println("\t".repeat(depth) + key + " : " + value);
            }
        }
    }

    static void printList(List<?> list, int depth) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof HashMap) {
                System.out.println("\t".repeat(depth) + "list[" + i + "] ");
                printHashMap((HashMap<String, ?>) list.get(i), depth + 1);
            } else if (list.get(i) instanceof List) {
                System.out.println("\t".repeat(depth) + "list[" + i + "] ");
                printList((List<?>) list.get(i), depth + 1);
            } else {
                System.out.println("\t".repeat(depth) + "list[" + i + "] " + list.get(i));
            }
        }

    }

}
