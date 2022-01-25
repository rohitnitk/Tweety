package com.twitter.api.entity.backend.response;

public class TweetResponse extends BaseResponse {

    private String responseBody;

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
