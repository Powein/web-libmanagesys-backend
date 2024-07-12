package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookQueryArgs
{
    Integer id;
    String barCode;
    Integer typeID;
    String name;
    String author;
    String press;
    Integer bookCase;
}