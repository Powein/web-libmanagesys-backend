package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.pojo.Book;
import com.powei.testmybatis.pojo.BookQueryArgs;

import java.util.List;
public interface BookService {
    List<Book> queryBook(BookQueryArgs args);

    void deleteById(Integer id);

    void updateBook(Book book);

    void addBook(Book book);

}
