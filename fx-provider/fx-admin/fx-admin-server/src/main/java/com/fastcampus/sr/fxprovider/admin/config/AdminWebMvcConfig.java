package com.fastcampus.sr.fxprovider.admin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class AdminWebMvcConfig implements WebMvcConfigurer {
    public static final String INDEX_PAGE = "/index.html";
    private final StaticResourcePathStrategy staticResourcePathStrategy;

    public AdminWebMvcConfig(StaticResourcePathStrategy staticResourcePathStrategy) {
        this.staticResourcePathStrategy = staticResourcePathStrategy;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(INDEX_PAGE);
        registry.addViewController("/page/**").setViewName(INDEX_PAGE);
        registry.addViewController("/login/form").setViewName(INDEX_PAGE);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var resourcePath = staticResourcePathStrategy.getStaticResourcePath();

        registry.addResourceHandler(INDEX_PAGE)
                .setCacheControl(CacheControl.noStore().mustRevalidate().sMaxAge(0, TimeUnit.SECONDS))
                .addResourceLocations(resourcePath);

        registry.addResourceHandler("/**")
                .addResourceLocations(resourcePath);
    }

    @Component
    @ConditionalOnProperty(value = "admin.static-resource", havingValue = "local")
    public static class LocalStaticResourcePathStrategy implements StaticResourcePathStrategy {

        @Override
        public String getStaticResourcePath() {
            return String.format("file:%s/dist/", localWebClientPath());
        }

        private String localWebClientPath() {
            var adminModulePath = "fx-admin";
            var serverModulePath = "fx-admin-server";
            var frontModulePath = "fx-admin-front";
            var userDir = new File(System.getProperty("user.dir"));
            File clientDir;
            if (userDir.getName().equals(serverModulePath)) {
                userDir = userDir.getParentFile();
                clientDir = searchDir(userDir, frontModulePath);
            } else {
                var adminModuleDirs = searchDir(userDir, adminModulePath);
                clientDir = searchDir(adminModuleDirs, frontModulePath);
            }

            return clientDir.getPath();
        }

        private File searchDir(File target, String directoryName) {
            var targetDirectory = Stream.of(target.listFiles()).filter(el -> el.isDirectory() && el.getName().equals(directoryName))
                    .collect(Collectors.toList());

            if (targetDirectory.isEmpty()) {
                throw new IllegalStateException("Target directory not found.");
            }

            return targetDirectory.get(0);
        }
    }

    @Component
    @ConditionalOnProperty(value = "admin.static-resource", havingValue = "server", matchIfMissing = true)
    public static class ServerStaticResourcePathStrategy implements StaticResourcePathStrategy {

        @Override
        public String getStaticResourcePath() {
            return "classpath:/static/";
        }
    }

    interface StaticResourcePathStrategy {
        String getStaticResourcePath();
    }
}
