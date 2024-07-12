package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Chara {
    String name;
    Integer duration;
    Integer maxBorrow;
    Integer id;
}
