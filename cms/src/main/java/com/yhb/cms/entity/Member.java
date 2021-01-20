package com.yhb.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fusu
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("member")
public class Member implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("ID")
    private Long id;

    @TableField("NAME")
    private String name;

    @TableField("AGE")
    private String age;


}
