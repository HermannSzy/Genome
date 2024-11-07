import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Genom {
    public static boolean error(String input) {
        String output = "";
        if (input == null || input.isEmpty()) {
            output += "Enter another string.";
        } else {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c != 'a' && c != 't' && c != 'c' && c != 'g') {
                    output += "Invalid characters found in gene sequence.\n";
                    break;
                }
            }
//            if (!input.matches("[atgcATGC]+"))
//                output += "Invalid characters found in gene sequence.\n";
            if (input.length() % 3 != 0)
                output += "You have to enter genes in triplets.\n";
            }
        if (output.length() != 0) {
            System.out.println(output);
            return true;
        }
        else
            return false;
    }

    public static Object[] find_gene(String input, int index) {
        String output = "";
        for (int i = index; i < input.length() - 2; i += 3) {
            char c1 = input.charAt(i);
            char c2 = input.charAt(i + 1);
            char c3 = input.charAt(i + 2);
            if (c1 == 'a' && c2 == 't' && c3 == 'g') {
                output += "" + c1 + c2 + c3;
                for (int j = i + 3; j < input.length() - 2; j += 3) {
                    char c4 = input.charAt(j);
                    char c5 = input.charAt(j + 1);
                    char c6 = input.charAt(j + 2);
                    output += "" + c4 + c5 + c6;
                    if ((c4 == 't' && c5 == 'a' && (c6 == 'a' || c6 == 'g')) || (c4 == 't' && c5 == 'g' && c6 == 'a')) {
                        return new Object[]{output, j + 3};
                    }
                }
            }
        }
        return new Object[]{"", input.length()};
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Gib den Pfad zur zu prüfenden Datei an!");
            return;
        }

        String gesamtesZeichenkette = "";
        try {
            String dateiPfad = args[0];
            gesamtesZeichenkette = new String(Files.readAllBytes(Paths.get(dateiPfad)), StandardCharsets.UTF_8);
            System.out.println("Datei erfolgreich eingelesen!");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (!error(gesamtesZeichenkette)) {
            int index = 0, counter = 0;
            int len = gesamtesZeichenkette.length();
            while (index < len) {
                Object[] result = find_gene(gesamtesZeichenkette, index);
                index = (int) result[1];
                if (index != len) {
                    String gene = (String) result[0];
                    counter++;
                    System.out.println("Gen #" + counter + ":");
                    System.out.println(gene);
                    System.out.println("An Stelle: " + index);
                }
            }
        }
    }
}
