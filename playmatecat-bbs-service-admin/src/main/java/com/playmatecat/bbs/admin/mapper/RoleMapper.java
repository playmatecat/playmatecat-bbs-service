package com.playmatecat.bbs.admin.mapper;

import java.util.List;
import java.util.Map;

import com.playmatecat.domains.sysBBS.dto.RoleDTO;

public interface RoleMapper {
    public List<RoleDTO> getRoles(Map<String, Object> params);
}
