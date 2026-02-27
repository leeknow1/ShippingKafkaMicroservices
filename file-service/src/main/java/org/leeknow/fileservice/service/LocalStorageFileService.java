package org.leeknow.fileservice.service;

import org.leeknow.fileservice.dto.FileDownloadDTO;
import org.leeknow.fileservice.entity.File;
import org.leeknow.fileservice.enums.FileType;
import org.leeknow.fileservice.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.leeknow.fileservice.utils.FileUtils.extractFilename;
import static org.leeknow.fileservice.utils.FileUtils.validateFileName;

@Service
public class LocalStorageFileService implements FileService {

    private final Path path;

    private final FileRepository fileRepository;

    public LocalStorageFileService(@Value("${storage.local.path}") String path, FileRepository fileRepository) {
        this.path = Paths.get(path);
        this.fileRepository = fileRepository;
    }


    @Override
    public String save(String filename, MultipartFile file, FileType fileType, int objectId) throws IOException {
        String objectKey = UUID.randomUUID() + "_" + filename;

        File fileEntity = new  File();
        fileEntity.setFilename(objectKey);
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setObjectId(objectId);
        fileEntity.setType(fileType);
        fileRepository.save(fileEntity);

        Path target = resolvePath(fileType, objectId).resolve(objectKey);
        try(InputStream is = file.getInputStream()) {
            Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return objectKey;
    }

    @Override
    public FileDownloadDTO download(String filename, FileType fileType, int objectId) throws IOException {
        Optional<File> fileOptional = fileRepository.findByFilenameAndObjectIdAndType(validateFileName(filename), objectId, fileType);

        if (fileOptional.isPresent()) {
            Path target = resolvePath(fileType, objectId).resolve(filename);
            InputStream inputStream = Files.newInputStream(target, StandardOpenOption.READ);
            FileDownloadDTO fileDownloadDTO = new FileDownloadDTO();
            fileDownloadDTO.setFileName(extractFilename(filename));
            fileDownloadDTO.setContentType(fileOptional.get().getContentType());
            fileDownloadDTO.setInputStream(inputStream);
            return fileDownloadDTO;
        }

        return null;
    }

    @Override
    public boolean delete(String filename, FileType fileType, int objectId) throws IOException {
        long deleted = fileRepository.deleteByFilenameAndObjectIdAndType(validateFileName(filename), objectId, fileType);

        Path target = resolvePath(fileType, objectId).resolve(filename);
        return Files.deleteIfExists(target) && deleted > 0;
    }

    @Override
    public List<String> findFilesInDirectory(FileType fileType, int objectId) throws IOException {
        Path resolvePath = resolvePath(fileType, objectId);

        if (!Files.exists(resolvePath)) {
            return List.of();
        }

        try (Stream<Path> streamList = Files.list(resolvePath)) {
            return streamList.filter(Files::isRegularFile).map(s -> s.getFileName().toString()).toList();
        }
    }

    private Path resolvePath(FileType fileType, int objectId) throws IOException {
        Path resolved = path
                .resolve(fileType.name().toLowerCase())
                .resolve(String.valueOf(objectId));

        Files.createDirectories(resolved);
        return resolved;
    }
}
