package com.fsl.gabriel.repository;

import com.fsl.gabriel.entity.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("user")
public interface UserRepository extends BaseMapper<User> {
    List<User> selectByTest();
}
