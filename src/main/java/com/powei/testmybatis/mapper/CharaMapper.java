package com.powei.testmybatis.mapper;

import com.powei.testmybatis.pojo.Chara;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CharaMapper {
    List<Chara> list();

    @Insert("insert into reader_char(max_borrow, duration, name) value(#{maxBorrow}, #{duration}, #{name})")
    void insert( Chara chara);

    void update( Chara chara);

    @Delete("delete from reader_char where id = #{id}")
   void deleteById(Integer id);

    @Select("select max_borrow as maxBorrow, duration as duration, " +
            "name as name, id as id from reader_char where id = #{id}")
    Chara getById(Integer id);
}
