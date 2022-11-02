package com.mispi.mispibot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.mispi.mispibot.botapi.state.BotState;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_user", schema = "public")
@Getter
@Setter
public class AppUser {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sate")
    private BotState state = BotState.DEFAULT;


    public AppUser() {
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }

  
}
