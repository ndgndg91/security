package com.ndgndg91.todo.item.model.response;

import lombok.Getter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public final class ItemResponse {
    private final long id;

    private final String name;
    private final String details;

    private final String due;
    private final String createdTime;

    private final long userId;

    public ItemResponse(long id, String name, String details, LocalDateTime due, LocalDateTime createdTime, long userId) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.due = due.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.createdTime = createdTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .toString();
    }
}
