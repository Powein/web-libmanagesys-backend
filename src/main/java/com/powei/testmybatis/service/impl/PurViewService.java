package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.pojo.PurView;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface PurViewService {

    List<PurView> queryAll(String token) throws AccessDeniedException;

    void add(PurView purView, String token) throws AccessDeniedException;

    void delete(Integer id, String token) throws AccessDeniedException;

    void update(PurView purView, String token) throws AccessDeniedException;

    PurView getPurViewByToken(String token);

    PurView getPurViewById(Integer id);

}