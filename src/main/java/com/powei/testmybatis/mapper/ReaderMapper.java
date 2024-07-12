package com.powei.testmybatis.mapper;

import com.powei.testmybatis.pojo.Reader;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReaderMapper {
    Reader query(Reader reader);

    void update(Reader reader);

    @Delete("delete from user where readerid = #{readerId}")
    void deleteByReaderId(Integer readerId);

    @Insert("insert into user(borrowing_count, name, email, expire_date, typeid) " +
            "values(0, #{name}, #{email}, #{expireDate}, #{typeId})")
    void insert(Reader reader);

    @Select("select readerid as readerId, borrowing_count as borrowingCount, name as name," +
            " email as email, expire_date as expireDate, typeid as typeId from user where readerid = #{readerId}")
    Reader queryByReaderId(Integer readerId);
}
