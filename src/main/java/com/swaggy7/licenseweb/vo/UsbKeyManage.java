package com.swaggy7.licenseweb.vo;

public class UsbKeyManage {

    private String usbName;

    private String usbNumber;

    public String getUsbName() {
        return usbName;
    }

    public void setUsbName(String usbName) {
        this.usbName = usbName;
    }

    public String getUsbNumber() {
        return usbNumber;
    }

    public void setUsbNumber(String usbNumber) {
        this.usbNumber = usbNumber;
    }

    @Override
    public String toString() {
        return "UsbKeyManage{" +
                "usbName='" + usbName + '\'' +
                ", usbNumber='" + usbNumber + '\'' +
                '}';
    }
}
