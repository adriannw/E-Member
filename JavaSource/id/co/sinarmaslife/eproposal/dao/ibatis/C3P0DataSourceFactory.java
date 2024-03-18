package id.co.sinarmaslife.eproposal.dao.ibatis;

import java.beans.PropertyVetoException;
import java.util.Map;

import javax.sql.DataSource;

import com.ibatis.sqlmap.engine.datasource.DataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class C3P0DataSourceFactory implements DataSourceFactory {
	protected final Log logger = LogFactory.getLog( getClass() );
    ComboPooledDataSource datasource;
    
    public DataSource getDataSource() {
        return datasource;
    }

    public void initialize(Map map) {
        datasource = new ComboPooledDataSource();
        try {
            datasource.setDriverClass((String) map.get("driverClass"));
            datasource.setJdbcUrl((String) map.get("jdbcUrl"));
            datasource.setUser((String) map.get("user"));
            datasource.setPassword((String) map.get("password"));
            datasource.setMinPoolSize(Integer.valueOf((String) map.get("minPoolSize")));
            datasource.setMaxPoolSize(Integer.valueOf((String) map.get("maxPoolSize")));
            datasource.setAutoCommitOnClose(Boolean.valueOf((String) map.get("autoCommitOnClose")));
        } catch (PropertyVetoException e) {
            logger.error("ERROR", e);
        }
    }

    
    
}
