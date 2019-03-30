package com.example.hp.materialdesign.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Channel implements Serializable {
    static final long serialVersionUID = 3;
    public Channel(String channelId, String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }
    @Generated(hash = 308675568)
    public Channel(Long id, String channelId, String channelName) {
        this.id = id;
        this.channelId = channelId;
        this.channelName = channelName;
    }
    @Generated(hash = 459652974)
    public Channel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getChannelId() {
        return this.channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getChannelName() {
        return this.channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    @Id
    Long id;
    @Unique
    String channelId;
    String channelName;
}
