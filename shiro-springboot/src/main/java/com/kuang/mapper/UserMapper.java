package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xxx
 * @version 1.0
 * @Description
 * @date 2022/8/16 20:33
 */
@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);

}
