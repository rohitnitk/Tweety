package com.twitter.api.entity.backend.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twitter.api.entity.backend.Media;
import com.twitter.api.entity.backend.Reply;

@JsonInclude(Include.NON_NULL)
public class TweetRequest {

    /*
     * Text of the Tweet being created. This field is required if media.media_ids is not present. Example:
     * {"text": "Hello World!"}
     */
    private String text;

    @JsonProperty("for_super_followers_only")
    private boolean forSuperFollowersOnly; // Allows you to Tweet exclusively for Super Followers.

    /* Link to the Tweet being quoted. Example: {"text": "Yay!", "quote_tweet_id": "1455953449422516226"} */
    @JsonProperty("quote_tweet_id")
    private String quoteTweetId;

    /*
     * A JSON object that contains media information being attached to created Tweet. This is mutually
     * exclusive from Quote Tweet ID and Poll.
     */
    private Media media;

    private Reply reply; // A JSON object that contains information of the Tweet being replied to

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isForSuperFollowersOnly() {
        return forSuperFollowersOnly;
    }

    public void setForSuperFollowersOnly(boolean forSuperFollowersOnly) {
        this.forSuperFollowersOnly = forSuperFollowersOnly;
    }

    public String getQuoteTweetId() {
        return quoteTweetId;
    }

    public void setQuoteTweetId(String quoteTweetId) {
        this.quoteTweetId = quoteTweetId;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

}
