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

@WebServlet("/bbs")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ���O�C�����[�U���A�������ݓ��e�Ǘ��N���X

	// ���ڃA�N�Z�X���������ꍇ�� index.jsp  �ɏ�����]��
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	// top.jsp �́u���ԁv�{�^������Ă΂��
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writing = request.getParameter("shout");
		RequestDispatcher dispatcher;

		// �������ݓ��e������΁A���X�g�ɒǉ�
		if (!writing.equals("")) {
			HttpSession session = request.getSession();
			// �Z�b�V�������烍�O�C�����[�U�����擾
			UserDTO user = (UserDTO) session.getAttribute("user");

			// �P�x���� DataManager �I�u�W�F�N�g�𐶐�
			if(dbm == null){
				dbm = new DBManager();
			}

			// ���O�C�����[�U���Ə������ݓ��e�������ɁA���X�g�ɒǉ����郁�\�b�h���Ăяo��
			dbm.setWriting(user, writing);

			// �������ݓ��e�ǉ���̃��X�g���擾
			ArrayList<ShoutDTO> list = dbm.getShoutList();

			// ���X�g���Z�b�V�����ɕۑ�
			session.setAttribute("shouts", list);
		}

		// top.jsp �ɏ�����]��
		dispatcher = request.getRequestDispatcher("top.jsp");
		dispatcher.forward(request, response);
	}
}
