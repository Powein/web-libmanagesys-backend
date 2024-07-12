package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.mapper.BookMapper;
import com.powei.testmybatis.mapper.BorrowMapper;
import com.powei.testmybatis.mapper.CharaMapper;
import com.powei.testmybatis.mapper.ReaderMapper;
import com.powei.testmybatis.pojo.*;
import com.powei.testmybatis.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;

@Service

public class BorrowServiceImpl implements BorrowService{

    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private PurViewService purViewService;
    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CharaMapper charaMapper;

    @Override
    public void borrowBook(Integer bookId, Integer readerId, Integer borrowDays, String token) throws AccessDeniedException {
        PurView currView = purViewService.getPurViewByToken(token);
        Integer maxBorrow = 0;
        Integer maxBorrowDays = 0;
        if(!currView.getBorrowBack())
            throw new AccessDeniedException("No permission to borrow service");

        //获取读者和书籍信息
        Reader newReader = readerMapper.queryByReaderId(readerId);
        Book newBook = bookMapper.getBookById(bookId);
        if((newBook.getId() == null) || (newReader.getReaderId() == null)) {
            throw new AccessDeniedException("Reader or borrow does not exist");
        }
       //判断书籍是否可借
        if (!newBook.getAvail()) {
            throw new AccessDeniedException("Book is not available");
        }

        //获取读者类型
        Chara chara = charaMapper.getById(newReader.getTypeId());
//        System.out.println(newReader.toString());
        if (chara != null) {
            maxBorrow = chara.getMaxBorrow();
            maxBorrowDays = chara.getDuration();
        } else {
            throw new AccessDeniedException("reader does not have a type");
        }


        //判断读者是否达到最大借书数量
        if(newReader.getBorrowingCount() >= maxBorrow)
            throw new AccessDeniedException("已经达到借阅上限！");

        //判断借书时间是否超时
        if (borrowDays > maxBorrowDays) {
            throw new AccessDeniedException("Borrowing time exceeds the maximum limit");
        }
        //判断借书卡是否超时
//        if (newReader.getExpireDate().before(new Date())) {
//            throw new AccessDeniedException("Reader card has expired");
//        }

        //现在可以开始借书
        newBook.setAvail(false);
        newReader.setBorrowingCount(newReader.getBorrowingCount() + 1);

        //更新读者和书籍消息
        readerMapper.update(newReader);//读者借书数量+1
        bookMapper.update(newBook);//更新书籍借出信息

        //添加借书记录
        Claims claims = JwtUtils.parseJWT(token);
        String operator = (String)claims.get("name");
        System.out.println(claims.toString());
        Borrow borrow = new Borrow();
        borrow.setReaderId(readerId);
        borrow.setBookId(bookId);
        borrow.setIfBack(false);
        borrow.setOperator(operator);
        System.out.println("当前要插入的借书记录");
        System.out.println(borrow.toString());
        borrowMapper.insertBorrow(borrow);
    }

    @Override
    public void returnBook(Integer borrowId, String token) throws AccessDeniedException {
        //权限验证部分
        PurView currView = purViewService.getPurViewByToken(token);
        Integer maxBorrow = 0;
        if(!currView.getBorrowBack())
            throw new AccessDeniedException("No borrow access");

        //有还书权限,接下来看是否存在要归还的借书id
        Borrow borrow =  borrowMapper.queryById(borrowId);
        if(borrow == null)
            throw new AccessDeniedException("Borrow does not exist");
        //查询书籍
        Integer bookId = borrow.getBookId();
        Integer readerId = borrow.getReaderId();
        Reader reader = readerMapper.queryByReaderId(readerId);
        Book book = bookMapper.getBookById(bookId);
        //数据库操作
        reader.setBorrowingCount(reader.getBorrowingCount() - 1);
        book.setAvail(true);
        readerMapper.update(reader);
        bookMapper.update(book);
        borrowMapper.returnBorrowById(borrowId);
        return;
    }

    @Override
    public List<Borrow> queryBorrow(BorrowQueryArgs borrowQueryArgs, String token) throws AccessDeniedException {
        if(!purViewService.getPurViewByToken(token).getBorrowBack())
            throw new AccessDeniedException("No permission to borrow service");
        return borrowMapper.query(borrowQueryArgs);
    }
}
