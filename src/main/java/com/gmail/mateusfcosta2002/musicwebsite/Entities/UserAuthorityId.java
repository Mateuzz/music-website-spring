package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserAuthorityId {
    @Column(length = 50)
    private String authority;

    private long userId;

    public UserAuthorityId() {
    }

    public UserAuthorityId(String authority) {
        this.authority = authority;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

