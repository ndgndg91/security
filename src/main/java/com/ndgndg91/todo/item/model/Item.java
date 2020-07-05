package com.ndgndg91.todo.item.model;


import com.ndgndg91.todo.user.model.User;
import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@Table(name = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todoIdGen")
    @SequenceGenerator(name = "todoIdGen", sequenceName = "TODO_ID_SEQ", allocationSize = 20)
    private long id;

    private String todoName;
    private String details;

    private boolean done;

    private LocalDateTime due;
    private LocalDateTime createdTime;

    private long userId;

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .toString();
    }
}
