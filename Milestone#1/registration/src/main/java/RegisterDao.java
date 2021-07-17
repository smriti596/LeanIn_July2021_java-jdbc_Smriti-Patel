import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 */

/**
 * @author Kratika Patel
 *
 */
public class RegisterDao {

	private String dbUrl = "jdbc:mysql://localhost:3306/userdb";
	private String dbUname ="root";
	private String dbPassword ="rootpasswordgiven";
	private String dbDriver = "con.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con= null;
		try {
			con=DriverManager.getConnection(dbUrl,dbUname,dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insert(Member member)
	{
		loadDriver(dbDriver);
		Connection con=getConnection();
		String result="Data entered successfully";
		
		String sql="insert into member values(?,?,?,?)";
		
		PreparedStatement ps;
		try
		{
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getUname());
		ps.setString(1, member.getPassword());
		ps.setString(1, member.getEmail());
		ps.setString(1, member.getPhone());
		ps.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
			result="Data not entered";
			
		}
		return result;
	}
}
