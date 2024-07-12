package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reader {
    Integer readerId;
    Integer borrowingCount;
    String name;
    Date expireDate;
    Integer typeId;
    String email;
}
