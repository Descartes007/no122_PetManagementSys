package com.example.petback.config;


import com.example.petback.utils.FileUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path projectRootPath = Paths.get(FileUtil.FILE_BASE_PATH);
        String imgFolderPath = projectRootPath.resolve("img").toAbsolutePath().toString();
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + imgFolderPath + "/");
    }
}
