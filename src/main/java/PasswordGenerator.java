import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class PasswordGenerator {

    private static final int DEFAULT_PASSWORD_LENGTH = 10;
    public static final String PASSWORD_REQUEST = "Your password is: ";
    public static final String PASSWORD_LENGTH = "Enter password length (default - " + DEFAULT_PASSWORD_LENGTH + ") " ;
    public static final String ERROR_MESSAGE = "Password length must be positive";
    public static final String INVALID_LENGTH_MESSAGE = "Invalid password length";

    public static void main(String[] args) {
        int length = readPasswordLength();
        String password = generatePassword(length);
        System.out.println(PASSWORD_REQUEST + password);
    }

    public static int readPasswordLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(PASSWORD_LENGTH);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return DEFAULT_PASSWORD_LENGTH;
        }
        try {
            int length = Integer.parseInt(input);
            if (length < 1) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            return length;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_LENGTH_MESSAGE);
        }
    }

    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String password = encoder.encodeToString(bytes);
        return password.substring(0, length);
    }
}
