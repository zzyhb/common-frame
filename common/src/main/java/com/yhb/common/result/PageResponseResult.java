package com.yhb.common.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fusu
 * @since 2021/1/16 12:50 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResponseResult<T> extends ResponseResult<T>{

    private Integer total;

    private Integer pageSize;

    private Integer pageNumber;

}
