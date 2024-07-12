package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Conditional;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    private Integer id;
    private String barCode;
    private String bookName;
    private Integer typeId;
    private String author;
    private String translator;
    private String ISBN;
    private Double price;
    private Integer page;
    private Integer bookCase;
    private String inTime;
    private String operator;
    private Boolean del;
    private String press;
    private Boolean avail;
}
