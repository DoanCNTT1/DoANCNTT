package ute.firstproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ute.firstproject.DAO.VocabularyDAO;
import ute.firstproject.model.BinaryTreeModel;
import ute.firstproject.model.OneWordModel;
import ute.firstproject.services.BinaryTree;
import ute.firstproject.utils.MyUtils;

/**
 * Servlet implementation class HomeController
 */

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BinaryTreeModel bTreeModel = MyUtils.getBinaryTree(request.getSession());
		if (bTreeModel == null) {
			List<OneWordModel> allWords = VocabularyDAO.allWords(MyUtils.getStoredConnection(request));
			bTreeModel = new BinaryTreeModel();
			BinaryTree bTree = new BinaryTree();
			for (int i = 0; i < allWords.size(); i++) {
				bTreeModel.setRoot(bTree.insertAVL(bTreeModel.getRoot(), allWords.get(i)));
			}
			
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/views/web/home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
}
