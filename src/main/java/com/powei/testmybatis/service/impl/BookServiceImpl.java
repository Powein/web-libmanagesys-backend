package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.mapper.BookMapper;
import com.powei.testmybatis.pojo.Book;
import com.powei.testmybatis.pojo.BookQueryArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;

    public List<Book> queryBook(BookQueryArgs args) {
        return bookMapper.query(args);
    }

    public void deleteById(Integer id){
        bookMapper.deleteBook(id);
    }

    public void updateBook(Book book) {
        bookMapper.update(book);
    }

    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

}
