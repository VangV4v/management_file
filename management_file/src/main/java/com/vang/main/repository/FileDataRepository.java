package com.vang.main.repository;

import com.vang.main.entities.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CreatedDate: 26/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: FileDataRepository
 */
public interface FileDataRepository extends JpaRepository<FileData, Long> {
}