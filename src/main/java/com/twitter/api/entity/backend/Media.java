package com.twitter.api.entity.backend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * "media": {"media_ids": ["1455952740635586573"], "tagged_user_ids": ["2244994945","6253282"]}}
 */
@JsonInclude(Include.NON_NULL)
public class Media {

    @JsonProperty("media_ids")
    private List<String> mediaIds;

    @JsonProperty("tagged_user_ids")
    private List<String> taggedUserIds;

    public List<String> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<String> mediaIds) {
        this.mediaIds = mediaIds;
    }

    public List<String> getTaggedUserIds() {
        return taggedUserIds;
    }

    public void setTaggedUserIds(List<String> taggedUserIds) {
        this.taggedUserIds = taggedUserIds;
    }

}
