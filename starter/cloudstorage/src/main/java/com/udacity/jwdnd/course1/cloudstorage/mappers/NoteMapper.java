package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
  @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
  Note getNote(Integer noteId);

  @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
  Note[] getNotesByUser(Integer userId);

  @Select("SELECT * FROM NOTES")
  Note[] getNotes();

  @Insert("INSERT INTO NOTES (notetitle,notedescription,userid) " +
      "VALUES (#{noteTitle},#{noteDescription},#{userId})")
  @Options(useGeneratedKeys = true,keyProperty = "noteId")
  Integer insert(Note note);

  @Delete("DELETE FROM NOTES WHERE noteid = #{id}")
  void delete(Integer id);

  @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
  void updateNote(Integer noteId, String title, String description);
}
