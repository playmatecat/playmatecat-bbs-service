package com.playmatecat.bbs.admin.mapper;

import java.util.List;
import java.util.Map;

import com.playmatecat.domains.sysBBS.dto.RoleDTO;

public interface RoleMapper {
    public List<RoleDTO> getRoles(Map<String, Object> params);
    
    public int getRolesCount(Map<String, Object> params);
    
    public RoleDTO getRole(Map<String, Object> params);
    
    public int addRole(Map<String, Object> params); 
}
