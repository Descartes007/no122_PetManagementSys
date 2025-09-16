package com.example.petback.common.Enum;

public enum AdoptEnum {
    ADOPTING("领养中"),
    NO_ADOPT("待领养"),
    PENDING_REVIEW("审核中"),
    REJECTED("审核不通过");

    private String info;

    AdoptEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}