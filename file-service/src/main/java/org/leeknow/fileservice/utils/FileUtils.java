package org.leeknow.fileservice.utils;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FileUtils {

    private static final Pattern SAVE_FILENAME = Pattern.compile("^[a-zA-Z0-9._-]{1,255}$");

    public static String validateFileName(String fileName) {

        String base = Paths.get(fileName).getFileName().toString();

        if (base.contains("..") || base.contains("/") || base.contains("\\") ) {
            throw new IllegalArgumentException("file.illegal_filename"); //TODO messages
        }

        if (!SAVE_FILENAME.matcher(base).matches()) {
            throw new IllegalArgumentException("file.illegal_filename");
        }

        return base;
    }

    public static String extractFilename(String filename) {
        if (filename.contains("_")) {
            filename = filename.substring(filename.indexOf("_"), filename.length() - 1);
        }
        return filename;
    }
}
