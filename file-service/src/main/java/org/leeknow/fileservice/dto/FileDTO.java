package org.leeknow.fileservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {

    private String objectKey;
    private String originalFileName;
    private long size;
    private String contentType;
    private String fileType;
}
