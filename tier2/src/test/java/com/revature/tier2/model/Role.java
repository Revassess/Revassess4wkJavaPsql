package com.revature.tier2.model;


public enum Role {
    DEBUG(0),
    ADMIN(1),
    DEV(2),
    BASIC_USER(3),
    PREMIUM_USER(4),
    LOCKED(5);

    private int role_id;

    Role(int role_id) {
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }
}