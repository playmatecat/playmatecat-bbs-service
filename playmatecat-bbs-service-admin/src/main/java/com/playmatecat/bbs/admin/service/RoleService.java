package com.playmatecat.bbs.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playmatecat.bbs.admin.mapper.RoleMapper;
import com.playmatecat.commons.constants.PropertiesKeyConstants;
import com.playmatecat.domains.sysBBS.dto.RoleDTO;
import com.playmatecat.utils.dataformat.UtilsPagination;
import com.playmatecat.utils.spring.UtilsProperties;

@Service
public class RoleService {
    
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获得角色列表
     * @param roleDTO
     * @return
     */
    public List<RoleDTO> getRoles(RoleDTO roleDTO) {
        Map<String, Object> params = new HashMap<String, Object>();
        int pageNo = roleDTO.getPageNo();
        int pageSize = roleDTO.getPageSize();
        
        params.put("offset", UtilsPagination.getOffset(pageNo, pageSize));
        params.put("pageSize", pageSize);
        params.put("isDeleted", false);
        
        //指定子系统库
        String subSysDatabase = UtilsProperties.getProp(PropertiesKeyConstants.CAS_SUBSYS_SYS_DATABASE);
        params.put("subSysDatabase", subSysDatabase);

        return roleMapper.getRoles(params);
    }
    
    /**
     * 获得角色数
     * @param roleDTO
     * @return
     */
    public int getRolesCount(RoleDTO roleDTO) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("isDeleted", false);
        
        //指定子系统库
        String subSysDatabase = UtilsProperties.getProp(PropertiesKeyConstants.CAS_SUBSYS_SYS_DATABASE);
        params.put("subSysDatabase", subSysDatabase);
        
        return roleMapper.getRolesCount(params);
    }
}
