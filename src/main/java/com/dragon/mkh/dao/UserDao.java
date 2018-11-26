package com.dragon.mkh.dao;

import com.dragon.mkh.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {


    @Select("SELECT id,user_name,password,nick_name,age,sex,phone,email,head_image,del_flag,create_time,update_time FROM s_user")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="userName",column="user_name"),
            @Result(property="password",column="PASSWORD"),
            @Result(property="nickName",column="nick_name"),
            @Result(property="age",column="age"),
            @Result(property="sex",column="sex"),
            @Result(property="phone",column="phone"),
            @Result(property="email",column="email"),
            @Result(property="headImage",column="head_image"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="delFlag",column="del_flag")
    })
     List<User> findAllUser() ;


}
