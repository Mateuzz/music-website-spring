package com.gmail.mateusfcosta2002.musicwebsite;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class WebProperties {
    private URI appUri;
    private Path publicHtml;
    private Path storagePath;

    public static final String USER_ROLE_PREXIX = "ROLE_";

    public WebProperties(Environment env) throws URISyntaxException, IOException {
        this.appUri = new URI(env.getProperty("app.web.app-uri", "http://localhost:8080"));
        this.publicHtml = Path.of(env.getProperty("app.web.public-html", "./public"));
        this.storagePath = Path.of(env.getProperty("app.web.storage-path", publicHtml.resolve("storage").toString()));

        if (!Files.isDirectory(storagePath))
            Files.createDirectories(storagePath);
    }

    public URI getAppUri() {
        return appUri;
    }

    public String getFilepathRelativeToPublic(Path path) {
        return "/" + publicHtml.relativize(path).normalize().toString();
    }

    public Path getPublicHtml() {
        return publicHtml;
    }

    public Path getStoragePath() {
        return storagePath;
    }
}
