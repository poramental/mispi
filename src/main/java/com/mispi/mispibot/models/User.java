package com.mispi.mispibot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mispi.mispibot.botapi.state.BotState;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    
    private BotState state = BotState.DEFAULT;
    @OneToMany(mappedBy="user")
    List<Mispi> mispis = new ArrayList<>();

    public User() {
    }

    public void addMispi(Mispi mispi){
        mispis.add(mispi);
    }
    
}
