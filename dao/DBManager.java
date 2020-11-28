package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DBManager extends SnsDAO {

	// ���O�C��ID �ƃp�X���[�h���󂯎��A�o�^���[�U�ꗗ�Ɉ�v�������̂����邩����
	public UserDTO getLoginUser(String loginId, String password) {
		Connection conn = null;            // �f�[�^�x�[�X�ڑ����
		PreparedStatement pstmt = null;    // SQL �Ǘ����
		ResultSet rset = null;             // ��������

		String sql = "SELECT * FROM users WHERE loginId=? AND password=?";
		UserDTO user = null;    // �o�^���[�U���

		try {
			// �f�[�^�x�[�X�ڑ����擾
			conn = getConnection();

			// SELECT ���̓o�^�Ǝ��s
			pstmt = conn.prepareStatement(sql);	// SELECT �\���o�^
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();

			// �������ʂ������
			if (rset.next()) {
				// �K�v�ȗ񂩂�l�����o���A���[�U���I�u�W�F�N�g�𐶐�
				user = new UserDTO();
				user.setLoginId(rset.getString(2));
				user.setPassword(rset.getString(3));
				user.setUserName(rset.getString(4));
				user.setIcon(rset.getString(5));
				user.setProfile(rset.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �f�[�^�x�[�X�ؒf����
			close(rset);
			close(pstmt);
			close(conn);
		}

		return user;
	}

	// �������ݓ��e���X�g�� getter
	public ArrayList<ShoutDTO> getShoutList() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rset = null;

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		try {
			// SnsDAO �N���X�̃��\�b�h�Ăяo��
			conn = getConnection();
			pstmt = conn.createStatement();

			// SELECT ���̎��s
			String sql = "SELECT * FROM shouts ORDER BY date DESC";
			rset = pstmt.executeQuery(sql);

			// �������ʂ̐������J��Ԃ�
			while (rset.next()) {
				// �K�v�ȗ񂩂�l�����o���A�������ݓ��e�I�u�W�F�N�g�𐶐�
				ShoutDTO shout = new ShoutDTO();
				shout.setUserName(rset.getString(2));
				shout.setIcon(rset.getString(3));
				shout.setDate(rset.getString(4));
				shout.setWriting(rset.getString(5));

				// �������ݓ��e�����X�g�ɒǉ�
				list.add(shout);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �f�[�^�x�[�X�ؒf����
			close(rset);
			close(pstmt);
			close(conn);
		}

		return list;
	}

	// ���O�C�����[�U���Ə������ݓ��e���󂯎��A���X�g�ɒǉ�����
	public boolean setWriting(UserDTO user, String writing) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean result = false;
		try {
			conn = getConnection();

			// INSERT ���̓o�^�Ǝ��s
			String sql = "INSERT INTO shouts(userName, icon, date, writing) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getIcon());
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			pstmt.setString(3, sdf.format(calender.getTime()));
			pstmt.setString(4, writing);

			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				// INSERT ���̎��s���ʂ��P�Ȃ�o�^����
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �f�[�^�x�[�X�ؒf����
			close(pstmt);
			close(conn);
		}

		return result;
	}
}
