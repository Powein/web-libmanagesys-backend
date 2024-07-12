package com.powei.testmybatis.mapper;

import com.powei.testmybatis.pojo.Admin;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AdminMapper {
//    @Select("select id, name, password from admin")
    public List<Admin> getAllAdmin();

    @Delete("delete from admin where id=#{id}")
    public void deleteAdminByID(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO admin(name, password, usergroup) VALUES(#{name}, #{password}, #{group})")
    public void insertAdmin(Admin admin);

    @Update("UPDATE admin SET name=#{name}, password=#{password}, usergroup = #{group} WHERE id=#{id}")
    public void updateAdmin(Admin admin);

    @Update("UPDATE admin SET usergroup = #{group} WHERE id=#{id}")
    public void updateAdminGroup(Admin admin);

    public void deleteAdminByIDs(List<Integer> ids);

    @Select("select id, password,`name` from admin where password = #{password} and id = #{id}")
    public Admin getByUsernameAndPassword(Admin admin);

    @Select("select usergroup as `group`, name as name, password as password from admin where id = #{id}")
    public Admin getAdminByID(Integer id);
}