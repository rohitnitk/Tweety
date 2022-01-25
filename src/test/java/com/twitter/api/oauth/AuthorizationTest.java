package com.twitter.api.oauth;

import static com.twitter.api.constants.EngineConstants.POST;
import static com.twitter.api.constants.EngineConstants.TWEET_URL;
import static com.twitter.api.constants.EngineConstants.include_entities;
import static com.twitter.api.constants.EngineConstants.oauth_consumer_key;
import static com.twitter.api.constants.EngineConstants.oauth_nonce;
import static com.twitter.api.constants.EngineConstants.oauth_signature_method;
import static com.twitter.api.constants.EngineConstants.oauth_timestamp;
import static com.twitter.api.constants.EngineConstants.oauth_token;
import static com.twitter.api.constants.EngineConstants.oauth_version;
import static com.twitter.api.constants.EngineConstants.status;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.twitter.api.utils.SecurityUtils;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationTest {

    private Authorization authorization;
    String consumer_key = "xvz1evFS4wEEPTGEFPHBog";
    String consumer_secret = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw";
    String nonce = "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg";
    String signature = "tnnArxj06cWHq44gCs1OSKk/jLY=";
    String signature_method = "HMAC-SHA1";
    String timestamp = "1318622958";
    String token = "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb";
    String tokenSecret = "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE";
    SecurityUtils securityUtils;


    @Test
    public void generateOauthHeader() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

        // when(securityUtils.getHmacSHA1Signature(any(), any())).thenReturn(signature);
        // when(securityUtils.generateNonce()).thenReturn(nonce);
        // when(securityUtils.getUnixTimeStamp()).thenReturn(timestamp);
        // when(authorization.getOauthParamsMap()).thenReturn(getRequestMap());
        authorization = new Authorization(consumer_key, token, tokenSecret, consumer_secret);
        authorization.generateOauthHeader(POST, TWEET_URL);
    }

    private Map<String, String> getRequestMap() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(status, "Hello Ladies + Gentlemen, a signed OAuth request!");
        requestParams.put(include_entities, "true");
        requestParams.put(oauth_consumer_key, consumer_key);
        requestParams.put(oauth_nonce, nonce);
        requestParams.put(oauth_signature_method, signature_method);
        requestParams.put(oauth_timestamp, timestamp);
        requestParams.put(oauth_token, token);
        requestParams.put(oauth_version, "1.0");

        return requestParams;
    }

}
