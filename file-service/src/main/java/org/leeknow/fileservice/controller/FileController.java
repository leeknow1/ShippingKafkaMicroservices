package org.leeknow.fileservice.controller;

import lombok.RequiredArgsConstructor;
import org.leeknow.fileservice.dto.FileDTO;
import org.leeknow.fileservice.dto.FileDownloadDTO;
import org.leeknow.fileservice.enums.FileType;
import org.leeknow.fileservice.service.FileService;
import org.leeknow.fileservice.utils.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("type") FileType fileType,
                                        @AuthenticationPrincipal Jwt jwt) throws IOException {
        int userId = Integer.parseInt(jwt.getSubject());

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("file.no_file"); //TODO messages
        }

        String validatedFileName = FileUtils.validateFileName(file.getOriginalFilename());

        String objectKey = fileService.save(validatedFileName, file, fileType, userId);

        return ResponseEntity.ok(FileDTO
                .builder()
                .objectKey(objectKey)
                .originalFileName(validatedFileName)
                .size(file.getSize())
                .contentType(Optional.ofNullable(file.getContentType()).orElse("application/octet-stream"))
                .fileType(fileType.name())
                .build());
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename,
                                                 @RequestParam("type") FileType fileType,
                                                 @RequestParam("objectId") int objectId) throws IOException {
        FileDownloadDTO fileDownloadDTO = fileService.download(filename, fileType, objectId);

        if (fileDownloadDTO != null) {
            String encoded = URLEncoder.encode(fileDownloadDTO.getFileName(), StandardCharsets.UTF_8).replace("+", "%20");
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(fileDownloadDTO.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDownloadDTO.getFileName() + "\"; filename*=UTF-8''" + encoded)
                    .body(new InputStreamResource(fileDownloadDTO.getInputStream()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<?> deleteFile(@PathVariable("filename") String filename,
                                        @RequestParam("type") FileType fileType,
                                        @RequestParam("objectId") int objectId) throws IOException {
        boolean deleted = fileService.delete(filename, fileType, objectId);
        return ResponseEntity.ok(Map.of("deleted", deleted));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getFilesInDirectory(@RequestParam("type") FileType fileType,
                                                 @RequestParam("objectId") int objectId) throws IOException {
        List<String> filesInDirectory = fileService.findFilesInDirectory(fileType, objectId);
        return ResponseEntity.ok(Map.of("files", filesInDirectory));
    }
}
