package com.vang.main.repository;

import com.vang.main.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * CreatedDate: 26/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: FileDataRepository
 */
public interface FileDataRepository extends JpaRepository<FileData, Long> {

    @Query(value = "select fd.* from file_data fd where fd.file_name like ?1 and fd.deleted_date is null", nativeQuery = true)
    public List<FileData> searchOnlyByName(String name);

    @Modifying
    @Query(value = "update file_data set deleted_date = ?2 where id = ?1", nativeQuery = true)
    int deleteFileByFileId(Long fileId, Date deletedDate);
}