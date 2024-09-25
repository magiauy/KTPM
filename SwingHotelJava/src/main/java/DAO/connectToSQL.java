package DAO;

import java.io.*;
import java.sql.*;


public class connectToSQL {
	protected Connection con;
    private String namedb = "swinghotel2";
//	Thiết lập kiểm tra kết nối
	public boolean openConectionToSQL() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/"+namedb;
			String username="root";
			String password="";
			
			con=DriverManager.getConnection(url,username,password);

			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
public boolean openforcheck() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "";
        con = DriverManager.getConnection(url, username, password);

        if (checkAndCreateDatabase()){
            return true;
        }
    } catch(Exception e) {
                e.printStackTrace();
        System.out.println("Failed to establish a connection to the database: " + e.getMessage());
        return false;
    }
    return false;
}
//	Đóng kết nối
	public void closeConnectionToSQL() {
		try {
			if(con!=null) {
				con.close();
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
//	createStatement
	public Statement createStatement() throws SQLException {
		return con.createStatement();
	}
//	preparedStatement
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

public void loadSQLFile(String filePath) {
    try {
        // Create a Statement
        Statement stmt = con.createStatement();

        // Read the SQL script
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        // Use ";" as a delimiter for each request
        String line = "";
        StringBuilder sb = new StringBuilder();
        while((line = reader.readLine()) != null) {
            // Ignore SQL comments
            if(line.startsWith("--") || line.startsWith("//") || line.startsWith("/*")) {
                continue;
            }
            sb.append(line);
            // If the line ends with a ";", execute it
            if(line.endsWith(";")) {
                stmt.execute(sb.toString());
                sb = new StringBuilder();
            }
        }
        // Close the reader
        reader.close();

        // Close the statement
        stmt.close();
    } catch (IOException | SQLException e) {
        System.out.println(e);
    } finally {
        closeConnectionToSQL();
    }
}
public boolean checkAndCreateDatabase() {
    String dbName = namedb;
    String filePath = "/ktpm.sql";
        try {
            // Create a Statement
            Statement stmt = con.createStatement();

            // Execute SHOW DATABASES
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");

            // Check if the database exists
            boolean dbExists = false;
            while(rs.next()) {
                if(dbName.equals(rs.getString(1))) {
                    dbExists = true;
                    return true;
                }
            }

            // If the database does not exist, load the SQL file
            if(!dbExists) {
                loadSQLFile(filePath);
            }
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnectionToSQL();
        }

    }

public void setMaxConnection() {
		try {
			if (openConectionToSQL()) {
				Statement stmt = con.createStatement();
				stmt.execute("SET GLOBAL max_connections = 1000");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			closeConnectionToSQL();
		}
	}
}
