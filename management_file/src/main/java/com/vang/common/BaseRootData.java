package com.vang.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * CreatedDate: 21/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: BaseRootData
 */
@Getter
@Setter
@MappedSuperclass
public class BaseRootData {

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;
}