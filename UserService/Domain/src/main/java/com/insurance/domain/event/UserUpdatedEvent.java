package com.insurance.domain.event;

import com.insurance.domain.aggregate.UserAggregate;
import com.insurance.shared.base.BaseEvent;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserUpdatedEvent extends BaseEvent {

    public static final String USER_UPDATED_EVENT_V1 = "UserUpdatedEvent_V1";
    public static final String AGGREGATE_TYPE = UserAggregate.AGGREGATE_TYPE;

    @Size(min = 6, max = 15, message = "Username phải có từ 6 đến 15 ký tự")
    @NotBlank(message = "Không được để trống username")
    @Pattern(regexp = "\\S+", message = "Username không được chứa khoảng trắng")
    private String newUsername;

    @Size(min = 8, max = 15, message = "Password phải có từ 8 đến 15 ký tự")
    @NotBlank(message = "Không được để trống password")
    @Pattern(regexp = "\\S+", message = "Password không được chứa khoảng trắng")
    private String newPassword;

    private Integer newRole;

    @Builder
    public UserUpdatedEvent(String aggregateId, String updatedBy,String newUsername, String newPassword, Integer newRole) {
        super(aggregateId, updatedBy);
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.newRole = newRole;
    }
}
