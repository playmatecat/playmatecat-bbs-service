package com.playmatecat.bbs.admin.cpt;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playmatecat.bbs.admin.service.RoleService;
import com.playmatecat.bbs.bbsApp.admin.vo.AuthorizationVO;
import com.playmatecat.commons.structure.Pagination;
import com.playmatecat.domains.sysBBS.dto.RoleDTO;
import com.playmatecat.utils.dataformat.UtilsPagination;

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
     * [authorizationVO.roleDTO.isDeleted]
     * <authorizationVO.roleDTO.pageSize>
     * <authorizationVO.roleDTO.pageNo>
     * @param authorizationVO
     * @return
     */
    public AuthorizationVO getRolesPagination(AuthorizationVO authorizationVO) {
        
        AuthorizationVO rtnVO = new AuthorizationVO();
        
        //获得/初始化角色分页参数
        Pagination<RoleDTO> rolePage = UtilsPagination.getPage(authorizationVO.getRolePage());
        
        //获得/初始化角色传参
        RoleDTO roleDTO = new RoleDTO();
        RoleDTO roleSrcDTO = authorizationVO.getRoleDTO();
        roleSrcDTO = roleSrcDTO == null ? new RoleDTO() : roleSrcDTO;
        
        //设定查询未删除的记
        roleSrcDTO.setIsDeleted(false);
        BeanUtils.copyProperties(roleSrcDTO, roleDTO, new String[]{"id"});

        //获得角色列表数据
        List<RoleDTO> roleList = roleService.getRoles(roleDTO, rolePage);
        //数据总数
        int total = roleService.getRolesCount(roleDTO);
        rolePage.setList(roleList);
        rolePage.setTotal(total);
        
        rtnVO.setRolePage(rolePage);
        return rtnVO;
    }
    
    /**
     * 获得单个角色信息
     * @param authorizationVO
     * @return
     */
    public AuthorizationVO getRole(AuthorizationVO authorizationVO) {
        
        AuthorizationVO rtnVO = new AuthorizationVO();
        
        RoleDTO roleDTO = new RoleDTO();
        RoleDTO roleSrcDTO = authorizationVO.getRoleDTO();
        roleSrcDTO = roleSrcDTO == null ? new RoleDTO() : roleSrcDTO;
        
        //设定查询未删除的记
        roleSrcDTO.setIsDeleted(false);
        BeanUtils.copyProperties(roleSrcDTO, roleDTO);
        
        rtnVO.setRoleDTO(roleService.getRole(roleDTO));
        
        return rtnVO;
    }
    
    /**
     * 添加一个角色信息
     * @param authorizationVO
     * @return
     */
    public AuthorizationVO addRole(AuthorizationVO authorizationVO) throws Exception{
        AuthorizationVO rtnVO = new AuthorizationVO();
        
        RoleDTO roleDTO = new RoleDTO();
        RoleDTO roleSrcDTO = authorizationVO.getRoleDTO();
        roleSrcDTO = roleSrcDTO == null ? new RoleDTO() : roleSrcDTO;
        
        BeanUtils.copyProperties(roleSrcDTO, roleDTO);
        
        int rtn = roleService.addRole(roleDTO);
        
        if(rtn == 0) {
            throw new Exception("写入角色信息失败");
        }
        
        return rtnVO;
    }
}
