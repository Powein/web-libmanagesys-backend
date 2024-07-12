package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.AutomapConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Admin {
    private Integer id;
    private String name;
    private String password;
    private Integer group;
}