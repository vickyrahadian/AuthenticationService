package com.kode19.authenticationservice.service;

import com.kode19.authenticationservice.converter.PermissionConverter;
import com.kode19.authenticationservice.domain.Permission;
import com.kode19.authenticationservice.dto.PermissionDTO;
import com.kode19.authenticationservice.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PermissionService {

    private PermissionRepository permissionRepository;
    private PermissionConverter permissionConverter;

    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
        Permission permission = permissionConverter.dtoToEntity(permissionDTO);
        return permissionConverter.entityToDTO(permissionRepository.save(permission));
    }

    public PermissionDTO updatePermission(PermissionDTO permissionDTO) {
        // Check if the permission exists
        Permission existingPermission = permissionRepository.findById(permissionDTO.getId())
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        // Update the fields
        existingPermission.setName(permissionDTO.getName());

        return permissionConverter.entityToDTO(permissionRepository.save(existingPermission));
    }

    public PermissionDTO getPermissionById(long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        return permissionConverter.entityToDTO(permission);
    }

    public PermissionDTO getPermissionByName(String name) {
        Permission permission = permissionRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        return permissionConverter.entityToDTO(permission);
    }

    public void deletePermission(long permissionId) {
        permissionRepository.deleteById(permissionId);
    }

    public Page<PermissionDTO> getAllPermissions(int page, int pageSize, String sortDirection, String sortBy) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(direction, sortBy)); // Change 10 to your desired page size
        Page<Permission> permissions = permissionRepository.findAll(pageable);
        return permissions.map(permissionConverter::entityToDTO);
    }
}
