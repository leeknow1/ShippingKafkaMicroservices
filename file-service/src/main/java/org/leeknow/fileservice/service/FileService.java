package org.leeknow.fileservice.service;

import org.leeknow.fileservice.dto.FileDownloadDTO;
import org.leeknow.fileservice.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    String save(String filename, MultipartFile file, FileType fileType, int objectId) throws IOException;

    FileDownloadDTO download(String filename, FileType fileType, int objectId) throws IOException;

    boolean delete(String filename, FileType fileType, int objectId) throws IOException;

    List<String> findFilesInDirectory(FileType fileType, int objectId) throws IOException;
}
