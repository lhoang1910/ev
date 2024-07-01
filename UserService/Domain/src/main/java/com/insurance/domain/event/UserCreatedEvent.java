package com.insurance.domain.event;

import com.insurance.domain.aggregate.UserAggregate;
import com.insurance.shared.base.BaseEvent;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserCreatedEvent extends BaseEvent {

    public static final String USER_CREATED_EVENT_V1 = "UserCreatedEvent_V1";
    public static final String AGGREGATE_TYPE = UserAggregate.AGGREGATE_TYPE;

    @Builder
    public UserCreatedEvent(String userCode, String aggregateId, String updatedBy, String username, String password, Integer role, Boolean isDeleted) {
        super(aggregateId, updatedBy);
        this.userCode  = userCode;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isDeleted = isDeleted;
    }

    private String userCode;

    @Size(min = 6, max = 15, message = "Username phải có từ 6 đến 15 ký tự")
    @NotBlank(message = "Không được để trống username")
    @Pattern(regexp = "\\S+", message = "Username không được chứa khoảng trắng")
    private String username;

    @Size(min = 8, max = 15, message = "Password phải có từ 8 đến 15 ký tự")
    @NotBlank(message = "Không được để trống password")
    @Pattern(regexp = "\\S+", message = "Password không được chứa khoảng trắng")
    private String password;

    private Integer role;
    private Boolean isDeleted = false;
}
