package com.example.seckilling.dao;

import com.example.seckilling.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Xiao Yijia
 * @create 2020-09-15 10:32
 */

@Mapper
@Component
public interface UserDao {

    @Select("select * from user where id = #{id}")
    User getById(@Param("id") int id);

    @Insert("insert into user(id, name) values(#{id}, #{name})")
    void insert(User user);

}
