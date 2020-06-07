package com.ndgndg91.todo.user.model.dto.request;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class CreateUserRequest {
    private String username;
    private String password;

    public void validate() {
        com.google.common.base.Preconditions.checkNotNull(username, "이메일은 필수값입니다.");
        com.google.common.base.Preconditions.checkNotNull(password, "비밀번호는 필수값입니다.");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .toString();
    }
}
