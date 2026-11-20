package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.Secrets;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
        try{
            if(access_token == null || Instant.now().isAfter(expiry_time)){
                System.out.println("Renewing token ...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else{
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            throw new RuntimeException("ABORT!!! Failed to get token", e);
        }
        return access_token;
    }


    private static Response renewToken(){
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", Secrets.clientId());
        formParams.put("client_secret", Secrets.clientSecret());
        formParams.put("refresh_token", Secrets.refreshToken());
        formParams.put("grant_type", Secrets.grantType());

         Response response = RestResource.postAccount(formParams);

        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;
    }
}
