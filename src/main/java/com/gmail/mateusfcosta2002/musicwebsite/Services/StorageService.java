package com.gmail.mateusfcosta2002.musicwebsite.Services;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.stereotype.Component;
import com.gmail.mateusfcosta2002.musicwebsite.WebProperties;

@Component
public class StorageService {
    private Path storagePath;
    public StorageService(WebProperties webProperties) {
        this.storagePath = webProperties.STORAGE_PATH;
    }

    public static String uniqueFilename(String baseName) {
        return UUID.randomUUID().toString() + "-" + baseName;
    }

    public boolean deleteIfExists(Path path) throws IOException {
        return Files.deleteIfExists(path);
    }

    public void delete(Path path) throws IOException {
        Files.delete(path);
    }

    public Path move(Path src, Path dest, CopyOption... options) throws IOException {
        return Files.move(src, dest, options);
    }

    public Path uploadFileRandomName(InputStream input, Path basepath) throws IOException {
        var uniqueName = uniqueFilename("");
        return uploadFile(input, basepath, uniqueName);
    }

    public Path uploadFileRandomName(InputStream input, Path basePath, String baseName) throws IOException {
        var uniqueName = uniqueFilename(baseName);
        return uploadFile(input, basePath, uniqueName);
    }

    public Path uploadFile(InputStream input, Path basePath, String filename) throws IOException {
        var finalPath = storagePath.resolve(basePath).resolve(filename);
        var parent = finalPath.toFile().getParentFile();

        if (!parent.isDirectory() && !parent.mkdirs()) {
            throw new IOException("Failed to upload file: Could not create parent path " + parent.toString());
        }

        try (var output = new BufferedOutputStream(new FileOutputStream(finalPath.toFile()))) {
            input.transferTo(output);
        }

        return finalPath;
    }
}
