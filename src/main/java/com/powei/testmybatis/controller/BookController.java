package com.powei.testmybatis.controller;

import com.powei.testmybatis.pojo.Book;
import com.powei.testmybatis.pojo.BookQueryArgs;
import com.powei.testmybatis.pojo.PurView;
import com.powei.testmybatis.pojo.Result;
import com.powei.testmybatis.service.impl.BookService;
import com.powei.testmybatis.service.impl.PurViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j

public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private PurViewService purViewService;

    @PostMapping("/book/query")
    public Result queryBook(@RequestBody BookQueryArgs bookQueryArgs){
        System.out.println("正在查询");
        System.out.println(bookQueryArgs);
        List<Book> books = bookService.queryBook(bookQueryArgs);

        return Result.success(books);
    }

    @GetMapping("/book/delete/{id}")
    public Result deleteBook(@PathVariable("id") Integer id, @RequestHeader("token") String token){
        PurView purView = purViewService.getPurViewByToken(token);
        if (!purView.getBookSet()){
            return Result.error("没有权限");
        }
        bookService.deleteById(id);
        return Result.success(null);
    }

    @PostMapping("/book/add")
    public Result addBook(@RequestBody Book book, @RequestHeader("token") String token){
        PurView purView = purViewService.getPurViewByToken(token);
        if (!purView.getBookSet()){
            return Result.error("没有权限");
        }
        bookService.addBook(book);
        return Result.success(null);
    }

    @PostMapping("/book/update")
    public Result updateBook(@RequestBody Book book, @RequestHeader("token") String token) {
        PurView purView = purViewService.getPurViewByToken(token);
        if (!purView.getBookSet()){
            return Result.error("没有权限");
        }
        bookService.updateBook(book);
        return Result.success(null);
    }
}
