package com.kode19.authenticationservice.converter;

import com.kode19.authenticationservice.domain.*;
import com.kode19.authenticationservice.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    public RoleDTO entityToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getId());
        roleDTO.setName(role.getName());
        List<Long> permissionIds = role.getPermissions().stream()
                .map(Permission::getId)
                .collect(Collectors.toList());
        roleDTO.setPermissionIds(permissionIds);
        return roleDTO;
    }

    public Role dtoToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        // Permissions are not set here to avoid loading permission entities
        return role;
    }
}

