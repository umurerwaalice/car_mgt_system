package com.car.service;

import com.car.model.Admin;

public interface AdminService {
    boolean authenticateAdmin(String username, String password);
}
