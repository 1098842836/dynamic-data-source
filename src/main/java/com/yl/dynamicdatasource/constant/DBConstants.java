package com.yl.dynamicdatasource.constant;
/**
 * 数据库常量
 * @ClassName: DBConstants 
 * @author: UFO
 * @date: 2020年7月21日
 */
public class DBConstants {
	/**数据库密码加密key**/
	public static final String DB_PASSWROD_AES_KEY="de0f8de40cada6e93634ace2488f2a1e";
	/**数据库逻辑删除字段 1001有效 1002无效 **/
	public static final String DB_LOGIC_DELETE_FIELD="is_valid";
	
	/**postgresql数据库驱动名称**/
	public static final String DB_POSTGRESQL_DRIVER_CLASS_NAME="org.postgresql.Driver";
	/**POSTGRESQL验证查询**/
	public static final String DB_POSTGRESQL_VALIDATION_QUERY="select 1";
	
	
	/**oracle数据库驱动名称**/
	public static final String DB_ORACLE_DRIVER_CLASS_NAME="oracle.jdbc.OracleDriver";
	/**ORACLE验证查询**/
	public static final String DB_ORACLE_VALIDATION_QUERY="select 1 from dual";
	
	
	/**mysql数据库驱动名称**/
	public static final String DB_MYSQL_DRIVER_CLASS_NAME="com.mysql.jdbc.Driver";
	/**MYSQL验证查询**/
	public static final String DB_MYSQL_VALIDATION_QUERY="select 1 from dual";
}
