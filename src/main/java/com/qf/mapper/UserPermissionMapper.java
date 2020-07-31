package com.qf.mapper;

import com.qf.entity.UserPermission;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserPermissionMapper {

    public Set<String> findAllPerms(Integer uid);

    public void addPerms(UserPermission permission);
}
