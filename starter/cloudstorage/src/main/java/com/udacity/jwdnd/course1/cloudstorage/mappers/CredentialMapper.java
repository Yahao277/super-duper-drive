package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {
  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
  Credential[] getCredentials(Integer userId);

  @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
  Credential getCredential(Integer credentialId);

  @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) " +
      "VALUES (#{url},#{username},#{key},#{password},#{userId})")
  @Options(useGeneratedKeys = true,keyProperty = "credentialId")
  Integer insert(Credential credential);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id}")
  void delete(Integer id);

  @Update("UPDATE CREDENTIALS SET url = #{url}, key = #{key}, password = #{password}, username = #{newUserName} WHERE credentialid = #{credentialId}")
  void updateCredential(Integer credentialId, String newUserName, String url, String key, String password);

}
