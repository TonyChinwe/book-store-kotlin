package com.phisoft.bookstorekotlin.authentication;

/**
 * Username and password class used in request filter during authentication
 */
public class UsernamePasswordAuthRequest {


    String username;
    String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
