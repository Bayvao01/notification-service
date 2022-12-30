package com.notification.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "notification_tbl")
public class Notification implements Serializable {

    @Id
    @Column(name = "notification_name")
    private String name;

    @Column(name = "application")
    private String application;

    @Column(name = "subject")
    private String subject;

    @Column(name = "template")
    private String template;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "creation_modification_time")
    private Timestamp createUpdateTime;
}
