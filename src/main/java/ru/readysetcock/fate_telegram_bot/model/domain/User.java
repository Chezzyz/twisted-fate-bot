package ru.readysetcock.fate_telegram_bot.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "tg_user_id")
    private Long tgUserId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "state")
    @Setter
    private String state;

    public User(Long tgUserId, String firstName, String lastName, String userName){
        this.tgUserId = tgUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }
}