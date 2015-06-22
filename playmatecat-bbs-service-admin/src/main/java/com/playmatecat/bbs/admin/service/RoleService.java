package com.playmatecat.bbs.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playmatecat.bbs.admin.mapper.RoleMapper;
import com.playmatecat.commons.constants.PropertiesKeyConstants;
import com.playmatecat.commons.structure.Pagination;
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
     * @param page 分页信息,页号,页大小
     * @return
     */
    public List<RoleDTO> getRoles(RoleDTO roleDTO,Pagination<RoleDTO> page) {
        Map<String, Object> params = new HashMap<String, Object>();
        int pageNo = page.getPageNo();
        int pageSize = page.getPageSize();
        
        params.put("offset", UtilsPagination.getOffset(pageNo, pageSize));
        params.put("pageSize", pageSize);
        
        params.put("roleDTO", roleDTO);

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
        
        params.put("roleDTO", roleDTO);
        
        //指定子系统库
        String subSysDatabase = UtilsProperties.getProp(PropertiesKeyConstants.CAS_SUBSYS_SYS_DATABASE);
        params.put("subSysDatabase", subSysDatabase);
        
        return roleMapper.getRolesCount(params);
    }
    
    /**
     * 获得单个角色信息
     * @param roleDTO
     * @return
     */
    public RoleDTO getRole(RoleDTO roleDTO) {
        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("roleDTO", roleDTO);
        
        //指定子系统库
        String subSysDatabase = UtilsProperties.getProp(PropertiesKeyConstants.CAS_SUBSYS_SYS_DATABASE);
        params.put("subSysDatabase", subSysDatabase);
        
        return roleMapper.getRole(params);
    }
    
    /**
     * 添加单个角色信息
     * @param roleDTO
     * @return
     */
    public int addRole(RoleDTO roleDTO) {
        Map<String, Object> params = new HashMap<String, Object>();
        
        params.put("roleDTO", roleDTO);
        
        //指定子系统库
        String subSysDatabase = UtilsProperties.getProp(PropertiesKeyConstants.CAS_SUBSYS_SYS_DATABASE);
        params.put("subSysDatabase", subSysDatabase);
        
        return roleMapper.addRole(params);
    }
}
