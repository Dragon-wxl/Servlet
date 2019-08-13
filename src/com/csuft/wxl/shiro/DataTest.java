package com.csuft.wxl.shiro;

import java.util.EnumMap;
import java.util.Enumeration;

import com.csuft.wxl.pojo.Role;

public class DataTest {
//	public static void main(String[] args) throws IOException {
//		User zhang3 = new User();
//		zhang3.setName("zhang3");
//		zhang3.setPassword("1234");
//		zhang3.setSalt(new SecureRandomNumberGenerator().nextBytes().toString());
//		zhang3.setPassword(new SimpleHash("md5", zhang3.getPassword(), zhang3.getSalt(), 2).toString());
//
//		User li4 = new User();
//		li4.setName("li4");
//		li4.setPassword("abcde");
//		li4.setSalt(new SecureRandomNumberGenerator().nextBytes().toString());
//		li4.setPassword(new SimpleHash("md5", li4.getPassword(), li4.getSalt(), 2).toString());
//		System.out.println(zhang3);
//		System.out.println(li4);
//		SqlSession session=GetSession.getSession();
//		int a = session.insert("insertUser", zhang3);
//		int b = session.insert("insertUser", li4);
//		
//		session.commit();
//		session.close();
//		System.out.println("a:"+a+"\n"+"b:"+b);
//	}
	//根据用户名获取角色和权限 三张主表的联合查询 表不全的花会出错
//	public static void main(String[] args) {
//		String userName="root";
//		SqlSession session = null;
//		try {
//			session = GetSession.getSession();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		User user = session.selectOne("selectUserRolePermission",userName);
//		
//		session.close();
//		Set<String> permissions = new HashSet<String>();
//		for (Permission role : user.getPermission()) {
//			permissions.add(role.getName());
//		}
//		Set<String> roles = new HashSet<String>();
//		for (Role role : user.getRole()) {
//			roles.add(role.getName());
//		}
//		System.out.println(roles);
//		System.out.println(permissions);
//	}
	//添加权限
//	public static void main(String[] args) throws IOException {
//		String userName="li4";
////		String perString=Role.ROLE_NAME.admin.toString();
//		String perString=Role.ROLE_NAME.productManager.toString();
//		SqlSession session=GetSession.getSession();
//		
//		int uid=session.selectOne("selectUserByNameGetId",userName);
//		int rid=session.selectOne("selectRoleByNameGetId",perString);
//		
//		Map<String, Integer> map=new HashMap<String, Integer>();
//		map.put("uid", uid);
//		map.put("rid", rid);
//		try {
//			int a=session.insert("insertUserRole",map);
//			System.out.println("已添加 用户 角色关系");
//		} catch (org.apache.ibatis.exceptions.PersistenceException e) {
//			// TODO: handle exception
//			System.out.println("已存在的用户角色关系");
//		}
//		session.commit();
//		session.close();
//	}
	public static void main(String[] args) {
		Role.ROLE_NAME.values();
		for (Object string : Role.ROLE_NAME.values()) {
			System.out.println(string);
		}
		
	}
}
