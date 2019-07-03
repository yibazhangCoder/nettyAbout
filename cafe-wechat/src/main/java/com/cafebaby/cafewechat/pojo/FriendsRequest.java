package com.cafebaby.cafewechat.pojo;

import java.io.Serializable;
import java.util.Date;

public class FriendsRequest implements Serializable {
    private String id;

    private String sendUserId;

    private String acceptUserId;

    private Date requestDateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId == null ? null : sendUserId.trim();
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId == null ? null : acceptUserId.trim();
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FriendsRequest other = (FriendsRequest) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSendUserId() == null ? other.getSendUserId() == null : this.getSendUserId().equals(other.getSendUserId()))
            && (this.getAcceptUserId() == null ? other.getAcceptUserId() == null : this.getAcceptUserId().equals(other.getAcceptUserId()))
            && (this.getRequestDateTime() == null ? other.getRequestDateTime() == null : this.getRequestDateTime().equals(other.getRequestDateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSendUserId() == null) ? 0 : getSendUserId().hashCode());
        result = prime * result + ((getAcceptUserId() == null) ? 0 : getAcceptUserId().hashCode());
        result = prime * result + ((getRequestDateTime() == null) ? 0 : getRequestDateTime().hashCode());
        return result;
    }
}