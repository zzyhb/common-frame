package com.yhb.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhb.cms.entity.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fusu
 * @since 2021-01-20
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

}
