package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Borrow {
    Integer id;
    Integer readerId;
    Integer bookId;
    Date borrowTime;
    Date backTime;
    String operator;
    Boolean ifBack;
}

/** DB be like
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