package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

  @Select("SELECT * FROM USERS")
  List<User> getAll();

  @Select("SELECT * FROM USERS WHERE username = #{username}")
  User getUser(String username);

  @Select("SELECT * FROM USERS WHERE userid = #{userId}")
  User getUserById(Integer userId);

  @Insert("INSERT INTO USERS (username,salt,password,firstname,lastname) " +
      "VALUES (#{username},#{salt},#{password},#{firstName},#{lastName})")
  @Options(useGeneratedKeys = true,keyProperty = "userId")
  Integer insert(User user);

  @Delete("DELETE FROM USERS WHERE userid = #{id}")
  void delete(Integer id);
}
