package com.vang.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * CreatedDate: 25/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: BaseService
 */
@Getter
@Setter
public class BaseService {

    protected String currentDateTime;

    {
        currentDateTime = VangUtils.getStringDateByFormat(new Date(), BaseConstant.DateTimeConstant.YYYY_MM_DD_HH_MM);
    }
}