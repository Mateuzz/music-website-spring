package com.gmail.mateusfcosta2002.musicwebsite.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.mime.MediaType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class FileDetector {
    private TikaConfig tika;

    private static final Map<String, String> extensionsFromAudioTypes = Map.of(
        "aac", ".aac",
        "mpeg", ".mp3",
        "mp4", ".mp4",
        "ogg", ".ogg",
        "wav", ".wav",
        "webm", ".webm"
    );

    public FileDetector() throws TikaException, IOException {
        this.tika = new TikaConfig();
    }

    public MediaType getMediaType(InputStream inputStream, String filename) throws IOException {
        var metadata = new Metadata();

        metadata.set(TikaCoreProperties.RESOURCE_NAME_KEY, filename);

        return tika.getDetector()
            .detect(inputStream, metadata);
    } 

    public static String getAudioExtension(MediaType type) {
        return extensionsFromAudioTypes.get(type.getSubtype());
    }

    @Cacheable
    public static String getAcceptedAudioMimeTypes() {
        return extensionsFromAudioTypes.keySet().toString();
    }
}
