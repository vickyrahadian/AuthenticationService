package com.kode19.authenticationservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDTO {
    private long id;
    private String name;

}
