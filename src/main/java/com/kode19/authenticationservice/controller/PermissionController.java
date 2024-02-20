package com.kode19.authenticationservice.controller;

import com.kode19.authenticationservice.dto.PermissionDTO;
import com.kode19.authenticationservice.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/permissions")
@AllArgsConstructor
public class PermissionController {

    private PermissionService permissionService;

    @PostMapping
    public PermissionDTO createPermission(@RequestBody PermissionDTO permissionDTO) {
        return permissionService.createPermission(permissionDTO);
    }

    @PutMapping("/{id}")
    public PermissionDTO updatePermission(@PathVariable("id") long id, @RequestBody PermissionDTO permissionDTO) {
        permissionDTO.setId(id);
        return permissionService.updatePermission(permissionDTO);
    }

    @GetMapping("/{id}")
    public PermissionDTO getPermissionById(@PathVariable("id") long id) {
        return permissionService.getPermissionById(id);
    }

    @GetMapping("/name/{name}")
    public PermissionDTO getPermissionByName(@PathVariable("name") String name) {
        return permissionService.getPermissionByName(name);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable("id") long id) {
        permissionService.deletePermission(id);
    }

    @GetMapping
    public Page<PermissionDTO> getAllPermissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "name") String sortBy) {

        return permissionService.getAllPermissions(page, pageSize, sortDirection, sortBy);
    }
}
