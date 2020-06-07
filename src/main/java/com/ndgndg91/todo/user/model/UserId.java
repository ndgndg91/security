package com.ndgndg91.todo.user.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UserId implements Serializable {
    private String value;

    protected static UserId generate(){
        UserId id = new UserId();
        id.value = UUID.fromString("userId").toString();
        return id;
    }

}
