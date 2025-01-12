package com.vang.main.repository;

import com.vang.main.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * CreatedDate: 26/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: FileDataRepository
 */
public interface FileDataRepository extends JpaRepository<FileData, Long> {

    @Query(value = "select * from file_data where file_name like ?1", nativeQuery = true)
    public List<FileData> searchOnlyByName(String name);
}