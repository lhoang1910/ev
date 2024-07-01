package com.insurance.domain.entity;

import com.demo.shared.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    private String id;

    @Column(unique = true)
    private String userCode;
    private String username;
    private String password;

    @Column(columnDefinition = "INTEGER DEFAULT 1")
    private int role;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;

}

