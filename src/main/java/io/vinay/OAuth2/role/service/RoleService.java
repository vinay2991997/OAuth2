package io.vinay.OAuth2.role.service;

import io.vinay.OAuth2.role.model.Role;
import io.vinay.OAuth2.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }

}
