package com.successfactors.I311616;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		doGetForJDBC(req, resp);
		doGetForSpring(req, resp);
	}
	
	protected void doGetForJDBC(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/test");
			UserTransaction ut = (UserTransaction) initCtx.lookup("java:comp/UserTransaction");
			
			ut.begin();
			
			Connection conn = ds.getConnection();
//			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement("insert into Persons values (5, 'WSY')");
			ps.executeUpdate();
			
			ut.commit();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		resp.getWriter().write("Hello world jdbc!");
	}
	
	protected void doGetForSpring(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MyInterface myInterface = (MyInterface) ctx.getBean("myClass");
		
		try {
			myInterface.doProcess();
		} catch(NullPointerException e) {
			
		}
		

		resp.getWriter().write("Hello world spring!");
	}
}
