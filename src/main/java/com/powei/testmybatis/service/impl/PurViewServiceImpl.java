package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.mapper.AdminMapper;
import com.powei.testmybatis.mapper.PurViewMapper;
import com.powei.testmybatis.pojo.Admin;
import com.powei.testmybatis.pojo.PurView;
import com.powei.testmybatis.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class PurViewServiceImpl implements PurViewService{
    @Autowired
    private PurViewMapper purViewMapper;
    @Autowired
    private AdminMapper adminMapper;
//得到用户和它们权限的对应关系

    @Override
    public List<PurView> queryAll(String token) throws AccessDeniedException {
        if (getPurViewByToken(token).getSysSet())
            return purViewMapper.getAllPurView();
        else throw new AccessDeniedException("Permission denied");
    }

    @Override
    public void add(PurView purView, String token) throws AccessDeniedException {
        if (getPurViewByToken(token).getSysSet())
            purViewMapper.insertPurView(purView);
        else throw new AccessDeniedException("Permission denied");
    }

    @Override
    public void delete(Integer id, String token) throws AccessDeniedException{
        if (getPurViewByToken(token).getSysSet())
            purViewMapper.deletePurView(id);
        else throw new AccessDeniedException("Permission denied");
    }

    @Override
    public void update(PurView purView, String token) throws AccessDeniedException {
        if (getPurViewByToken(token).getSysSet()){
            System.out.println("正在更新purView");
            System.out.println(purView.toString());
            purViewMapper.updatePurView(purView);
            return;
        }
        else throw new AccessDeniedException("Permission denied");
    }


    @Override
    public PurView getPurViewByToken(String token) {
        Claims claims =  JwtUtils.parseJWT(token);
        Admin admin = adminMapper.getAdminByID((Integer) claims.get("id"));
        return purViewMapper.getPurViewById(admin.getGroup());
    }

    @Override
    public PurView getPurViewById(Integer id) {
        return purViewMapper.getPurViewById(id);
    }


}
