package com.car.service.impl;

import com.car.model.Admin;
import com.car.model.Admin;
import com.car.repository.AdminRepository;
import com.car.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }


    @Override
    public boolean authenticateAdmin(String username, String password) {
        Admin admin=adminRepository.findByUsernameAndPassword(username,password);
        return admin!=null;
    }
}
