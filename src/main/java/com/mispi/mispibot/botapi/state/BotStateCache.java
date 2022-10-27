package com.mispi.mispibot.botapi.state;

import java.util.HashMap;
import java.util.Map;

import com.mispi.mispibot.models.User;

public class BotStateCache {
    private Map<Long, User> usersAndStates = new HashMap<>();
    

    public Boolean isHaveState(long userId){
        for(long id : usersAndStates.keySet()){
            if(id == userId){
                return true;
            }
        }
        return false;
    }

    public void add(long id, User user){
        usersAndStates.put(id,user);
    }

    public BotState getStateById(long id){
        try{
        return usersAndStates.get(id).getState();
        }catch (NullPointerException e){
            return BotState.DEFAULT;
        }
    }

    public Iterable<User> getValues(){
        return usersAndStates.values();
    }

    public void delete(long id){
        usersAndStates.remove(id);
    }
}
