package com.twitter.api;

import static com.twitter.api.constants.EngineConstants.POST;
import static com.twitter.api.constants.EngineConstants.TWEET_URL;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.twitter.api.entity.backend.request.TweetRequest;
import com.twitter.api.entity.backend.response.TweetResponse;
import com.twitter.api.http.HttpService;
import com.twitter.api.oauth.Authorization;

public class Tweety {

    private String consumerKey;
    private String consumerSecret;
    private String token;
    private String tokenSecret;

    public Tweety(String consumerKey, String token, String tokenSecret, String consumerSecret) {
        super();
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    public TweetResponse postTweet(TweetRequest tweetRequest) throws Exception {
        Authorization authorization = new Authorization(consumerKey, token, tokenSecret, consumerSecret);
        HttpService httpService = new HttpService();
        HttpRequest request = httpService.post(TWEET_URL, authorization.generateOauthHeader(POST, TWEET_URL), tweetRequest);
        HttpResponse<String> backenHttpResponse = httpService.getResponse(request);
        TweetResponse response = new TweetResponse();
        if (backenHttpResponse.statusCode() == 201) {
            response.setSuccess(true);
        }
        response.setHttpStatus(backenHttpResponse.statusCode());
        response.setResponseBody(backenHttpResponse.body());
        return response;
    }

}
