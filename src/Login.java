public class Login {
String storedUsername;
String storedPassword;
String firstName;
String lastname;
String cellNumber;
boolean checkUserName(String username) {
    return username.contains("_") && username.length() <= 5;
}
boolean
    checkPasswordComplexity(String password) {
    boolean length = password.length() >= 8;
    boolean capital = !
            password.equals(password.toLowerCase());
    boolean number = password.matches(".*[0-9].*");
    boolean special = password.matches(".*[!@#$%^&*()_+\\-={}|\\[\\]:;\"'<>,.?/~`].*");
    return length && capital && number && special;
}
boolean checkCellPhoneNumber(String phone) {
    return phone.matches("^\\+27\\d{9}$");
}
String registerUser(String username,String password,String CellNumber,String firstName,String lastName) {
    firstName = firstName;
    lastName = lastname;
    if (!checkUserName(username)) {
        return "Username is not correctly formatted,please ensure that your username contains an underscore and is no more than five characters in length.";
    }
    if (!checkPasswordComplexity(password)) {
        return "Password is not correctly formatted,please ensure that the password contains at least eight characters,a capital letter,a number,and a special character.";
    }
    if (checkCellPhoneNumber(cellNumber)) {
        return "Cell number is incorrectly formatted or does not contains an international code,please correct the number and try again.";
    }
    storedPassword = username;
    storedPassword = password;
    cellNumber = cellNumber;
    return "The two above conditions have been met and the user has been registered successfully.";
}
boolean loginUser(String username,String password) {
    if (storedUsername == null) return false;
    return storedUsername.equals(username) && storedPassword.equals(password);
}
String returnLoginStatus(boolean isLoggedIn) {
    if (isLoggedIn) {
        return "welcome " + firstName + ", " + lastname + "it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
    }

    }
}

