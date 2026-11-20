package com.spotify.oauth2.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import io.github.cdimascio.dotenv.DotenvException;

import java.util.Map;
import java.util.stream.Collectors;

public final class Secrets {

    private static Map<String, String> values;

    private Secrets() {
    }

    /** Spotify app client ID. */
    public static String clientId() {
        return getSecret("CLIENT_ID");
    }

    /** Spotify app client secret. */
    public static String clientSecret() {
        return getSecret("CLIENT_SECRET");
    }

    /** Spotify refresh token, exchanged for an access token by TokenManager. */
    public static String refreshToken() {
        return getSecret("REFRESH_TOKEN");
    }

    /** OAuth grant type for token renewal; defaults to refresh_token. */
    public static String grantType() {
        return getSecret("GRANT_TYPE", "refresh_token");
    }

    /** Spotify user ID that owns the playlists. */
    public static String userId() {
        return getSecret("USER_ID");
    }

    /** Spotify Web API base URI; a -DBASE_URI system property overrides .env. */
    public static String baseUri() {
        return System.getProperty("BASE_URI", getSecret("BASE_URI", "https://api.spotify.com"));
    }

    /** Spotify Accounts base URI; a -DACCOUNT_BASE_URI system property overrides .env. */
    public static String accountBaseUri() {
        return System.getProperty("ACCOUNT_BASE_URI", getSecret("ACCOUNT_BASE_URI", "https://accounts.spotify.com"));
    }

    /** Returns the value of {@code name} from .env, failing if it is missing or blank. */
    public static String getSecret(String name) {
        String value = values().get(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(name + " is not set in .env");
        }
        return value;
    }

    /** Returns the value of {@code name} from .env, or {@code defaultValue} if it is missing or blank. */
    public static String getSecret(String name, String defaultValue) {
        String value = values().get(name);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    private static synchronized Map<String, String> values() {
        if (values == null) {
            try {
                values = Dotenv.load().entries(Dotenv.Filter.DECLARED_IN_ENV_FILE).stream()
                        .collect(Collectors.toMap(DotenvEntry::getKey, DotenvEntry::getValue));
            } catch (DotenvException e) {
                throw new IllegalStateException(".env file not found in the project root. Create it from .env.example.", e);
            }
        }
        return values;
    }
}
