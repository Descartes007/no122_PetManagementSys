package com.example.petback.common.Enum;

public enum AnimalEnum {
    ADOPTING("领养中"),
    NO_ADOPT("待领养"),   ADOPT_CANCEL("放弃领养");

    private String info;
    AnimalEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
