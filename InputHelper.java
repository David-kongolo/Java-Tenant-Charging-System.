import java.util.Scanner;

public class InputHelper {

    private Scanner scanner;

    public InputHelper() {
        scanner = new Scanner(System.in);
    }

    public String readNonBlank(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                "Input cannot be blank. Please try again."
            );
        }
    }

      public int readPositiveInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(input);

                if (number > 0) {
                    return number;
                }

                System.out.println(
                    "Please enter a number greater than zero."
                );

            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid number. Please try again."
                );
            }
        }
    }

     public String readId(String message) {
        return readNonBlank(message).toUpperCase();
    }

    public boolean readYesNo(String message) {
        while (true) {
            System.out.print(message);

            String input = scanner.nextLine()
                    .trim()
                    .toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                return true;
            }

            if (input.equals("no") || input.equals("n")) {
                return false;
            }

            System.out.println(
                "Please enter yes or no."
            );
        }
    }
}
