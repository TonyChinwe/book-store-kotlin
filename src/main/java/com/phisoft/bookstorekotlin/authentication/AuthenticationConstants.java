package com.phisoft.bookstorekotlin.authentication;

/**
 * Jwt token constants
 */
public class AuthenticationConstants {

    private AuthenticationConstants(){

    }

    /**
     * jwt secret key
     */
    static final String jwtSecretKey="securedhereinnigeria5672securedtherewithenugu109765butsecuredinigboetiti65309andthenpositive";
    /**
     * jwt token prefix which is bearer
     */
    static final String jwtTokenPrefix="Bearer";
    /**
     * jwt header key
     */
    static final String jwtHeader="Authorization";
    /**
     * jwt token expiration period. This expiration period is one day
     */
    static final int jwtTokenExpiration=1;
}
