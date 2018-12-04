package com.example.demo.mapper;

import com.example.demo.model.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author XieLongzhen
 * @date 2018/12/4 14:58
 */
public interface PermissionMapper {
    @Select("SELECT p.id, p.code, p.name FROM permission p WHERE p.id IN (" +
            "SELECT rp.permission_id FROM role_permission rp WHERE rp.role_id in(" +
            "SELECT ur.role_id FROM user_role ur WHERE ur.user_id = #{userId}))")
    List<Permission> getPermissionsByUserId(Long userId);
}
