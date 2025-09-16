package com.example.petback.common.Enum;

public enum FosterStatus {
   Fostering("寄养中") ,
    W_HANDLE("待处理") ,

   END("已领回");

    private  String info;

    FosterStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
