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
    public final URI APP_URI;
    public final Path PUBLIC_HTML;
    public final Path STORAGE_PATH;

    public static final String USER_ROLE_PREXIX = "ROLE_";

    public WebProperties(Environment env) throws URISyntaxException, IOException {
        this.APP_URI = new URI(env.getProperty("app.web.app-uri", "http://localhost:8080"));
        this.PUBLIC_HTML = Path.of(env.getProperty("app.web.public-html", "./public"));
        this.STORAGE_PATH = Path.of(env.getProperty("app.web.storage-path", PUBLIC_HTML.resolve("storage").toString()));

        if (!Files.isDirectory(STORAGE_PATH))
            Files.createDirectories(STORAGE_PATH);
    }

    public String publicHtmlPath(Path path) {
        return "/" + PUBLIC_HTML.relativize(path).normalize().toString();
    }

    public URI appURI(String relative) {
        return APP_URI.resolve(relative);
    }
    
    public URI appURI(URI relative) {
        return APP_URI.resolve(relative);
    }
}
