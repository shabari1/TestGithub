
package com.abc.ecart.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author shabarinath
 *
 */
public interface DBConnection {

	public String getInventory() throws SQLException;
	public String getUserDetails() throws SQLException;
	public String getProductDetails() throws SQLException;	
}
