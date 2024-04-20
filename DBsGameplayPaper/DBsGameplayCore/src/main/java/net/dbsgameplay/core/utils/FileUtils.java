package net.dbsgameplay.core.utils;

import java.io.File;

public class FileUtils {
    public static void createParentDirectories(File file) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }
}