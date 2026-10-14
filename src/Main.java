import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login login = new Login();

        System.out.print("Enter your first Name: ");
        login.FirstName = sc.nextLine().trim();
        System.out.print("Enter your last Name: ");
        login.LastName = sc.nextLine().trim();

        String username;
        while (true) {
            System.out.print("Enter your user name: ");
            username = sc.nextLine().trim();
            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured");
                break;
            } else {
                System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = sc.nextLine().trim();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            }
        }

        String cellNumber;
        while (true) {
            System.out.print("Enter your cell number (+27...): ");
            cellNumber = sc.nextLine().trim();
            // the feedback part that you told me to fix with matches
            if (cellNumber.matches("^\\+27\\d{9}$")) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
            }
        }

        String result = login.registerUser(username, password, cellNumber);
        System.out.println(result);

        if (result.toLowerCase().contains("successfully")) {
            System.out.println("-----------------Registration Successful-----------------");
            System.out.println("Please enter your login details");
            login.loginMethod();
        }
        // PART2
        System.out.println("Welcome to QuickChat.");

        int option = 0;

        while (option != 3) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter option: ");

            try {
                option = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                option = 0;
            }

            if (option == 1) {
                System.out.print("How many messages do you want to send? ");
                int numMessages = Integer.parseInt(sc.nextLine().trim());

                for (int i = 0; i < numMessages; i++) {
                    String recipient;
                    while (true) {
                        System.out.print("Enter recipient (max 10 chars, start with +): ");
                        recipient = sc.nextLine().trim();
                        Message validator = new Message(recipient, "test", 0);
                        String res = validator.checkRecipientCell();
                        System.out.println(res);
                        if (res.equals("Cell phone number successfully captured.")) break;
                    }

                    String msgText;
                    while (true) {
                        System.out.print("Enter message (max 250 chars): ");
                        msgText = sc.nextLine().trim();
                        Message validator = new Message(recipient, msgText, 0);
                        String check = validator.checkMessage();
                        System.out.println(check);
                        if (check.equals("Message ready to send.")) break;
                    }

                    Message msg = new Message(recipient, msgText, Message.returnTotalMessages());

                    System.out.print("1-Send, 2-Disregard, 3-Store: ");
                    int choice = Integer.parseInt(sc.nextLine().trim());
                    System.out.println(msg.SentMessage(choice));

                    if (choice == 1) {
                        msg.storeMessage();
                        System.out.println("MessageID: " + msg.getMessageID());
                        System.out.println("MessageHash: " + msg.getMessageHash());
                    } else if (choice == 2) {
                        System.out.println("Message disregarded");
                    } else if (choice == 3) {
                        msg.storeMessage();
                        System.out.println("Message successfully stored.");
                    }
                }
                System.out.println("Total messages sent: " + Message.returnTotalMessages());

            } else if (option == 2) {
                System.out.println("Coming Soon.");

            } else if (option == 3) {
                System.out.println("Quitting...");
            }
        }
        sc.close();
    }
}