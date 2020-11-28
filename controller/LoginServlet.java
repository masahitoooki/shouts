package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.ShoutDTO;
import dto.UserDTO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ���ڃA�N�Z�X���������ꍇ�� index.jsp �ɏ�����]��
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	// index.jsp �́u���O�C���v�{�^������Ăяo�����
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		String message = null;

		if (loginId.equals("") || password.equals("")) {
			// ���O�C��ID ���p�X���[�h�ǂ��炩�A�������͑o�������͂Ȃ�
			message = "���O�C��ID�ƃp�X���[�h�͕K�{���͂ł�";

			// �G���[���b�Z�[�W�����N�G�X�g�I�u�W�F�N�g�ɕۑ�
			request.setAttribute("alert", message);

			// index.jsp �ɏ�����]��
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} else {
			// ���O�C���F�؂��s���A���[�U�����擾
			DBManager dbm = new DBManager();
			UserDTO user = dbm.getLoginUser(loginId, password);

			if (user != null) {
				// ���[�U�����擾�ł�����A�������ݓ��e���X�g���擾
				ArrayList<ShoutDTO> list = dbm.getShoutList();
				HttpSession session = request.getSession();

				// ���O�C�����[�U���A�������ݓ��e���X�g�Ƃ��ăZ�b�V�����ɕۑ�
				session.setAttribute("user", user);
				session.setAttribute("shouts", list);

				// �����̓]����� top.jsp �Ɏw��
				dispatcher = request.getRequestDispatcher("top.jsp");
			} else {
				// ���[�U��񂪎擾�ł��Ȃ��ꍇ
				// �G���[���b�Z�[�W�����N�G�X�g�I�u�W�F�N�g�ɕۑ�
				message = "���O�C��ID�܂��̓p�X���[�h���Ⴂ�܂�";
				request.setAttribute("alert", message);

				// �����̓]����� index.jsp �Ɏw��
				dispatcher = request.getRequestDispatcher("index.jsp");
			}

			// ������]��
			dispatcher.forward(request, response);
		}
	}
}
