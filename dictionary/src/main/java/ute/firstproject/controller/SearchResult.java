package ute.firstproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ute.firstproject.model.NodeModel;
import ute.firstproject.services.BinaryTree;
import ute.firstproject.utils.MyUtils;

/**
 * Servlet implementation class SearchResult
 */
@WebServlet("/searchResult")
public class SearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchOptions = request.getParameter("searchOptions");
		BinaryTree bTree = new BinaryTree();
		String error = "";
		String url = "";
		if (Integer.parseInt(searchOptions) == 1) {
			if (request.getParameter("term") == null) {
				error = "Bạn vui lòng điền một từ";
				request.setAttribute("error", error);
			}
			else {
				String word = request.getParameter("term");
				System.out.println(word);
				NodeModel node = bTree.Search(MyUtils.getBinaryTree(request.getSession()).getRoot(), word);
				if (node == null) {
					error = "Xin lỗi! Không thể tìm thấy từ này";
					request.setAttribute("error", error);
				}
				else {
					request.setAttribute("result", node);
				}
			}
			url = "/views/web/result.jsp";
		}
		else {
			if (request.getParameter("termEn") == null) {
				error = "Bạn vui lòng điền một từ";
				request.setAttribute("error", error);
			}
			else {
				String word = request.getParameter("termEn");
				NodeModel node = bTree.Search(MyUtils.getBinaryTreeForViet_Anh(request.getSession()).getRoot(), word);
				if (node == null) {
					error = "Xin lỗi! Không thể tìm thấy từ này";
					request.setAttribute("error", error);
				}
				else {
					request.setAttribute("result", node);
				}
			}
			
			url = "/views/web/result2.jsp";
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
