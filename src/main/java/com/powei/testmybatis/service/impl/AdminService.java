package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.pojo.Admin;
import com.powei.testmybatis.pojo.Result;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AdminService {
    List<Admin> list(String token) throws AccessDeniedException;

    void delete(Integer id, String token) throws AccessDeniedException;

    void update(Admin admin, String token) throws AccessDeniedException;

    void add(Admin admin, String token) throws AccessDeniedException;

    Admin getByUsernameAndPassword(Admin admin);

    void updateMyself(Admin admin, String token) throws  AccessDeniedException;

    Admin getAdminById(Integer id);
}
