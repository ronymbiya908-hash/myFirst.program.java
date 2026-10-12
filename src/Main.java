import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        String cellNumber;
        String FirstName;
        String LastName;

        System.out.print("Enter your first Name: ");
        FirstName = sc.nextLine();
        System.out.print("Enter your last Name: ");
        LastName = sc.nextLine();

        while (true) {
            System.out.print("Enter your user name: ");
            username = sc.nextLine();
            if (username.length() <= 5 && username.contains("_")) {
                System.out.println("Username successfully saved");
                break;
            } else {
                System.out.println("Username wrongly formatted");
            }
        }

        while (true) {
            System.out.print("Enter password: ");
            password = sc.nextLine();
            boolean hasCapital = false;
            boolean hasNumber = false;
            boolean hasSpecial = false;
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if (Character.isUpperCase(ch)) hasCapital = true;
                if (Character.isDigit(ch)) hasNumber = true;
                if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
            }
            if (password.length() >= 8 && hasCapital && hasNumber && hasSpecial) {
                System.out.println("Password successfully saved");
                break;
            } else {
                System.out.println("Password wrongly formatted");
            }
        }

        while (true) {
            System.out.print("Enter your cell number (+27...): ");
            cellNumber = sc.nextLine();
            if (cellNumber.startsWith("+27") && cellNumber.length() == 12) {
                System.out.println("CellNumber successfully saved");
                break;
            } else {
                System.out.println("CellNumber wrongly formatted");
            }
        }

        System.out.println("-----------------Registration Successful-----------------");
        System.out.println("Please enter your login details");

        Login myLogin = new Login();
        myLogin.username = username;
        myLogin.password = password;
        myLogin.FirstName = FirstName;
        myLogin.LastName = LastName;
        myLogin.loginMethod();

        // PART 2
        System.out.println("Welcome to QuickChat.");
        System.out.print("How many messages do you wish to send? ");
        int totalToSend = Integer.parseInt(sc.nextLine());

        int option = 0;
        while (option != 3) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            System.out.print("Choose: ");
            option = Integer.parseInt(sc.nextLine());

            if (option == 1) {
                for (int i = 0; i < totalToSend; i++) {
                    System.out.print("Enter recipient (+27...): ");
                    String rec = sc.nextLine();
                    System.out.print("Enter message (max 250): ");
                    String txt = sc.nextLine();

                    Message msg = new Message(rec, txt, i);

                    System.out.println(msg.checkMessage());
                    System.out.println(msg.checkRecipientCell());

                    if (msg.checkMessage().equals("Message ready to send.") && msg.checkRecipientCell().contains("successfully")) {
                        System.out.println("MessageID: " + msg.messageID);
                        System.out.println("Message Hash: " + msg.createMessageHash());

                        System.out.println("\n1) Send Message\n2) Disregard Message\n3) Store Message");
                        System.out.print("Choose: ");
                        int choice = Integer.parseInt(sc.nextLine());
                        System.out.println(msg.SentMessage(choice));

                        if (choice == 1) {
                            Message.sentMessages.add(msg);
                            Message.totalMessages++;
                            System.out.println(msg.printMessages());
                        }
                        if (choice == 3) {
                            msg.storeMessage();
                            Message.sentMessages.add(msg);
                            Message.totalMessages++;
                        }
                    }
                }
                System.out.println("Total messages sent: " + Message.totalMessages);
            }
            if (option == 2) {
                System.out.println("Coming Soon.");
            }
        }
    }
}