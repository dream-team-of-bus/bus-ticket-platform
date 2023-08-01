package com.bus.ticket.constant;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/31
 */
public enum UserTypeEnum {
    ADMIN((byte)-1, "管理员"), PASSENGER((byte)1, "乘客"), DRIVER((byte)2, "大巴司机");

    private byte type;

    private String name;

    UserTypeEnum(byte type, String name) {
        this.type = type;
        this.name = name;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
