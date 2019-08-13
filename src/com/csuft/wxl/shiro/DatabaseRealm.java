package com.csuft.wxl.shiro;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.csuft.wxl.pojo.Permission;
import com.csuft.wxl.pojo.Role;
import com.csuft.wxl.pojo.User;
import com.csuft.wxl.session.GetSession;

public class DatabaseRealm extends AuthorizingRealm {

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// TODO Auto-generated method stub
		// 能进入到这里，表示账号已经通过验证了
		String userName = (String) principalCollection.getPrimaryPrincipal();
		// 通过DAO获取角色和权限
		User user=getUser(userName);
		Set<String> roles=new HashSet<String>();
		for (Role role : user.getRole()) {
			roles.add(role.getName());
		}
		
		Set<String> permissions=new HashSet<String>();
		for (Permission role : user.getPermission()) {
			permissions.add(role.getName());
		}
		// 授权对象
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		// 把通过DAO获取到的角色和权限放进去
		s.setStringPermissions(permissions);
		s.setRoles(roles);
		return s;
	}

	public static User getUser(String userName) {
		SqlSession session = null;
		try {
			session = GetSession.getSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = session.selectOne("selectUserRolePermission",userName);
		session.close();
		return user;
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String userName = token.getPrincipal().toString();
		String password = new String(t.getPassword());
//		String passwordInDB = userMapper.selectUserByIdGetPassword(userName);
		SqlSession session = null;
		try {
			session = GetSession.getSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = session.selectOne("selectUserByNameGetUser", userName);
		session.close();
		String passwordInDB = user.getPassword();
		String salt = user.getSalt();
		String passwordEncoded = new SimpleHash("md5", password, salt, 2).toString();
//        如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
		if (null == passwordInDB || !passwordInDB.equals(passwordEncoded))
			throw new AuthenticationException();
		// 认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, password, getName());
		return a;
	}

	
}
