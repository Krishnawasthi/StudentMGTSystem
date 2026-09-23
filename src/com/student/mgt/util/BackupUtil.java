package com.student.mgt.util;

import com.student.mgt.config.AppConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupUtil {

    public static String createDataBackup(String sourceFilePath) throws IOException {
        File srcFile = new File(sourceFilePath);
        if (!srcFile.exists()) {
            throw new IOException("Source data file does not exist: " + sourceFilePath);
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File backupDir = new File("backups");
        if (!backupDir.exists()) {
            backupDir.mkdirs();
        }

        String destFileName = "backups/students_backup_" + timestamp + ".dat";
        File destFile = new File(destFileName);

        try (FileInputStream in = new FileInputStream(srcFile);
             FileOutputStream out = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
        AppLogger.info("Created backup file: " + destFile.getAbsolutePath());
        return destFile.getName();
    }
}
