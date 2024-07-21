package com.gmail.mateusfcosta2002.musicwebsite.Util;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class FileUtils {
    public static boolean createFileRecursive(URI uri) throws IOException {
        var file = new File(uri);
        var parent = file.getParentFile();
        parent.mkdirs();
        return file.createNewFile();
    }

    public static String replaceExtension(String filename, String newExtension) {
        var extIndex = filename.indexOf('.');
        if (extIndex != -1) 
            return filename.substring(0, filename.indexOf('.')) + newExtension;

        return filename + newExtension;
    }
}
