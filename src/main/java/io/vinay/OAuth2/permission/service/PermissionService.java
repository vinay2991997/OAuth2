package io.vinay.OAuth2.permission.service;

import io.vinay.OAuth2.permission.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

}
