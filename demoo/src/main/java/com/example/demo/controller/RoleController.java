package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import com.sun.org.apache.bcel.internal.generic.DREM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    RoleService roleIpml;
    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    @ResponseBody
    public Map<String, Object> getRoles() {
        Map<String, Object> map = new HashMap<>();
        List<Role> roles = roleIpml.roleChildrens();
        map.put("status", "200");
        map.put("data", roles);
        System.out.println(roleIpml.roleChildrens());
        return map;
    }

    /**
     * 删除角色下的权限
     * @param req
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/deleteRole")
    @ResponseBody
    public Map<String, Object> deleteRoleById(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (roleIpml.deleteRoleById(req)){
            Role role = roleIpml.roleChildrensByRid(req);
            map.put("status","200");
            map.put("data", role.getChildrenRigths());
            return map;
        }
        return map;
    }

    /**
     * 添加角色下的权限
     * @param req
     * @return
     */
    @RequestMapping(value = "/roleAdd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> roleAdd(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        roleIpml.AddRoleAndRights(req);
        map.put("status","200");
        return map;
    }

    /**
     * 删除角色（修改status的值）
     * @param req
     * @return
     */
    @RequestMapping(value = "/deleteRoleAll", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRoleAll(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if (roleIpml.updateStatus(req)){
            map.put("status", "200");
            return map;
        }
        return map;
    }
    @RequestMapping(value = "/findRoleById", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findRoleById(Integer id) {
        System.out.println(id);
        Map<String, Object> map = new HashMap<>();
        map.put("status","200");
        map.put("data", roleIpml.findRoleById(id));
        return map;
    }
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateRole(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        if(roleIpml.updateRole(req)){
            map.put("status", "200");
        }
        return map;
    }

    /**
     * 添加角色
     * @param req
     * @return
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addRole(@RequestBody String req) {
        Map<String, Object> map = new HashMap<>();
        roleIpml.addRole(req);
        map.put("status", "200");
        return map;
    }
}
