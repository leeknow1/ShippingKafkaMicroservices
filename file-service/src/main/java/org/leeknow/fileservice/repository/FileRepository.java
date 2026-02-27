package org.leeknow.fileservice.repository;

import org.leeknow.fileservice.entity.File;
import org.leeknow.fileservice.enums.FileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {

    Optional<File> findByFilenameAndObjectIdAndType(String filename, int objectId, FileType fileType);

    long deleteByFilenameAndObjectIdAndType(String filename, int objectId, FileType fileType);
}
