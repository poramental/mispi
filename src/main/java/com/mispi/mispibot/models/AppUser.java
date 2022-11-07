package com.mispi.mispibot.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import com.mispi.mispibot.botapi.state.BotState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class AppUser {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "userfirstname")
    private String FirstName;

    @Column(name = "userlastname")
    private String LastName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private BotState state = BotState.DEFAULT;

    @Column(name = "username")
    private String username;

    @OneToMany(targetEntity = Mispi.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "mispi", referencedColumnName = "id")
    List<Mispi> mispies = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime firstLoginDate;


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getLastName() +" "+getFirstName()+ "'" +
            ", state='" + getState() + "'" +
            "}";
    }


    public void setMispi(Mispi mispi){
        this.mispies.add(mispi);
    }

  
}
