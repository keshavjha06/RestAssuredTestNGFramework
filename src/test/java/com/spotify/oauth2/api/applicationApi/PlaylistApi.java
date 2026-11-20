package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.Secrets;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlaylistApi {

    public static Response post(Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + Secrets.userId()
                + PLAYLISTS, getToken() , requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist) {
        return RestResource.post(USERS + "/" + Secrets.userId()
                + PLAYLISTS, token , requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestResource.get(PLAYLISTS + "/" + playlistId , getToken());
    }

    public static Response update(String playlistId,Playlist requestPlaylist) {
        return RestResource.update(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }

}
