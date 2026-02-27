package org.leeknow.fileservice.dto;

import lombok.Data;

import java.io.InputStream;

@Data
public class FileDownloadDTO {
    private InputStream inputStream;
    private String fileName;
    private String contentType;
}
