package com.powei.testmybatis.service.impl;

import com.powei.testmybatis.mapper.AdminMapper;
import com.powei.testmybatis.mapper.PurViewMapper;
import com.powei.testmybatis.pojo.Admin;
import com.powei.testmybatis.pojo.PurView;
import com.powei.testmybatis.pojo.Result;
import com.powei.testmybatis.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationSupport;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PurViewService purViewService;

    public List<Admin> list(String token) throws AccessDeniedException {
        if (purViewService.getPurViewByToken(token).getSysSet())
            return adminMapper.getAllAdmin();
        else throw new AccessDeniedException("Permission Denied");
    }

    public void delete(Integer id, String token) throws AccessDeniedException{
        if (purViewService.getPurViewByToken(token).getSysSet()){
            adminMapper.deleteAdminByID(id);
        }
        else throw new AccessDeniedException("Permission Denied");
    }

    public void update(Admin admin, String token) throws AccessDeniedException{
        PurView currentAccess = purViewService.getPurViewByToken(token);
        PurView targetAccess = purViewService.getPurViewById(admin.getGroup());
        boolean isTargetAdmin = (targetAccess.getSysSet() || targetAccess.getSysQuery());
        if (currentAccess.getSysSet()) {
            adminMapper.updateAdmin(admin);
            return;
        } else if (currentAccess.getSysQuery() && !isTargetAdmin) {
            adminMapper.updateAdmin(admin);
            return;
        } else throw new AccessDeniedException("Permission Denied");
    }

    public void add(Admin admin, String token) throws AccessDeniedException{
        if (purViewService.getPurViewByToken(token).getSysSet()) {
            System.out.println("超级管理员正在添加管理员");
            adminMapper.insertAdmin(admin);
            System.out.println(admin.toString());
            return;
        }
        else throw new AccessDeniedException("Permission Denied");
    }

    public Admin getByUsernameAndPassword(Admin admin) {
        return adminMapper.getByUsernameAndPassword(admin);
    }

    public void updateMyself(Admin admin, String token) throws AccessDeniedException {
        Claims loginStatus = JwtUtils.parseJWT(token);
        Integer userid = (Integer) loginStatus.get("id");
        System.out.println("用户ID如下");
        System.out.println(userid);
        if (admin.getId().equals(userid)) {
            adminMapper.updateAdmin(admin);
            return;
        }
        else throw new AccessDeniedException("Permission Denied");
    }

    public Admin getAdminById(Integer id) {
        return adminMapper.getAdminByID(id);
    }
}
