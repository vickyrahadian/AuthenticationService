package com.kode19.authenticationservice.converter;

import com.kode19.authenticationservice.domain.Permission;
import com.kode19.authenticationservice.dto.PermissionDTO;
import org.springframework.stereotype.Component;

@Component
public class PermissionConverter {

    public PermissionDTO entityToDTO(Permission permission) {
        PermissionDTO dto = new PermissionDTO();
        dto.setId(permission.getId());
        dto.setName(permission.getName());
        return dto;
    }

    public Permission dtoToEntity(PermissionDTO dto) {
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        return permission;
    }
}