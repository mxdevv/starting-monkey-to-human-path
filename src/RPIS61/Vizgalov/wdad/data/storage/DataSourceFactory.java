package RPIS61.Vizgalov.wdad.data.storage;

import javax.sql.DataSource;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
//import com.mysql.cj.jdbc.MysqlDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import RPIS61.Vizgalov.wdad.data.managers.PreferencesManager;
import RPIS61.Vizgalov.wdad.utils.PreferencesManagerConstants;

public class DataSourceFactory {
	public static DataSource createDataSource(String className,
			String driverType, String host, int port, String dbName, String user,
			String password) throws SQLException {
		// System.setProperty("java.library.path", "/usr/lib64/libodbc"); ?
		//private static final String url = "jdbc:mysql://localhost:3306/organization";
		InitialContext initialContext = new InitialContext();
		DataSource dataSource = (DataSource)initialContext.lookup(
				"java:comp/env/jdbc/organization");

		// className ?
		// driverType ?
		dataSource.setServerName(host);
		dataSource.setPortNumber(port);
		dataSource.setDatabaseName(dbName);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		return dataSource;
	}
	public static DataSource createDataSource() throws SQLException {
		PreferencesManager preferencesManager = PreferencesManager.getInstance();
		return createDataSource(
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_CLASS_NAME),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_DRIVER_TYPE),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_HOST_NAME),
			Integer.parseInt(
				preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_PORT)
			),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_DB_NAME),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_USER),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_PASS));
	}
}
