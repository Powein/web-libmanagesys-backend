package com.powei.testmybatis.mapper;

import com.powei.testmybatis.pojo.Book;
import com.powei.testmybatis.pojo.BookQueryArgs;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * (
 *     id         int auto_increment comment '图书ID'
 *         primary key,
 *     press varchar(30) null comment '出版社',
 *     barcode    int          null comment '图书条形码',
 *     bookname   varchar(80)  null comment '图书名字',
 *     typeid     int unsigned null comment '类型',
 *     author     varchar(30)  null comment '作者',
 *     translator varchar(30)  null comment '译者',
 *     ISBN       varchar(20)  null,
 *     price      double(8, 2) null comment '价格',
 *     page       int          null,
 *     bookcase   int unsigned null comment '书架',
 *     inTime     date         null comment '录入时间',
 *     operator   varchar(30)  null comment ' 操作员',
 *     del        tinyint(1)   null comment '是否删除,删除为1',
 *     constraint book_info_pk_2
 *         unique (id)
 * )
 */
@Mapper
public interface BookMapper {
    //查询书籍页面接口
    public List<Book> query(BookQueryArgs args);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into book_info(press, barcode, bookname, typeid," +
            " author, translator, isbn, price, page, bookcase, intime, operator, del) " +
            "values(#{press}, #{barCode}, #{bookName}, #{typeId}, #{author}, #{translator}," +
            " #{ISBN}, #{price}, #{page}, #{bookCase}, now(), #{operator}, 0)")
    public void addBook(Book book);

    @Delete("delete from book_info where id = #{id}")
    public void permanentlyDeleteBook(Integer id);

    @Update("update book_info set del = 1, avail = 1 where id = #{id}")
    public void deleteBook(Integer id);

    public void update(Book book);//这里需要根据id来判断更新哪一本书，id是必须有的

    @Select("select avail from book_info where id = #{id}")
    public Boolean checkBook(Integer id);

    @Select("select id as id, press as press, barcode as barCode, bookname as bookName, typeid as typeId, author as author, translator as translator, " +
            "isbn as ISBN, price as price, page as page, bookcase as bookCase, intime as inTime, operator as operator, del as del, avail as avail from book_info where id = #{id}")
        public Book getBookById(Integer id);
}
