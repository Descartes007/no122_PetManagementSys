package com.example.petback.common.Enum;

public enum SubmitEnum {
    NO("待处理"),
    YES("已处理");
    private String info;

    SubmitEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
