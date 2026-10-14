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
        if (password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        // Source: IIEVC PROG5121 - South African number format validation
        return cellNumber.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username, String password, String cellNumber) {
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;

        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
        return "Username successfully captured\nPassword successfully captured\nCell phone number successfully added.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
    }

    public String returnLoginStatus() {
        return "Welcome " + FirstName + " " + LastName + " it is great to see you again.";
    }

    public void loginMethod() {
        while (true) {
            System.out.print("Enter your username: ");
            String enteredUsername = sc.nextLine().trim();
            System.out.print("Enter your password: ");
            String enteredPassword = sc.nextLine().trim();

            if (loginUser(enteredUsername, enteredPassword)) {
                System.out.println(returnLoginStatus());
                break;
            } else {
                System.out.println("Username or password incorrect, please try again.");
            }
        }
    }
}