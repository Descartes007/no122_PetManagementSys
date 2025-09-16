package com.example.petback.common.Enum;

public enum UserRole {
    ADMIN(1, "ADMIN", "管理员"),
    USER(2, "USER", "用户"),
    SUPER_ADMIN(3, "SUPER_ADMIN", "超级管理员");

    private Integer id;
    private String value;
    private String name;

    UserRole(Integer id, String value, String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }

    // 可以添加getter方法来访问枚举的属性
    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}