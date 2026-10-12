import java.util.Scanner;

public class Login {
    String username;
    String password;
    String FirstName;
    String LastName;
    String cellNumber;
    Scanner sc = new Scanner(System.in);

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8
                && password.matches(".[A-Z].")
                && password.matches(".[0-9].")
                && password.matches(".[^a-zA-Z0-9].");
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.startsWith("+27") && cellNumber.length() == 12;
    }

    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username))
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        if (!checkPasswordComplexity(password))
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        if (!checkCellPhoneNumber(cellNumber))
            return "CellNumber is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        return "User registered successfully";
    }

    public boolean loginUser(String storedUsername, String storedPassword) {
        return storedUsername.equals(username) && storedPassword.equals(password);
    }

    public String returnLoginStatus(String storedUsername, String storedPassword) {
        if (loginUser(storedUsername, storedPassword)) {
            return "Welcome " + FirstName + ", " + LastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public void loginMethod() {
        String storedUsername;
        String storedPassword;
        while (true) {
            System.out.print("Enter your username: ");
            storedUsername = sc.nextLine().trim();
            System.out.print("Enter your password: ");
            storedPassword = sc.nextLine().trim();

            if (storedUsername.equals(username) && storedPassword.equals(password)) {
                System.out.println("Welcome " + FirstName + ", " + LastName + " it is great to see you again.");
                break;
            } else {
                System.out.println("Username or password incorrect, please try again.");
            }
        }
    }
}