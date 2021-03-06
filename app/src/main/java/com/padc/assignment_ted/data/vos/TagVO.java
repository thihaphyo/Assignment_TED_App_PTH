package com.padc.assignment_ted.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phyo Thiha on 6/10/18.
 */
public class TagVO {

    @SerializedName("tag_id")
    private int tagId;
    @SerializedName("tag")
    private String tag;
    @SerializedName("description")
    private String description;

    public int getTagId() {
        return tagId;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }
}
