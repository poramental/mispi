package com.mispi.mispibot.botapi.state;

import java.util.HashMap;
import java.util.Map;

import com.mispi.mispibot.models.AppUser;

public class BotStateCache {
    private Map<Long, AppUser> usersAndStates = new HashMap<>();
    

    public Boolean isHaveState(long userId){
        for(long id : usersAndStates.keySet()){
            if(id == userId){
                return true;
            }
        }
        return false;
    }

    public void add(long id, AppUser user){
        usersAndStates.put(id,user);
    }

    public BotState getStateById(long id){
        try{
        return usersAndStates.get(id).getState();
        }catch (NullPointerException e){
            return BotState.DEFAULT;
        }
    }

    public Iterable<AppUser> getValues(){
        return usersAndStates.values();
    }

    public void delete(long id){
        usersAndStates.remove(id);
    }

    public AppUser getUserById(long id){
        return usersAndStates.get(id);
    }
}
