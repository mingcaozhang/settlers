package com.example.models.networkModels;
import com.example.models.gameModels.OwnableUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    //private List<Integer> aGameIds;
    //private UserStatus aStatus;

    public User(){

    }

    public User(String username,String password){
        this.username = username;
        this.password = password;
        //aStatus = UserStatus.Offline;
        //aGameIds = new ArrayList<Integer>();
    }

    public String getUsername(){return username;}

    public boolean comparePassword(String pPassword)
    {
        if(pPassword.equals(password))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
/*
    public void updateStatus(UserStatus pStatus) {
        aStatus = pStatus;
    }
*/
    /*public void addGame(int pID)
    {
        aGameIds.add(pID);
    }*/

    public enum UserStatus{
        Offline,Available,Ready,Ingame
    }

}
