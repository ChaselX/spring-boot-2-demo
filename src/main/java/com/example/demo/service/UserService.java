package com.example.demo.service;

import com.example.demo.model.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author XieLongzhen
 * @date 2018/10/8 8:59
 */
public interface UserService extends UserDetailsService {
    List<SysUser> getAll();

    boolean addUser(SysUser sysUser);
}
