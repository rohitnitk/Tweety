package com.twitter.api.entity.backend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * A JSON object that contains information of the Tweet being replied to.
 */
@JsonInclude(Include.NON_NULL)
public class Reply {
    /*
     * Tweet ID of the Tweet being replied to. Please note that in_reply_to_tweet_id needs to be in the
     * request if exclude_reply_user_ids is present.
     */
    @JsonProperty("in_reply_to_tweet_id")
    private String inReplyToTweetId;

    /*
     * A list of User IDs to be excluded from the reply Tweet thus removing a user from a thread.
     */
    @JsonProperty("exclude_reply_user_ids")
    private List<String> excludeReplyUserIds;

    /*
     * Settings to indicate who can reply to the Tweet. Options include "mentionedUsers" and "following". If
     * the field isn’t specified, it will default to everyone. Example:
     * {"text":"Tweeting with reply settings!", "reply_settings": "mentionedUsers"}
     */
    @JsonProperty("reply_settings")
    private String replySettings;

    public String getInReplyToTweetId() {
        return inReplyToTweetId;
    }

    public void setInReplyToTweetId(String inReplyToTweetId) {
        this.inReplyToTweetId = inReplyToTweetId;
    }

    public List<String> getExcludeReplyUserIds() {
        return excludeReplyUserIds;
    }

    public void setExcludeReplyUserIds(List<String> excludeReplyUserIds) {
        this.excludeReplyUserIds = excludeReplyUserIds;
    }

    public String getReplySettings() {
        return replySettings;
    }

    public void setReplySettings(String replySettings) {
        this.replySettings = replySettings;
    }

}
