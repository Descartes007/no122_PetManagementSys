package com.example.petback.utils;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    private final static  Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    public final static String FILE_BASE_PATH = System.getProperty("user.dir") + "/files/";
    // 获取项目根目录路径
    public static Path getProjectRootPath() throws IOException {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:.");
        if (resources.length == 0) {
            throw new IOException("Cannot find project root path.");
        }
        // 通常第一个资源就是项目的根目录
        File rootDir = resources[0].getFile();
        return rootDir.toPath();
    }
    public static String saveImage(MultipartFile file, String filePath) {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String dFileName = timestamp + "-" + uuid + extension;

        // 获取项目根目录路径
        Path projectRootPath = Paths.get(FILE_BASE_PATH);

        // 检查filePath是否为空，如果为空则使用根目录
        Path targetDirectory = (filePath == null || filePath.isEmpty()) ? projectRootPath.resolve("img/") : projectRootPath.resolve("img/"+filePath);

        try {
            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory); // 如果目录不存在，则创建目录
            }
            Path uploadFilePath = targetDirectory.resolve(dFileName);
            File uploadFile = uploadFilePath.toFile();

            file.transferTo(uploadFile);
            System.out.println("File saved at: " + uploadFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // 返回相对路径，以便在Web应用中访问
        return (filePath == null || filePath.isEmpty()) ? "/img/" + dFileName : filePath + "/" + dFileName;
    }

    public static boolean deleteFile(String relativeFilePath) {
        // 获取项目根目录路径
        Path projectRootPath = Paths.get(FILE_BASE_PATH);

        // 构造文件绝对路径
        Path absoluteFilePath = projectRootPath.resolve("img/" + relativeFilePath);

        try {
            // 删除文件
            Files.delete(absoluteFilePath);
            System.out.println("File deleted: " + absoluteFilePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static String generateFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String dFileName = timestamp + "-" + uuid + extension;
        return dFileName;
    }

    public static String removePartFromUrlUsingJSON(String url, String part) {
        JSONObject jsonObject = JSON.parseObject("{\"url\":\"" + url + "\"}");
        String modifiedUrl = jsonObject.getString("url");
        if (modifiedUrl.contains(part)) {
            modifiedUrl = modifiedUrl.substring(0, modifiedUrl.indexOf(part)) + modifiedUrl.substring(modifiedUrl.indexOf(part) + part.length());
            modifiedUrl = modifiedUrl.endsWith("/") ? modifiedUrl.substring(0, modifiedUrl.length() - 1) : modifiedUrl;
            jsonObject.put("url", modifiedUrl);
        }
        return jsonObject.getString("url");
    }


}
