package register;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Scarletta's
 */
public class RegisterBean {
    private String fullname;
    private String username;
    private String email;
    private String password;
    //private String confirm;
    private String address;
    private String postal;
    private String phone;
    public boolean valid;
    
    public RegisterBean(){
        valid=false;
        fullname="";
        username="";
        email="";
        password="";
        address="";
        postal="";
        phone="";
    }
    public void setFullname (String newFullname) {
        fullname = newFullname;
    }
    
    public String getFullname() {
        return fullname;
    }
    
    public void setUsername (String newUsername) {
        username = newUsername;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setEmail (String newEmail) {
        email = newEmail;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setPassword (String newPassword) {
        password = newPassword;
    }
    
    public String getPassword() {
        return password;
    }
    /*
    public void setConfirm (String newConfirm) {
        confirm = newConfirm;
    }
    
    public String getConfirm() {
        return confirm;
    }
    */
    public void setAddress (String newAddress) {
        address = newAddress;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setPostal (String newPostal) {
        postal = newPostal;
    }
    
    public String getPostal() {
        return postal;
    }
    
    public void setPhone (String newPhone) {
        phone = newPhone;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setValid (boolean newValid) {
        valid = newValid;
    }
    
    public boolean isValid() {
        return valid;
    }
}
