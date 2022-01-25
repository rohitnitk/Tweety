package com.twitter.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.twitter.api.entity.backend.request.TweetRequest;
import com.twitter.config.PropConfig;

class TweetyTest {

    private Tweety tweety;
    private PropConfig propConfig;

    @BeforeEach
    void setUp() throws Exception {
        propConfig = new PropConfig();
        String consumerKey = propConfig.getApiKey();
        String consumerSecret = propConfig.getApiKeySecret();
        String token = propConfig.getAccessToken();
        String tokenSecret = propConfig.getAccessTokenSecret();
        tweety = new Tweety(consumerKey, token, tokenSecret, consumerSecret);
    }

    @Test
    void test() {
        TweetRequest tweetRequest = new TweetRequest();
        tweetRequest.setText("This is tweeted using bot #firstTweet");
        try {
            tweety.postTweet(tweetRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
