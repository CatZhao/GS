package cn.zhaoxiaoxue.nsfw.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.zhaoxiaoxue.core.constant.Constant;
import cn.zhaoxiaoxue.nsfw.entity.Role;
import cn.zhaoxiaoxue.nsfw.entity.RolePrivilege;
import cn.zhaoxiaoxue.nsfw.entity.RolePrivilegeId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class RoleDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Resource
	RoleDao roleDao;
	
	@Transactional
	@Rollback(false)
	@Test
	public void testSave() {
		Role role = new Role();
		role.setName("testSave");
		role.setState(Role.PRIVILEGE_ROLE_VALID);
		
		Set<RolePrivilege> rolePrivileges = new HashSet<RolePrivilege>();
		RolePrivilege rolePrivilege = new RolePrivilege();
		
		RolePrivilegeId id = new RolePrivilegeId();
		id.setRole(role);
		id.setCode(Constant.PRIVILEGE_NSFW);
		
		rolePrivilege.setRolePrivilegeId(id);
		rolePrivileges.add(rolePrivilege);
		role.setRolePrivileges(rolePrivileges);
		
		roleDao.save(role);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCleanPrivilegeOfRole(){
		roleDao.cleanPrivilegeOfRole("2c2881e5572927010157292702f30000");
	}

}
