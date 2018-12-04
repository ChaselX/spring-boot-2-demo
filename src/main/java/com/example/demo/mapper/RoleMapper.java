package com.example.demo.mapper;

import com.example.demo.model.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author XieLongzhen
 * @date 2018/12/4 14:36
 */
public interface RoleMapper {
    @Select("select r.id, r.code, r.name, r.remark from role r where r.id in (select ur.role_id from user_role ur where ur.user_id = #{userId})")
    List<Role> getRolesByUserId(Long userId);
}
