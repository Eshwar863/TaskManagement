package com.TaskManagement.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentsDto {
    private long id ;
    private String albumid;
    private String comment;

    public String getAlbumid() {
        return albumid;
    }

    public void setAlbumid(String albumid) {
        this.albumid = albumid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
