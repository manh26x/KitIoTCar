package com.kit.mike.entity;

import javax.persistence.*;

@Entity
@Table(name="APP_ROLE", //
    uniqueConstraints = {
        @UniqueConstraint(name="APP_USER_UK", columnNames = "Role_Name")
    })
public class AppRole {

    @Id
    @GeneratedValue
    @Column(name="Role_Id", nullable = false)
    private long id;

    @Column(name="Role_Name", length=30, nullable = false)
    private String roleName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
