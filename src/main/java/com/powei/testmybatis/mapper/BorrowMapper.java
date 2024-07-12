package com.powei.testmybatis.mapper;

import com.powei.testmybatis.pojo.Borrow;
import com.powei.testmybatis.pojo.BorrowQueryArgs;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * create table borrow
 * (
 *     id          int auto_increment comment '唯一主键ID',
 *     readerid    int         null comment '读者id',
 *     bookid      int         null comment '图书id',
 *     borrow_time datetime    null comment '借出时间',
 *     back_time   datetime    null comment '归还时间',
 *     operator    varchar(30) null comment '操作员',
 *     ifback      boolean     null comment '是否归还',
 *     constraint borrow_pk
 *         primary key (id),
 *     constraint borrow_pk_2
 *         unique (id)
 * )
 *     comment '借出表';
 */

//图书借阅查询的依据：图书条形码，图书名称，读者条形码，读者名称，借阅时间

//借阅到期显示
@Mapper
public interface BorrowMapper {
    public List<Borrow> query(BorrowQueryArgs queryArgs);

    @Insert("insert into borrow(readerid,bookid,borrow_time,back_time,operator,ifback) " +
            "values(#{readerId},#{bookId},now(),#{backTime},#{operator},0)")
    public void insertBorrow(Borrow borrow);

    @Update("update borrow set ifback = 1, back_time = now() where id = #{id}")
    public void returnBorrowById(Integer id);

    @Delete("DELETE FROM borrow WHERE id = #{id}")
    public void delete(Borrow borrow);

    @Select("select id, readerid as readerId, bookid as bookId, borrow_time as borrowTime, " +
            "back_time as backTime, operator as operator, ifback as ifBack from borrow where id = #{id}")
        public Borrow queryById(Integer id);
}
