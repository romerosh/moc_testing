package data.contracts.repositories;

public class RepositoryException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum err_enum {
		c_connection_invalid, c_sql_err, c_transaction_err, c_rollback_err, c_route_data, c_route_already_exist, c_error_unknown, c_id_notFind, c_insertDirectRoute, c_input_data;

		public String getMessage() {
			switch (this) {
			case c_connection_invalid:
				return "Can not get connection from Data source connection pool";
			case c_sql_err:
				return "SQL error";
			default:
				break;
			}
			return this.name();
		}
	}
	
	protected err_enum err;
	public RepositoryException(String errMsg){
		super(errMsg);
	}
	
	public RepositoryException(err_enum err){
		super(err.getMessage());
		
	}
}
