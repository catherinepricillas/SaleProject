/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identify;

/**
 *
 * @author Asus X550ZE
 */
public class Identity {
    private String token;
    private String username;
    Identity(){
        token="non-token";
        username="non-identified";
    }
    Identity(String token,String username){
        this.token= token;
        this.username=username;
    }
    public String getToken(){
        return this.token;
    }
    public void setToken(String token){
        this.token=token;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username= username;
    }
    
}
