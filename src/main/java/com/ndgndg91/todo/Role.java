package com.ndgndg91.todo;

public enum Role {
    ANONYMOUS,
    USER,
    ADMIN;

    public String addPrefix(){
        return "ROLE_" + this.name();
    }

    public static boolean isNotAnonymous(String role) {
        return ! ANONYMOUS.name().equals(role);
    }
}
