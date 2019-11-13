package com.kit.mike.entity;




import javax.persistence.*;

@Entity
@Table(name="APP_USER" , //
    uniqueConstraints = {
        @UniqueConstraint(name="APP_USER_UK", columnNames = "User_Name")
    })
public class AppUser {

    @Id
    @Column(name="User_Id", nullable = false)
    private Long userId;

    @Column(name="USER_NAME", length=36, nullable = false)
    private String userName;

    @Column(name = "FULL_NAME", length=50, nullable = false)
    private String fullName;

    @Column(name="ENCRYTED_PASSWORD", length=128, nullable = false)
    private String encrytedPassword;

    @Column(name="ENABLED", length = 1, nullable = false)
    private boolean enable;

    public AppUser() {

    }


    public AppUser(Long userId, String userName, String fullName, String encrytedPassword, boolean enable) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.encrytedPassword = encrytedPassword;
        this.enable = enable;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return this.getUserName() + "/" + this.getFullName() + " / " + this.getEncrytedPassword();
    }
}
