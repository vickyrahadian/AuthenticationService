package com.kode19.authenticationservice.service;

import com.kode19.authenticationservice.converter.PermissionConverter;
import com.kode19.authenticationservice.domain.Permission;
import com.kode19.authenticationservice.dto.PermissionDTO;
import com.kode19.authenticationservice.repository.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;
    @Mock
    private PermissionConverter permissionConverter;
    @InjectMocks
    private PermissionService permissionService;

    @BeforeEach
    public void init() {
        openMocks(this);
    }

    @Test
    @DisplayName("Success Create Permission")
    void testCreatePermission_Success() {

        Permission permission = Permission.builder()
                .name("READ_DATA")
                .build();

        Permission permissionSaved = Permission.builder()
                .id(1L)
                .name("READ_DATA")
                .build();

        PermissionDTO permissionDTO = PermissionDTO.builder()
                .name("READ_DATA")
                .build();

        when(permissionConverter.dtoToEntity(permissionDTO)).thenReturn(permission);
        when(permissionRepository.save(permission)).thenReturn(permissionSaved);
        when(permissionConverter.entityToDTO(permissionSaved)).thenReturn(PermissionDTO.builder().id(1L).name("READ_DATA").build());

        PermissionDTO permissionDTOSaved = permissionService.createPermission(permissionDTO);

        assertEquals(permissionDTO.getName(), permissionDTOSaved.getName());


        verify(permissionConverter, times(1)).dtoToEntity(permissionDTO);
        verify(permissionRepository, times(1)).save(permissionConverter.dtoToEntity(permissionDTO));
        verify(permissionConverter, times(1)).entityToDTO(permissionSaved);
    }

    @Test
    @DisplayName("Success Update Permission")
    void testUpdatePermission_Success() {

        PermissionDTO permissionDTO = PermissionDTO.builder()
                .id(1L)
                .name("WRITE_DATA")
                .build();

        Permission permission = Permission.builder()
                .id(1L)
                .name("READ_DATA")
                .build();

        Permission permissionUpdated = Permission.builder()
                .id(1L)
                .name("WRITE_DATA")
                .build();

        when(permissionRepository.findById(permissionDTO.getId())).thenReturn(Optional.of(permission));
        when(permissionRepository.save(permission)).thenReturn(permissionUpdated);
        when(permissionConverter.entityToDTO(permissionUpdated)).thenReturn(PermissionDTO.builder().id(1L).name("WRITE_DATA").build());

        PermissionDTO permissionDTOUpdated = permissionService.updatePermission(permissionDTO);

        assertEquals(permissionDTO.getId(), permissionDTOUpdated.getId());
        assertEquals(permissionDTO.getName(), permissionDTOUpdated.getName());

        verify(permissionRepository, times(1)).findById(permission.getId());
        verify(permissionRepository, times(1)).save(permission);
        verify(permissionConverter, times(1)).entityToDTO(permissionUpdated);

    }

}