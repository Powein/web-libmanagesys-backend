package com.powei.testmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  PurView {
    private String name;
    private Integer id;
    private Boolean borrowBack;//借书、还书权限
    private Boolean sysQuery;//普通管理员:修改非管理员密码
    private Boolean sysSet;//超级管理员:进行权限设置,修改所有用户密码
    private Boolean readerSet;//对读者操作权限
    private Boolean bookSet;//对书籍操作权限
}
