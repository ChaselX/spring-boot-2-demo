package com.example.demo.dao;

import com.example.demo.model.entity.SysUser;
import com.example.demo.model.vo.SysUserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author XieLongzhen
 * @date 2018/10/7 17:53
 */
public interface UserMapper {
    @Insert("INSERT INTO user(username, password, name, mobile) VALUES(#{username}, #{password}, #{name}, #{mobile})")
//    返回插入记录的主键id
//    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int add(SysUser sysUser);

    @Select("SELECT * from user")
    List<SysUser> getAll();

    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(property = "roles", column = "id", many = @Many(select = "com.example.demo.dao.RoleMapper.getRolesByUserId")),
            @Result(property = "permissions", column = "id", many = @Many(select = "com.example.demo.dao.PermissionMapper.getPermissionsByUserId"))
    })
    SysUserVO getDetailsByUsername(String username);
}
