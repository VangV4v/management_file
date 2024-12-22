package com.vang.common;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class BaseRootData {

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

}