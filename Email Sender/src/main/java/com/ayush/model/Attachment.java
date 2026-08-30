package com.ayush.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Attachment {
    private String fileName;
    private byte[] data;

    public Attachment(String fileName, String pathName) {
        try {
            this.fileName = fileName;
            this.data = Files.readAllBytes(Paths.get(pathName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }
}
