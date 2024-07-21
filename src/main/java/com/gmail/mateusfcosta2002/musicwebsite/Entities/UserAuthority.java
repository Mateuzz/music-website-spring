package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class UserAuthority extends AbstractEntity<UserAuthorityId> {
    @EmbeddedId 
    private UserAuthorityId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    private User user;

    public UserAuthority() { }

    public static UserAuthority of(String authority) {
        var userAuthority = new UserAuthority();
        userAuthority.id = new UserAuthorityId(authority);
        return userAuthority;
    }

    public static UserAuthority role(String authority) {
        var userAuthority = new UserAuthority();
        userAuthority.id = new UserAuthorityId(WebProperties.USER_ROLE_PREXIX + authority);
        return userAuthority;
    }

    public UserAuthorityId getId() {
        return id;
    }

    public void setId(UserAuthorityId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

