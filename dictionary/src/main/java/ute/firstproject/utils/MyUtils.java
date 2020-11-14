package ute.firstproject.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_NAME_CONNECTION";
	public static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	
	public static void storeConnection(ServletRequest request, Connection conn)
	{
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest request)
	{
		Connection conn = (Connection)request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}
	
//	public static void storeLoginedUser(HttpSession session, AccountModel loginedUser)
//	{
//		session.setAttribute("loginedUser",	loginedUser);
//	}
	
//	public static AccountModel getLoginedUser(HttpSession session)
//	{
//		AccountModel loginedUser = (AccountModel) session.getAttribute("loginedUser");
//		return loginedUser;
//	}
	
//	public static void storeUserCookie(HttpServletResponse response, AccountModel user)
//	{
//		System.out.print("Store user cookie");
//		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getTenTaiKhoan());
//		cookieUserName.setMaxAge(24*60*60);
//		response.addCookie(cookieUserName);
//	}
//	
//	public static String getUserNameInCookie(HttpServletRequest request)
//	{
//		Cookie[] cookies= request.getCookies();
//		if(cookies!=null)
//		{
//			for(Cookie cookie : cookies)
//			{
//				if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
//					return cookie.getValue();
//				}
//			}
//		}
//		return null;
//	}
	
	public static void deleteUserCookie(HttpServletResponse response)
	{
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
}
