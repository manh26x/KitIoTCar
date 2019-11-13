package com.kit.mike.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Device", uniqueConstraints = @UniqueConstraint(name="Device_UK", columnNames = {"User_Id"}))
public class Device {

    @Id
    @Column(name="Device_Id", nullable = false)
    private Long Device_Id;

    @Column(name="Device_Name",length = 64, nullable = false)
    private String  deviceName;

    @Column(name="Decription", length = 200)
    private String decryption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="User_Id")
    private AppUser appUser;

    public Long getDevice_Id() {
        return Device_Id;
    }

    public void setDevice_Id(Long device_Id) {
        Device_Id = device_Id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDecryption() {
        return decryption;
    }

    public void setDecryption(String decryption) {
        this.decryption = decryption;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getTopicId(List<Topic> tpList, Long Device_Id) {
        for(Topic tp: tpList)
            if(tp.getDevice().getDevice_Id().equals(Device_Id))
                return tp.getId().toString();

        return "";
    }
    @Override
    public String toString() {
        return this.getDevice_Id() + " / " + this.getDeviceName() + " / " + this.getDecryption() + " / " + this.getAppUser().getUserName();
    }
}
