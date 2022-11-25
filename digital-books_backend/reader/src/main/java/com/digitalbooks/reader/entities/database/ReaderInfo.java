package com.digitalbooks.reader.entities.database;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ReaderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "READER_ID")
    private Long readerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL_ID", unique = true)
    private String emailId;

    @OneToMany(mappedBy = "parentReaderInfo",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReaderSubscription> ReaderSubscriptionList;

    @OneToMany(mappedBy = "parentReaderInfo",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReaderNotification> ReaderNotificationList;

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<ReaderSubscription> getReaderSubscriptionList() {
        return ReaderSubscriptionList;
    }

    public void setReaderSubscriptionList(List<ReaderSubscription> readerSubscriptionList) {
        ReaderSubscriptionList = readerSubscriptionList;
    }

    public List<ReaderNotification> getReaderNotificationList() {
        return ReaderNotificationList;
    }

    public void setReaderNotificationList(List<ReaderNotification> readerNotificationList) {
        ReaderNotificationList = readerNotificationList;
    }
}
