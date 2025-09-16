package com.example.petback.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TransportationUtils {
    public static String generateTrackingNumber() {
        // 使用日期和随机数生成单号
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datePart = LocalDate.now().format(formatter);
        Random random = new Random();
        // 生成4位随机数
        String randomPart = String.format("%04d", random.nextInt(10000));
        return "T" + datePart + randomPart; // 前缀"T"表示运输单号
    }
}
