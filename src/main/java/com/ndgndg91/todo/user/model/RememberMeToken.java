package com.ndgndg91.todo.user.model;

import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "remember_me_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class RememberMeToken {

    @Id
    private String series;
    private String username;
    private String token;
    private Date lastUsed;

    public void updateToken(String series, String tokenValue, Date lastUsed) {
        this.series = series;
        this.token = tokenValue;
        this.lastUsed = lastUsed;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .toString();
    }
}
