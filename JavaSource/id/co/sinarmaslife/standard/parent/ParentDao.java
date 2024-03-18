package id.co.sinarmaslife.standard.parent;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.event.RowHandler;

/**
 * Semua DAO akan meng-extend class ini, hanya untuk mempermudah query2 iBatis
 * 
 * @author Yusuf Sutarko
 * @since Feb 12, 2007 (6:51:34 PM)
 */
public abstract class ParentDao extends SqlMapClientDaoSupport {

	protected String statementNameSpace;

	/**
	 * Perintah delete pada iBatis
	 * 
	 * @param queryName Nama Perintah delete pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @return jumlah row yang di-delete
	 * @throws DataAccessException
	 */
	protected int delete(String queryName, Object param) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("DELETE: " + queryName);
		return getSqlMapClientTemplate().delete(this.statementNameSpace + queryName, param);
	}

	/**
	 * Perintah insert pada iBatis
	 * 
	 * @param queryName Nama Perintah insert pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @throws DataAccessException
	 */
	protected void insert(String queryName, Object param) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("INSERT: " + queryName);
		getSqlMapClientTemplate().insert(this.statementNameSpace + queryName, param);
	}

	/**
	 * Perintah query pada iBatis
	 * 
	 * @param queryName Nama Perintah query pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @return List hasil query
	 * @throws DataAccessException
	 */
	protected List query(String queryName, Object param) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("QUERY: " + queryName);
		return getSqlMapClientTemplate().queryForList(this.statementNameSpace + queryName, param);
	}

	/**
	 * Perintah query dengan row handler
	 * 
	 * @param queryName Nama perintah query pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @param rowHandler Hasil query akan dimasukkan pada handler ini
	 * @throws DataAccessException
	 */
	protected void queryHandler(String queryName, Object param, RowHandler rowHandler) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("QUERY ROWHANDLER: " + queryName);
		getSqlMapClientTemplate().queryWithRowHandler(this.statementNameSpace + queryName, param, rowHandler);
	}

	/**
	 * Perintah query dengan hasil Map
	 * 
	 * @param queryName Nama perintah query pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @param keyProperty
	 * @return Map hasil query
	 * @throws DataAccessException
	 */
	protected Map queryMap(String queryName, Object param, String keyProperty) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("QUERY SINGLE: " + queryName);
		return getSqlMapClientTemplate().queryForMap(this.statementNameSpace + queryName, param, keyProperty);
	}

	/**
	 * Perintah query dengan hasil Map
	 * 
	 * @param queryName Nama perintah query pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @param keyProperty
	 * @param valueProperty
	 * @return Map hasil query
	 * @throws DataAccessException
	 */
	protected Map queryMap(String queryName, Object param, String keyProperty, String valueProperty) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("QUERY SINGLE: " + queryName);
		return getSqlMapClientTemplate().queryForMap(this.statementNameSpace + queryName, param, keyProperty, valueProperty);
	}

	/**
	 * Query dengan hasil harus 1 row, kalau tidak, exception
	 * 
	 * @param queryName Nama perintah query pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @return Object hasil query
	 * @throws DataAccessException
	 */
	protected Object querySingle(String queryName, Object param) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("QUERY SINGLE: " + queryName);
		return getSqlMapClientTemplate().queryForObject(this.statementNameSpace + queryName, param);
	}

	/**
	 * Perintah update pada iBatis
	 * 
	 * @param queryName Nama Perintah update pada sqlmap
	 * @param param Parameter yang ditransfer
	 * @return jumlah row yang di-update
	 * @throws DataAccessException
	 */
	protected int update(String queryName, Object param) throws DataAccessException {
		if (logger.isDebugEnabled()) logger.debug("UPDATE: " + queryName);
		return getSqlMapClientTemplate().update(this.statementNameSpace + queryName, param);
	}

}