package com.hert.base.controller;

import com.hert.base.service.IMenuService;
import com.hert.base.wrapper.MenuWrapper;
import com.hert.core.boot.ctrl.HertController;
import com.hert.core.mp.support.Condition;
import com.hert.core.secure.HertUser;
import com.hert.core.secure.annotation.PreAuth;
import com.hert.core.tool.api.R;
import com.hert.core.tool.constant.RoleConstant;
import com.hert.core.tool.support.Kv;
import com.hert.core.tool.utils.Func;
import com.hert.base.api.entity.Menu;
import com.hert.base.api.vo.MenuVO;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "菜单", tags = "菜单")
public class MenuController extends HertController {

	private IMenuService menuService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入menu")
	public R<MenuVO> detail(@ApiIgnore @RequestParam Integer menuId) {
		Menu detail = menuService.getById(menuId);
		return R.data(MenuWrapper.build().entityVO(detail));
	}

	/**
	 * 新增或修改
	 */
	@PostMapping("/submit")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入menu")
	public R submit(@Valid @RequestBody Menu menu) {
		return R.status(menuService.saveOrUpdate(menu));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@PreAuth(RoleConstant.HAS_ROLE_ADMIN)
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(menuService.removeByIds(Func.toIntList(ids)));
	}

	/**
	 * 前端菜单数据
	 */
	@GetMapping("/routes")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "前端菜单数据", notes = "前端菜单数据")
	public R<List<MenuVO>> routes(HertUser user) {
		/*List<MenuVO> list = menuService.routes(user.getRoleId());
		return R.data(list);*/
		return null;
	}

	/**
	 * 前端按钮数据
	 */
	@GetMapping("/buttons")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "前端按钮数据", notes = "前端按钮数据")
	public R<List<MenuVO>> buttons(HertUser user) {
		/*List<MenuVO> list = menuService.buttons(user.getRoleId());
		return R.data(list);*/
		return null;
	}

	/**
	 * 获取菜单树形结构
	 */
	@GetMapping("/tree")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "树形结构", notes = "树形结构")
	public R<List<MenuVO>> tree() {
		List<MenuVO> tree = menuService.tree();
		return R.data(tree);
	}

	/**
	 * 获取权限分配树形结构
	 */
	@GetMapping("/grant-tree")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "权限分配树形结构", notes = "权限分配树形结构")
	public R<List<MenuVO>> grantTree(HertUser user) {
		return R.data(menuService.grantTree(user));
	}

	/**
	 * 获取权限分配树形结构
	 */
	@GetMapping("/role-tree-keys")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "角色所分配的树", notes = "角色所分配的树")
	public R<List<String>> roleTreeKeys(String roleIds) {
		return R.data(menuService.roleTreeKeys(roleIds));
	}

	/**
	 * 获取配置的角色权限
	 */
	@GetMapping("auth-routes")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "菜单的角色权限")
	public R<List<Kv>> authRoutes(HertUser user) {
		return R.data(menuService.authRoutes(user));
	}

}
