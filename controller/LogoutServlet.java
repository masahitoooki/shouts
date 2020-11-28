package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ���ڃA�N�Z�X���������ꍇ�� index.jsp �ɏ�����]��
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	// top.jsp �́u���O�A�E�g�v�{�^������Ăяo�����
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �Z�b�V������j��
		HttpSession session = request.getSession();
		session.invalidate();

		// doGet ���\�b�h���Ăяo���Aindex.jsp �ɏ�����]��
		doGet(request, response);
	}
}
