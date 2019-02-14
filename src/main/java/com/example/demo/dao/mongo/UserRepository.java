package com.example.demo.dao.mongo;

import com.example.demo.model.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XieLongzhen
 * @date 2019/2/14 10:22
 */
@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public SysUser save(SysUser sysUser) {
        return mongoTemplate.save(sysUser);
    }

    public List<SysUser> findAll() {
        return mongoTemplate.findAll(SysUser.class);
    }
}
