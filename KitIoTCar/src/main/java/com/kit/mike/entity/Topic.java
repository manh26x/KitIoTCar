package com.kit.mike.entity;

import javax.persistence.*;

@Entity
@Table(name="Topic", //
    uniqueConstraints = @UniqueConstraint(name = "TOPIC_UK", columnNames = {"User_Id", "Device_Id"}))
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Topic_Id", nullable = false)
    private Long Id;

    @Column(name="Topic_Name", nullable = false)
    private String topicName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="User_Id", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Device_Id", nullable = false)
    private Device device;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
