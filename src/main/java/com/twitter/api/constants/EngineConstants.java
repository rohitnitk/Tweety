package com.twitter.api.constants;

public class EngineConstants {
    // https://developer.twitter.com/en/docs/authentication/oauth-1-0a/obtaining-user-access-tokens
    // terminology
    public static final String AUTH_URL = "https://api.twitter.com/oauth/authorize";
    public static final String TWEET_URL = "https://api.twitter.com/2/tweets";
    /* Request constants --START */
    public static final String include_entities = "include_entities";
    public static final String oauth_consumer_key = "oauth_consumer_key";
    public static final String oauth_token = "oauth_token";
    public static final String oauth_signature_method = "oauth_signature_method";
    public static final String oauth_timestamp = "oauth_timestamp";
    public static final String oauth_nonce = "oauth_nonce";
    public static final String oauth_version = "oauth_version";
    public static final String oauth_signature = "oauth_signature";
    public static final String HMAC_SHA1 = "HMAC-SHA1";
    public static final String status = "status";

    /* Request constants --END */

    /* Request Methods --START */
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";

    /* Request Methods --END */

}
