package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.pojo.*;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public interface BorrowService {
    //需要提供的接口:借书和还书
    void borrowBook(Integer bookId, Integer readerId, Integer borrowDays, String token) throws AccessDeniedException;

    void returnBook(Integer borrowId, String token) throws AccessDeniedException;

    List<Borrow> queryBorrow(BorrowQueryArgs borrowQueryArgs, String token) throws AccessDeniedException;

}
