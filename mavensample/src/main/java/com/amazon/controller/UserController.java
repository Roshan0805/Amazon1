package com.amazon.controller;

import com.amazon.model.User;
import com.amazon.service.Impl2.UserServiceImpl2;
import com.amazon.service.impl.UserServiceImpl;
import com.amazon.service.ProductService;
import com.amazon.service.UserService;

import java.util.Collection;

/**
 * <p>
 * Provides Control between service and the view to provide {@link User} sign in, sign up, update and delete
 * </p>
 *
 * @author Roshan
 * @version 1.0
 */
public class UserController {

    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();
    private final UserService USER_SERVICE_2 = UserServiceImpl2.getInstance();
    private static final UserController USER_CONTROLLER = new UserController();
    private final ProductService PRODUCT_SERVICE = ProductService.getInstance();

    private UserController() {
    }

    /**
     * Check whether the product list is empty or not
     * @return True if the product list is
     */
    public boolean checkProductList() {
        return !PRODUCT_SERVICE.getAllProducts().isEmpty();
    }

    /**
     * <p>
     * Represents the object of AmazonUserController class can be created for only one time
     * </p>
     *
     * @return Represents {@link UserController}
     */
    public static UserController getInstance() {
        return USER_CONTROLLER;
    }

    /**
     * <p>
     * Gets the user details from the user list using user email
     * </p>
     *
     * @param id Represents user's id
     * @return {@link User} from the amazon service
     */
    public User getDetail(final Long id) {
        return USER_SERVICE_2.get(id);
    }

    /**
     * <p>
     * Deletes the user from the user list
     * </p>
     *
     * @param user_id Represents {@link User}
     * @return True if the user is deleted successfully otherwise return false
     */
    public boolean delete(final Long user_id) {
        return USER_SERVICE_2.delete(user_id);
    }

    /**
     * Represents all the {@link User} details in the usersList
     * @return Represents collection of {@link User}
     */
    public Collection<User> getAllUser() {return USER_SERVICE_2.getAllUser();}

    /**
     * <p>
     * Represents the user update
     * </p>
     *
     * @param user   Represents the updated {@link User}
     * @param userId Represents the user's id
     * @return true if updated successfully
     */
    public boolean update(final User user, final Long userId) {
        return USER_SERVICE_2.update(user,userId);
    }
}