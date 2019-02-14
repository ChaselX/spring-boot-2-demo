package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.entity.Permission;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.SysUser;
import com.example.demo.model.vo.SysUserVO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XieLongzhen
 * @date 2018/10/8 8:59
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserVO userVO = userMapper.getDetailsByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : userVO.getRoles()
        ) {
            authorities.add(new SimpleGrantedAuthority(Role.PREFIX + role.getCode()));
        }
        for (Permission permission : userVO.getPermissions()
        ) {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        }
//        return new User(userVO.getUsername(), userVO.getPassword(), authorities);
        return User.builder().username(userVO.getUsername()).password(userVO.getPassword()).authorities(authorities).disabled(!userVO.isEnabled()).build();
    }

    @Override
    public List<SysUser> getAll() {
        return userMapper.getAll();
    }

    @Override
    public boolean addUser(SysUser sysUser) {
        sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        return userMapper.add(sysUser) > 0;
    }
}
