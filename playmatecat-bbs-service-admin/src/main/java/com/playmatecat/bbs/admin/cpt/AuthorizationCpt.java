package com.playmatecat.bbs.admin.cpt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playmatecat.bbs.admin.service.RoleService;
import com.playmatecat.bbs.bbsApp.admin.vo.AuthorizationVO;
import com.playmatecat.commons.structure.Pagination;
import com.playmatecat.domains.sysBBS.dto.RoleDTO;

/**
 * 授权相关组件
 * @author blackcat
 *
 */
@Component(value="admin-authorizationCpt")
public class AuthorizationCpt {
    
    @Autowired
    private RoleService roleService;
    
    
    /**
     * 获得角色分页列表
     * 参数表：
     * <authorizationVO.roleDTO.pageSize>
     * <authorizationVO.roleDTO.pageNo>
     * @param authorizationVO
     * @return
     */
    public AuthorizationVO getRolesPagination(AuthorizationVO authorizationVO) {
        AuthorizationVO rtnVO = new AuthorizationVO();
        int pageNo = authorizationVO.getRoleDTO().getPageNo();
        int pageSize = authorizationVO.getRoleDTO().getPageSize();
        Pagination<RoleDTO> page = new Pagination<>(pageNo,pageSize);
        
        
        List<RoleDTO> roleList = roleService.getRoles(authorizationVO.getRoleDTO());
        //数据总数
        int total = roleService.getRolesCount(authorizationVO.getRoleDTO());
        page.setList(roleList);
        page.setTotal(total);
        
        rtnVO.setRolePage(page);
        return rtnVO;
    }

}
