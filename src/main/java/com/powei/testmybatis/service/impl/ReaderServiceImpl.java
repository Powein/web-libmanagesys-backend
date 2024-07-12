package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.mapper.CharaMapper;
import com.powei.testmybatis.mapper.ReaderMapper;
import com.powei.testmybatis.pojo.Chara;
import com.powei.testmybatis.pojo.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService{

    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private PurViewService purViewService;
    @Autowired
    private CharaMapper charaMapper;

    @Override
    public void addReader(Reader reader, String token) throws AccessDeniedException {
        if (!purViewService.getPurViewByToken(token).getReaderSet())
            throw new AccessDeniedException("没有权限");
        readerMapper.insert(reader);
    }

    @Override
    public void deleteReader(int id, String token) throws AccessDeniedException {
        if (!purViewService.getPurViewByToken(token).getReaderSet())
            throw new AccessDeniedException("没有权限");
        readerMapper.deleteByReaderId(id);
    }

    @Override
    public void updateReader(Reader reader, String token) throws AccessDeniedException {
        if (!purViewService.getPurViewByToken(token).getReaderSet())
            throw new AccessDeniedException("没有权限");
         readerMapper.update(reader);
    }

    @Override
    public Reader queryReader(Reader reader, String token) throws AccessDeniedException {
        if (!purViewService.getPurViewByToken(token).getReaderSet()){
            System.out.println("测试：查询读者");
            System.out.println(purViewService.getPurViewByToken(token).toString());
            throw new AccessDeniedException("没有权限");
        }
        System.out.println("测试：查询读者");
        System.out.println(purViewService.getPurViewByToken(token).toString());
        return readerMapper.query(reader);
    }

    @Override
    public List<Chara> queryChara(String token) throws AccessDeniedException {
        if (!purViewService.getPurViewByToken(token).getReaderSet())
            throw new AccessDeniedException("没有权限");
        return charaMapper.list();
    }

    @Override
    public void addChara(Chara chara, String token) throws AccessDeniedException {
        if (!purViewService.getPurViewByToken(token).getReaderSet())
            throw new AccessDeniedException("没有权限");
        charaMapper.insert(chara);
    }

    @Override
    public void deleteChara(Integer id, String token) throws AccessDeniedException {
        if (!purViewService.getPurViewByToken(token).getReaderSet())
            throw new AccessDeniedException("没有权限");
        charaMapper.deleteById(id);
    }

    @Override
    public void updateChara(Chara chara, String token) throws AccessDeniedException {
        System.out.println("收到的角色数据:");
        System.out.println(chara.toString());
        if (!purViewService.getPurViewByToken(token).getReaderSet())
            throw new AccessDeniedException("没有权限");
        charaMapper.update(chara);
    }
}
