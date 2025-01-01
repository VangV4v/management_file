package com.vang.main.entities;

import com.vang.common.BaseRootData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedDate: 26/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: FileData
 */
@Entity
@Table(name = FileData.TABLE_NAME, schema = "public")
@Setter
@Getter
@NoArgsConstructor
public class FileData extends BaseRootData {

    public static final String TABLE_NAME = "FILE_DATA";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_file_data_g")
    @SequenceGenerator(name = "seq_file_data_g", sequenceName = "SEQ_" + TABLE_NAME, allocationSize = 1)
    private Long fileId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;
}