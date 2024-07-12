package com.powei.testmybatis.controller;

import com.powei.testmybatis.mapper.BookMapper;
import com.powei.testmybatis.mapper.BorrowMapper;
import com.powei.testmybatis.mapper.ReaderMapper;
import com.powei.testmybatis.pojo.*;
import com.powei.testmybatis.service.impl.BookService;
import com.powei.testmybatis.service.impl.BorrowService;
import com.powei.testmybatis.service.impl.BorrowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BorrowController {
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/borrow/borrowBook/{borrowDays}")
    public Result borrowBook(@RequestBody Borrow borrow, @PathVariable("borrowDays") Integer borrowDays, @RequestHeader("token") String token){
        Integer bookId = borrow.getBookId();
        Integer ReaderId = borrow.getReaderId();
        System.out.println(borrow.toString());
        try {
            borrowService.borrowBook(bookId, ReaderId, borrowDays, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/borrow/returnBook")
    public Result returnBook(@RequestBody Borrow borrow, @RequestHeader("token") String token){
        try {
            borrowService.returnBook(borrow.getId(), token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/borrow/query")//查询借书列表
    public Result queryBorrow(@RequestBody BorrowQueryArgs queryArgs, @RequestHeader("token") String token){
        try {
            System.out.println("查询借阅参数为 ：");
            System.out.println(queryArgs.toString());
            List<Borrow> borrowList = borrowService.queryBorrow(queryArgs, token);
            List<BorrowDetailed> borrowDetailedList = new ArrayList<BorrowDetailed>();
            for (Borrow borrowOld : borrowList) {
                BorrowDetailed tempBorrowDetailed = new BorrowDetailed();
                tempBorrowDetailed.setBorrow(borrowOld);
                String bookName = bookMapper.getBookById(borrowOld.getBookId()).getBookName();
                String readerName = readerMapper.queryByReaderId(borrowOld.getReaderId()).getName();
                tempBorrowDetailed.setBookName(bookName);
                tempBorrowDetailed.setReaderName(readerName);
                borrowDetailedList.add(tempBorrowDetailed);
            }
            return Result.success(borrowDetailedList);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
