package com.bus.ticket.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.ticket.entity.User;
import com.bus.ticket.model.UserBaseVo;
import com.bus.ticket.model.common.PageBean;
import com.bus.ticket.model.query.UserPageQuery;
import com.bus.ticket.service.UserService;
import com.bus.ticket.util.StreamUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户信息(User)表控制层
 *
 * @author honglixiang
 * @since 2023-07-31 16:37:31
 */
@Api(tags = "用户信息管理")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户信息分页查询", notes = "用户信息分页查询")
    @PostMapping("user/pageQuery")
    public PageBean<UserBaseVo>
        queryByPage(@ApiParam(value = "分页查询参数", required = true) @RequestBody UserPageQuery pageQuery) {
        PageBean<User> pageBean = this.userService.queryByPage(pageQuery);
        List<UserBaseVo> vos = StreamUtils.toList(pageBean.getPageDatas(), v -> UserBaseVo.convert(v));
        return new PageBean<>(pageBean, vos, pageBean.getTotalPages());
    }

    @ApiOperation(value = "获取用户信息详情", notes = "根据ID获取用户信息详细信息")
    @GetMapping("user/{id}")
    public User queryById(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        return this.userService.queryById(id);
    }

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息记录")
    @PostMapping("user")
    public User add(@ApiParam(value = "用户信息信息", required = true) @RequestBody User user) {
        return this.userService.insert(user);
    }

    @ApiOperation(value = "修改用户信息", notes = "根据ID修改用户信息信息")
    @PutMapping("user/{id}")
    public UserBaseVo edit(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id,
        @ApiParam(value = "用户信息信息", required = true) @RequestBody UserBaseVo baseUser) {
        baseUser.setId(id);
        User user = this.userService.update(baseUser.toUser());
        return UserBaseVo.convert(user);
    }
}
