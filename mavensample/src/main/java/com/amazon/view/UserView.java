package com.amazon.view;

import com.amazon.controller.UserController;
import com.amazon.model.User;
import com.amazon.view.validation.UserValidation;

import java.util.Collection;

/**
 * <p>
 * Represents the view of {@link User}
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserView extends View {


    private static final ProductView PRODUCT_VIEW = new ProductView();
    private static final AuthenticationView AUTHENTICATION_VIEW = AuthenticationView.getInstance();
    private static final UserView USER_VIEW = new UserView();
    private static final UserController USER_CONTROLLER = UserController.getInstance();
    private static final UserValidation USER_VALIDATION = UserValidation.getInstance();
    protected UserView() {
    }

    /**
     * <p>
     * Represents the {@link UserView} class object can be created for only one time
     * </p>
     *
     * @return Represents the object of {@link UserView}
     */

    public static UserView getInstance() {
        return USER_VIEW;
    }

    /**
     * <p>
     * Retrieves the admin details using email ,update the admin details and delete admin details
     * </p>
     *
     * @param userId Represents id of {@link User}
     */
    public void accessUser(final Long userId) {
        System.out.println("Choose from the options\n1.get user\n2.update user\n3.delete user\n4.back to user menu");
        final int userOption = getUserChoice();

        switch (userOption) {
            case 1:
                System.out.println(get(userId));
                break;
            case 2:
                update(userId);
                break;
            case 3:
                delete(userId);
                AUTHENTICATION_VIEW.displayMenu();
                break;
            case 4:
                getUserOptions();
                break;
            default:
                System.out.println("Enter the correct option");
                accessUser(userId);
        }
        System.out.println("Do you want to continue press yes(y) or return back to menu press no(n)");

        if (USER_VALIDATION.toContinueValidation(SCANNER.nextLine().trim())) {
            accessUser(userId);
        } else {
            getUserOptions();
        }
    }

    /**
     * <p>
     * Gets the user details in user list by using user id
     * </p>
     *
     * @return Represents {@link User}
     */
    public User get(final Long userId) {

        final User user = USER_CONTROLLER.getDetail(userId);

        if (user == null) {
            System.out.println("The user is not exists");

            return null;
        } else {
            return user;
        }
    }

    /**
     * <p>
     * Updates the Admin details in user list using user object
     * </p>
     *
     * @param userId Represents the id of {@link User}
     */
    private void update(final Long userId) {
        final User user = get(userId);
        if (user == null) {
            System.out.println("User not found");
            getUserOptions();
        }
        updateName(user);
        updateEmail(user);
        updatePassword(user);
        updateAddress(user);
        updatePhoneNo(user);
        USER_CONTROLLER.update(user, userId);
        getUserOptions();

    }

    /**
     * <p>
     * Updates the username in user object
     * </p>
     *
     * @param user Represents {@link User} wants to update
     */
    private void updateName(final User user) {
        System.out.println("Do you want to update user name press yes(y) else press no(n)");

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine().trim())) {
            user.setName(getName());
        }
    }

    /**
     * <p>
     * Updates the user email in user object
     * </p>
     *
     * @param user Represents {@link User} want's to update
     */
    private void updateEmail(final User user) {
        System.out.println("Do you want to update user email press yes(y) else press no(n)");

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine().trim())) {
            user.setEmail(getEmail());
        }
    }

    /**
     * <p>
     * Updates the user password in user object
     * </p>
     *
     * @param user {@link User} User object wants to update
     */
    private void updatePassword(final User user) {
        System.out.println("Do you want to update user password press yes(y) else press no(n)");

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine().trim())) {
            user.setPassword(getPassword());
        }
    }

    /**
     * <p>
     * Updates the user address in user object
     * </p>
     *
     * @param user {@link User} User object wants to update
     */
    private void updateAddress(final User user) {
        System.out.println("Do you want to update user address press yes(y) else press no(n)");

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine().trim())) {
            user.setAddress(getAddress());
        }
    }

    /**
     * <p>
     * Updates the phone no in user object
     * </p>
     *
     * @param user Represents {@link User} wants to update
     */
    private void updatePhoneNo(final User user) {
        System.out.println("Do you want to update user phone number press yes(y) else press no(n)");

        if (USER_VALIDATION.updateChoiceValidation(SCANNER.nextLine().trim())) {
            user.setPhoneNumber(getPhoneNumber());
        }
    }

    /**
     * <p>
     * Deletes the user object from user list
     * </p>
     *
     * @param user_id Represents id of {@link User}
     */
    private void delete(final Long user_id) {
        if (USER_CONTROLLER.delete(user_id)) {
            System.out.println("User Account deleted");
        } else {
            System.out.println("User account not deleted");
        }
    }

    /**
     * <p>
     * Gets and validate email value from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    public String getEmail() {
        try {
            System.out.println("Enter the email id\t(press # for logout to menu)");
            final String email = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(email)) {
                AUTHENTICATION_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateEmail(email)) {
                return email;
            } else {
                System.out.println("Invalid email id enter the correct email id");
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getEmail();
    }

    /**
     * <p>
     * Gets and validate id value from user
     * </p>
     *
     * @return Represents email id the user entered
     */
    public Long getId() {
        try {
            System.out.println("Enter the id\t(press # for logout to menu)");
            final Long id = Long.parseLong(SCANNER.nextLine().trim());

            if (USER_VALIDATION.isReturnToMenu(String.valueOf(id))) {
                AUTHENTICATION_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateId(String.valueOf(id))) {
                return id;
            } else {
                System.out.println("Invalid id enter the correct id");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getId();
    }

    /**
     * <p>
     * Gets and validate the user password from the user
     * </p>
     *
     * @return Represents password that the user entered
     */
    public String getPassword() {
        try {
            System.out.println(String.join(" ", "Enter the password\t(password must contain",
                    "one capital letter small letter, number and a symbol)\t(press # for logout to menu)"));
            final String password = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(password)) {
                AUTHENTICATION_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validatePassword(password)) {
                return password;
            }
            System.out.println("Your password is no strong enough");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getPassword();
    }

    /**
     * <p>
     * Gets address from the user object
     * </p>
     *
     * @return Represents address user entered
     */
    public String getAddress() {
        try {
            System.out.println(("Enter the address with door no\t (press # for logout to menu)"));
            final String address = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(address)) {
                AUTHENTICATION_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateAddress(address)) {
                return address;
            } else {
                System.out.println("Invalid address");
            }
        } catch (IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }

        return getAddress();
    }

    /**
     * <p>
     * Gets and validate the username from the user
     * </p>
     *
     * @return Represents username the user entered
     */
    public String getName() {
        try {
            System.out.println("Enter the user name\t(press # for logout to menu)");
            final String userName = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(userName)) {
                AUTHENTICATION_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validateUserName(userName)) {
                return userName;
            }
            System.out.println("Invalid username");
        } catch (final IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getName();
    }

    /**
     * <p>
     * Gets and validate phone number from user
     * </p>
     *
     * @return Represents phone number the user entered
     */
    public String getPhoneNumber() {
        try {
            System.out.println("Enter the phone no\t(press # for logout)");
            final String phoneNo = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(phoneNo)) {
                AUTHENTICATION_VIEW.displayMenu();
            }

            if (USER_VALIDATION.validatePhone(phoneNo)) {
                return phoneNo;
            }
            System.out.println("Invalid phone number");
        } catch (final IndexOutOfBoundsException exception) {
            System.out.println(exception.getMessage());
        }
        return getPhoneNumber();
    }

    /**
     * <p>
     * Gets and validate the user's choice
     * </p>
     *
     * @return Represents value that the user entered
     */
    public int getUserChoice() {
        System.out.println("Enter the choice\t(press # for return to menu)");
        try {
            final String userChoice = SCANNER.nextLine().trim();

            if (USER_VALIDATION.isReturnToMenu(userChoice)) {
                getUserOptions();
            }

            return Integer.parseInt(userChoice);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input enter the number input");
        } catch (StringIndexOutOfBoundsException exception) {
            System.out.println("Enter valid input");
        }

        return getUserChoice();
    }

    /**
     * <p>
     * Provides option for access admin details and the products details and logout
     * </p>
     */
    public void getUserOptions() {
        System.out.println(String.join("", "Choose from the options\n1.admin details\n",
                "2.sell product\n3.view products\n4.\n5.logout"));
        final int adminOption = USER_VIEW.getUserChoice();

        if (5 == adminOption) {
            AUTHENTICATION_VIEW.displayMenu();
        }
        final Long userId = USER_VIEW.getId();

        switch (adminOption) {
            case 1:
                USER_VIEW.accessUser(userId);
                break;
            case 2:
                PRODUCT_VIEW.accessProduct(userId);
                break;
            case 3:
                PRODUCT_VIEW.viewProduct(userId);
                break;
            case 4:
                PRODUCT_VIEW.accessCart(userId);
                break;
            case 5:
                PRODUCT_VIEW.accessOrder(userId);
                break;
            case 6:
                getAllUsers();
                break;
            default:
                System.out.println("Enter the correct option");
                getUserOptions();
        }
    }

    /**
     * <p>
     * Represents all the {@link User} details in the usersList
     * </p>
     */
    public void getAllUsers() {
        Collection<User> users = USER_CONTROLLER.getAllUser();

        if (users.isEmpty()) {
            System.out.println("The user list is empty");
        } else {
            System.out.println(users);
        }
        getUserOptions();
    }

    public static void main(final String[] args) {
        System.out.println("Welcome to amazon");

        if (USER_CONTROLLER.checkProductList()) {
            AUTHENTICATION_VIEW.displayMenu();
            System.out.println("Do you want to continue yes(y) else press n to exit");
            final String exitChoice = SCANNER.nextLine().trim();

            if (USER_VALIDATION.toContinueValidation(exitChoice)) {
                AUTHENTICATION_VIEW.displayMenu();
            } else {
                SCANNER.close();
            }
        } else {
            System.out.println("Product list is empty please signUp to add some products");
            AUTHENTICATION_VIEW.displayMenu();
        }
    }
}