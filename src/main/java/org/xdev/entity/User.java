package org.xdev.entity;


import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
public class User {
    private String username;
    private String password;
    private Role role;
}
