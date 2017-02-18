package login;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Scarletta's
 */
public class LoginBean {
    private String username;
    private String password;
    public boolean valid;
    
    public void setUsername (String newUsername) {
        username = newUsername;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setPassword (String newPassword) {
        password = newPassword;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setValid (boolean newValid) {
        valid = newValid;
    }
    
    public boolean isValid() {
        return valid;
    }
}
