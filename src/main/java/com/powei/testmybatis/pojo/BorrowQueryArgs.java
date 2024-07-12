package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BorrowQueryArgs {
    Date fromTime;
    Date toTime;
    Integer readerId;
    Integer bookId;
    Boolean ifBack;
    Integer borrowId;
}
