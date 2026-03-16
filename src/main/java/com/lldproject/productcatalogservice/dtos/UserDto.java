package com.lldproject.productcatalogservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private List<RoleDto> roles = new ArrayList<>();
}
