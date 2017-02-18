/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identify;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus X550ZE
 */
public class IdentityStorage {
    private ArrayList<Identity> data;
    IdentityStorage(List<Identity> data){
        this.data = (ArrayList)data;
    }
    public ArrayList<Identity> getData(){
        return this.data;
    }
    public void setData(List<Identity> data){
        this.data = (ArrayList)data;
    }
    public int findUsername(String username){
        int index = -1;
        for(int i=0;i<this.data.size();i++)
           if(this.data.get(i).getUsername().equals(username)){
               index= i;
           }
        return index;
      }
     public int findToken(String token){
        int index = -1;
        for(int i=0;i<this.data.size();i++)
           if(this.data.get(i).getToken().equals(token)){
               index= i;
           }
        return index;
      }
     
     public void UpdateStorage(String token,String username){
         int index = findUsername(username);
         if(index!=-1){
             this.data.get(index).setToken(token);
             this.data.get(index).setUsername(username);
         }else{
             Identity ident = new Identity(token,username);
             this.data.add(ident);
         }
     }
    
}
