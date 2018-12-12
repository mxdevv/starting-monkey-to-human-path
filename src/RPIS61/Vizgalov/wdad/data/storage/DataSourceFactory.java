package RPIS61.Vizgalov.wdad.data.storage;

import javax.sql.DataSource;
import java.sql.SQLException;
//import com.mysql.cj.jdbc.MysqlDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import RPIS61.Vizgalov.wdad.data.managers.PreferencesManager;
import RPIS61.Vizgalov.wdad.utils.PreferencesManagerConstants;

public class DataSourceFactory throws SQLException {
	public static DataSource createDataSource(String className,
			String driverType, String host, int port, String dbName, String user,
			String password) throws SQLException {
		// System.setProperty("java.library.path", "/usr/lib64/libodbc"); ?
		MysqlDataSource dataSource = new MysqlDataSource();
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
			null, null,
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_HOST_NAME),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_PORT),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_DB_NAME),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_USER),
			preferencesManager.getProperty(PreferencesManagerConstants.DATA_SOURCE_PASS));
	}
}
