import java.util.Scanner;

public class Cryptographer {

    private static final int ALPHABET_SIZE = 26;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String command = scanner.nextLine().trim();
            String[] parts = command.split(" -");

            String cipherType = parts[0].split(" ")[0];
            String text = getValue(parts, "text");
            text = text.trim();
            String result = "";

            switch (cipherType) {
                case "additive-cipher":
                    int key = Integer.parseInt(getValue(parts, "key"));
                    result = additiveCipher(text, key);
                    break;
                case "multiplicative-cipher":
                    key = Integer.parseInt(getValue(parts, "key"));
                    result = multiplicativeCipher(text, key);
                    break;
                case "affine-cipher":
                    int a = Integer.parseInt(getValue(parts, "a"));
                    int b = Integer.parseInt(getValue(parts, "b"));
                    result = affineCipher(text, a, b);
                    break;
                case "mapping-cipher":
                    String mapping = getValue(parts, "mapping");
                    result = mappingCipher(text, mapping);
                    break;
                default:
                    System.out.println("Unknown cipher type");
            }

            System.out.println(result.toUpperCase());
        }

        scanner.close();
    }

    private static String additiveCipher(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                encryptedText.append(c);
            } else {
                int originalPosition = Character.toUpperCase(c) - 'A';
                int newPosition = (originalPosition + key) % ALPHABET_SIZE;
                char newChar = (char) ('A' + newPosition);
                encryptedText.append(newChar);
            }
        }
        return encryptedText.toString();
    }

    private static String multiplicativeCipher(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                encryptedText.append(c);
            } else {
                int originalPosition = Character.toUpperCase(c) - 'A';
                int newPosition = (originalPosition * key) % ALPHABET_SIZE;
                char newChar = (char) ('A' + newPosition);
                encryptedText.append(newChar);
            }
        }
        return encryptedText.toString();
    }

    private static String affineCipher(String text, int a, int b) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                encryptedText.append(c);
            } else {
                int originalPosition = Character.toUpperCase(c) - 'A';
                int newPosition = (a * originalPosition + b) % ALPHABET_SIZE;
                char newChar = (char) ('A' + newPosition);
                encryptedText.append(newChar);
            }
        }
        return encryptedText.toString();
    }

    private static String mappingCipher(String text, String mapping) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                encryptedText.append(c);
            } else {
                int originalPosition = Character.toUpperCase(c) - 'A';
                char newChar = mapping.charAt(originalPosition);
                encryptedText.append(newChar);
            }
        }
        return encryptedText.toString();
    }

    private static String getValue(String[] parts, String key) {
        for (String part : parts) {
            String[] keyValue = part.split(" ", 2);
            if (keyValue[0].equals(key)) {
                return keyValue[1].replaceAll("\"", "").trim();
            }
        }
        return "";
    }
}

