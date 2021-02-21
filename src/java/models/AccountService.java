package models;

import java.io.Serializable;

/**
 *
 * @author 824664
 */
public class AccountService implements Serializable {    
    public User login (String username, String password) {
        if ((username.equals("abe") || username.equals("barb")) && password.equals("password")) {
            return new User(username, null);
        }
        return null;
    }
}
