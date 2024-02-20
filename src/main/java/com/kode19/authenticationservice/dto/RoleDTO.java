package com.kode19.authenticationservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
    private Long roleId;
    private String name;
    private List<Long> permissionIds;

}