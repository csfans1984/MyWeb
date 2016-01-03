package com.successfactors.I311616;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;

public class MyClass implements MyInterface {

	private DataSource dataSource;
	
	private JtaTransactionManager txManager;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JtaTransactionManager getTxManager() {
		return txManager;
	}

	public void setTxManager(JtaTransactionManager txManager) {
		this.txManager = txManager;
	}

	@Transactional
	public void doProcess() {
		try {
			Connection con = dataSource.getConnection();
			
//			txManager.getUserTransaction().begin();
			
			PreparedStatement ps = con.prepareStatement("insert into Persons values (8, 'ZXZ')");
			ps.executeUpdate();
			
//			txManager.getUserTransaction().commit();
//			throw new NullPointerException();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
