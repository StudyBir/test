package com.swaggy7.licenseweb.bean;

public class MsgToServer {
    String licenseName;
    String rbId;

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getRbId() {
        return rbId;
    }

    public void setRbId(String rbId) {
        this.rbId = rbId;
    }

    @Override
    public String toString() {
        return "MsgToServer{" +
                "licenseName='" + licenseName + '\'' +
                ", rbId='" + rbId + '\'' +
                '}';
    }
}
