package com.mispi.mispibot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mispi")
@Getter
@Setter
@NoArgsConstructor
public class Mispi {
    @Id
    @Column(name = "id")
    private long id;

    
    @JoinColumn(name = "user_id", nullable = false)
    private long userChatId;

    @Column(name = "place")
    private String place;

    public Mispi(long id,long chatId){
        this.id = id;
        this.userChatId = chatId;
        
    }

    
}
