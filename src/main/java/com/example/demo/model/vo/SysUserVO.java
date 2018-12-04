package com.example.demo.model.vo;

import com.example.demo.model.entity.Permission;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.SysUser;

import java.util.List;

/**
 * @author XieLongzhen
 * @date 2018/12/1 16:51
 */
public class SysUserVO extends SysUser {
    private List<Role> roles;

    private List<Permission> permissions;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
