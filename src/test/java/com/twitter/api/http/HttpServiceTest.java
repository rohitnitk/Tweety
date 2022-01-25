package com.twitter.api.http;

import static com.twitter.api.constants.EngineConstants.POST;
import static com.twitter.api.constants.EngineConstants.TWEET_URL;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.twitter.api.oauth.Authorization;
import com.twitter.config.PropConfig;

public class HttpServiceTest {

    private HttpService httpService;
    private Authorization authorization;
    private PropConfig propConfig;

    @BeforeEach
    public void setUp() throws Exception {
        propConfig = new PropConfig();
        String consumerKey = propConfig.getApiKey();
        String consumerSecret = propConfig.getApiKeySecret();
        String token = propConfig.getAccessToken();
        String tokenSecret = propConfig.getAccessTokenSecret();
        httpService = new HttpService();
        authorization = new Authorization(consumerKey, token, tokenSecret, consumerSecret);
    }

    @Test
    public void post() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException, InterruptedException {
        String body = "{\"text\": \"Hello World!! #Tweetings \"}";
        HttpRequest request = httpService.post(TWEET_URL, authorization.generateOauthHeader(POST, TWEET_URL), body);
    }

}
