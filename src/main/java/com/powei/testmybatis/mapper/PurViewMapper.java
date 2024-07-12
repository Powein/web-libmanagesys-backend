package com.powei.testmybatis.mapper;

import com.powei.testmybatis.pojo.PurView;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PurViewMapper {
    @Select("select name as name, id as id, borrowback as borrowBack, sysquery as sysQuery" +
            ", sysset as sysSet, readerset as readerSet, bookset as bookSet " +
            "from purview")
    public List<PurView> getAllPurView();
//按照类里面的标识符起别名，要不然查询不到

    @Select("select name as name, id as id, borrowback as borrowBack, sysquery as sysQuery" +
            ", sysset as sysSet, readerset as readerSet, bookset as bookSet " +
            "from purview where id = #{PurViewID}")
    public PurView getPurViewById(Integer PurViewID);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into purview(name ,borrowback, sysquery, sysset, readerset, bookset) values" +
        "(#{name},#{borrowBack}, #{sysQuery}, #{sysSet}, #{readerSet}, #{bookSet})")
    public void insertPurView(PurView purView);

    public void updatePurView(PurView purView);

    @Delete("delete from purview where id = #{PurViewID}")
    public void deletePurView(Integer PurViewID);

}
