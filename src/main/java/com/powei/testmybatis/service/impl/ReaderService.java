package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.pojo.Chara;
import com.powei.testmybatis.pojo.Reader;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ReaderService {
    public void addReader(Reader reader, String token) throws AccessDeniedException;

    public void deleteReader(int id, String token) throws AccessDeniedException;

    public void updateReader(Reader reader, String token) throws AccessDeniedException;

    public Reader queryReader(Reader reader, String token) throws AccessDeniedException;

    public List<Chara> queryChara(String token) throws AccessDeniedException;

    public void addChara(Chara chara, String token) throws AccessDeniedException;

    public void deleteChara(Integer id, String token) throws AccessDeniedException;

    public void updateChara(Chara chara, String token) throws AccessDeniedException;
}
