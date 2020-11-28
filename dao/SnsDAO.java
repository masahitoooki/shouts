package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SnsDAO {
	private final String DSN = "jdbc:mysql://localhost:3306/sns?serverTimezone=UTC";
	private final String USER = "suser";
	private final String PASSWORD = "spass";

	// �f�[�^�x�[�X�̐ڑ�����Ԃ�
	public Connection getConnection(){
		Connection conn = null;

		try {
			// JDBC �h���C�o�̃��[�h
            Class.forName("com.mysql.cj.jdbc.Driver");

            // �f�[�^�x�[�X�֐ڑ�
            conn = DriverManager.getConnection(DSN, USER, PASSWORD);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return conn;
	}

	// Connection �^�ϐ������f�[�^�x�[�X�� JDBC ���\�[�X�̉��
	public void close(Connection conn) {
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Statement �^�ϐ������f�[�^�x�[�X�� JDBC ���\�[�X�̉��
	public void close(Statement stmt) {
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// ResultSet �^�ϐ������f�[�^�x�[�X�� JDBC ���\�[�X�̉��
	public void close(ResultSet rset) {
		if(rset != null){
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
