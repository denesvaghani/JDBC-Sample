import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class JDBC {
	public static void main(String[] args) throws Exception {

		//	here i am using property file because 
		//  in future i want to change my database system or user or any things related to database 
		//then i can easily change it via property file, 
		//i don't need to touch my code
		Properties ps = new Properties();
		try {
			InputStream is = new FileInputStream(
				//	jdbc.properies file is inside the Properties folder which is inside the Project
					FileSystems.getDefault().getPath("").toAbsolutePath() + "\\Properties\\jdbc.properties");
			try {
				ps.load(is);
			} catch (Exception e) {
				System.out.println("file could not load");
			}
		} catch (Exception e) {
			System.out.println("file not found ");
		}
		
		//here "driver is key in jdbc.properties file"
		Class.forName(ps.getProperty("driver"));

		Connection con = DriverManager.getConnection(ps.getProperty("dburl"), ps.getProperty("user"),
		ps.getProperty("pass"));

		String query = "create table Demo(demo_id int)";

		Statement st = con.createStatement();
		st.execute(query);

	}

}
