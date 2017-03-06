package com.example.models.networkModels;
import com.example.models.gameModels.OwnableUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private final String aUsername;
    private final String aPassword;
    private List<Integer> aGameIds;
    private UserStatus aStatus;

    public User(String pUsername,String pPassword){
    aUsername = pUsername;
    aPassword = pPassword;
    aStatus = UserStatus.Offline;
    aGameIds = new ArrayList<Integer>();
    }

    public void updateStatus(UserStatus pStatus) {
        aStatus = pStatus;
    }

    public void addGame(int pID)
    {
        aGameIds.add(pID);
    }

    public enum UserStatus{
        Offline,Available,Ready,Ingame
    }

}
