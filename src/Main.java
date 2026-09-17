import java.util.Scanner;
void main() {
    String username;
    String password;
    String cellNumber;
    String firstName;
    String lastName;
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter your first Name: ");
    String FirstName = sc.nextLine();
    System.out.print("Enter your last Name: ");
    String LastName = sc.nextLine();
    System.out.println("Enter your user name: ");
    username = sc.nextLine();
    while (true) {
        if (username.length() <= 8 && username.contains("_")) {
            System.out.println("username sussesfully saved");
            break;
        } else {
            System.out.println("Username wronglly formated");
            username = sc.nextLine();
        }
    }
    while (true) {
        System.out.println("Enter password: ");
        password = sc.nextLine();
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            }
            if (!Character.isDigit(ch)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        if (password.length() >= 8 && hasCapital && hasNumber && hasSpecial) {
            System.out.println("Password successfully captured");
            break;
        } else {
            System.out.println("Password is not correctly formatted please ensure that the password contains at least " +
                    "eight characters, a capital letter, a number and a special character.");
        }
    }

    System.out.println("Enter your phone number:");
    cellNumber = sc.nextLine();
    while (true) {
        if (cellNumber.startsWith("+27") && cellNumber.length() <= 13) {
            System.out.println("cellNumber sussessfully saved");
            break;
        } else {
            System.out.println("cellNumber wrongfully formated");
            cellNumber = sc.nextLine();
        }

    }
    System.out.println("---------------------Registration successful-------------");
    // login"
    System.out.println("Please enter your login details");
    String storedUsername ;
    String storedPassword ;
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













