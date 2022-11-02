package com.mispi.mispibot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mispi")
@Getter
@Setter
public class Mispi {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "user_id")
    private long user;
    public Mispi(){
    }

    public Mispi(long id,long user){
        this.id = id;
        this.user = user;
        
    }

    
}
