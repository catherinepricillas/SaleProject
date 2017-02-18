/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.security.SecureRandom;

/**
 *
 * @author Asus X550ZE
 */
public class TokenGenerator {
    private String str_token; 
    private String user_agent;
    private String ip_address;
    private String  token;
    
    public TokenGenerator(){
      this.str_token=""; 
      this.user_agent="";
      this.ip_address="";
      this.token="";
    }
     public TokenGenerator(String token){
        this.token= token;
        String[] arrElToken = token.split("#");
        this.str_token = arrElToken[0];
        this.user_agent = arrElToken[1];
        this.ip_address = arrElToken[2];
     }
      
    public void buildToken(String user_agent,String ip_address){
        int len = 12;
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
        sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        this.str_token = sb.toString();
        this.user_agent = user_agent;
        this.ip_address = ip_address;
        this.token= this.str_token+"#"+this.user_agent+"#"+this.ip_address;  
    }
    
    public String getToken(){
        return this.token;
    }
    
    public void setToken(String token){
        this.token=token;
    }
    public String getStrToken(){
        return this.str_token;
    }
    
    public void setStrToken(String str_token){
        this.str_token = str_token;
    }
    
    public String getUserAgent(){
        return this.user_agent;
    }
    
    public void setUserAgent(String user_agent){
        this.user_agent=user_agent;
    }
    
    public String getIpAdress(){
        return this.ip_address;
    }
    
    public void setIpAddress(String ip_address){
        this.ip_address= ip_address;
    }
    
}
