package com.example.seckilling.dao;

import com.example.seckilling.domain.SeckillingUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Xiao Yijia
 * @create 2020/9/16 21:38
 */

@Mapper
@Component
public interface SeckillingUserDao {

    @Select("select * from seckilling_user where id = #{id}")
    SeckillingUser getById(@Param("id") long id);

}
