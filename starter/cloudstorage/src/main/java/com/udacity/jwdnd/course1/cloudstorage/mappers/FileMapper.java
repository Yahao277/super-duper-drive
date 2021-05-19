package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {
  @Select("SELECT * FROM FILES WHERE filename = #{filename}")
  File getFile(String filename);

  @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata) " +
      "VALUES (#{filename},#{contentType},#{fileSize},#{userId},#{fileData})")
  @Options(useGeneratedKeys = true,keyProperty = "fileId")
  Integer insert(File file);

  @Delete("DELETE FROM FILES WHERE fileId = #{id}")
  void delete(Integer id);

  @Delete("DELETE FROM FILES WHERE filename = #{filename}")
  void deleteFile(String filename);

  @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
  String[] getFilesList(Integer userId);

}
