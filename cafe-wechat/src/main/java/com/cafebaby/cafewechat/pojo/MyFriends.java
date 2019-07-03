package com.cafebaby.cafewechat.pojo;

import java.io.Serializable;

public class MyFriends implements Serializable {
    private String id;

    private String myUserId;

    private String myFriendUserId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId == null ? null : myUserId.trim();
    }

    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId == null ? null : myFriendUserId.trim();
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
        MyFriends other = (MyFriends) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMyUserId() == null ? other.getMyUserId() == null : this.getMyUserId().equals(other.getMyUserId()))
            && (this.getMyFriendUserId() == null ? other.getMyFriendUserId() == null : this.getMyFriendUserId().equals(other.getMyFriendUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMyUserId() == null) ? 0 : getMyUserId().hashCode());
        result = prime * result + ((getMyFriendUserId() == null) ? 0 : getMyFriendUserId().hashCode());
        return result;
    }
}