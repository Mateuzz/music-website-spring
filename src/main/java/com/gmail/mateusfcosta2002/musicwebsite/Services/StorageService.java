package com.gmail.mateusfcosta2002.musicwebsite.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;

@Component
public class StorageService {
    private Path storagePath;

    public StorageService(WebProperties webProperties) {
        this.storagePath = webProperties.getStoragePath();
    }

    public static String uniqueFilename(String baseName) {
        return UUID.randomUUID().toString() + "-" + baseName;
    }

    public void deleteFile(Path path) throws IOException {
        Files.delete(path);
    }

    public Path uploadFileRandom(MultipartFile file, Path basepath) throws IOException {
        var uniqueName = uniqueFilename(file.getOriginalFilename());
        return uploadFile(file, basepath, uniqueName);
    }

    public Path uploadFileRandom(MultipartFile file, Path basePath, String baseName) throws IOException {
        var uniqueName = uniqueFilename(baseName);
        return uploadFile(file, basePath, uniqueName);
    }

    public Path uploadFile(MultipartFile file, Path basePath, String filename) throws IOException {
        var finalPath = storagePath.resolve(basePath).resolve(filename);
        var parent = finalPath.toFile().getParentFile();

        if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException("Failed to upload file: Could not create parent path " + parent.toString());
        }

        file.transferTo(finalPath);
        return finalPath;
    }
}
