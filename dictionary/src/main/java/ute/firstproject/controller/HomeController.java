package ute.firstproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.BinaryRefAddr;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ute.firstproject.DAO.VocabularyDAO;
import ute.firstproject.model.BinaryTreeModel;
import ute.firstproject.model.NodeModel;
import ute.firstproject.model.OneWordModel;
import ute.firstproject.services.BinaryTree;
import ute.firstproject.utils.MyUtils;

/**
 * Servlet implementation class HomeController
 */

@WebServlet(urlPatterns = { "/trang-chu" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BinaryTreeModel bTreeModel = MyUtils.getBinaryTree(request.getSession());
		BinaryTreeModel bTreeModelVi = MyUtils.getBinaryTreeForViet_Anh(request.getSession());

		// Nếu các session đều rỗng thì khởi tạo giá trị ban đầu cho cây

		if (bTreeModel == null) {
			 List<OneWordModel> allWords =VocabularyDAO.allWords(MyUtils.getStoredConnection(request));
			 bTreeModel =new BinaryTreeModel();
			 BinaryTree bTree = new BinaryTree();
			 for (int i = 0; i< allWords.size(); i++)
			 {
				 bTreeModel.setRoot(bTree.insertAVL(bTreeModel.getRoot(), allWords.get(i))); 
			 }
			MyUtils.storeBinaryTree(request.getSession(), bTreeModel);
			List<String> list= bTree.find_NLR(bTreeModel.getRoot(),bTreeModel.getRoot().getData().getWord());
			MyUtils.storeBinaryTree(request.getSession(), bTreeModel);
		}
		if (bTreeModelVi == null) {
			List<OneWordModel> allWordsVi = VocabularyDAO.allWordsForViet_Anh(MyUtils.getStoredConnection(request));
			bTreeModelVi = new BinaryTreeModel();
			BinaryTree bTree = new BinaryTree();
			for (int i = 0; i < allWordsVi.size(); i++) {
				bTreeModelVi.setRoot(bTree.insertAVL(bTreeModelVi.getRoot(), allWordsVi.get(i)));
			}
			//bTree.find_NLR(bTreeModelVi.getRoot(),bTreeModelVi.getRoot().getData().getWord());
			MyUtils.storeBinaryTreeVA(request.getSession(), bTreeModelVi);
		}
		String url = "";
		if (request.getParameter("searchOptions") == null) {
			url = "/views/web/home.jsp";
		} else if (Integer.parseInt(request.getParameter("searchOptions")) == 1) {
			url = "/views/web/home.jsp";
		} else {
			url = "/views/web/home2.jsp";
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		/*
		 * List<OneWordModel> allWords = VocabularyDAO.allWords(MyUtils.getStoredConnection(request));
			bTreeModel = new BinaryTreeModel();
			BinaryTree bTree = new BinaryTree();
			for (int i = 0; i < allWords.size(); i++) {
				bTreeModel.setRoot(bTree.insertAVL(bTreeModel.getRoot(), allWords.get(i)));
			}
		 * String url=""; //System.out.println(WordSearch); BinaryTreeModel bTreeModel =
		 * MyUtils.getBinaryTree(request.getSession()); BinaryTreeModel
		 * bTreeModelForVA=MyUtils.getBinaryTreeForViet_Anh(request.getSession()); if
		 * (bTreeModel == null|| bTreeModelForVA==null) { List<OneWordModel> allWords =
		 * VocabularyDAO.allWords(MyUtils.getStoredConnection(request)); bTreeModel =
		 * new BinaryTreeModel(); BinaryTree bTree = new BinaryTree(); for (int i = 0; i
		 * < allWords.size(); i++) {
		 * bTreeModel.setRoot(bTree.insertAVL(bTreeModel.getRoot(), allWords.get(i))); }
		 * MyUtils.storeBinaryTree(request.getSession(), bTreeModel); //Load cây nhị
		 * phân Việt Anh. List<OneWordModel> allWordsVA =
		 * VocabularyDAO.allWordsForViet_Anh(MyUtils.getStoredConnection(request));
		 * bTreeModelForVA = new BinaryTreeModel(); BinaryTree bTreeVA = new
		 * BinaryTree();//Business class for (int i = 0; i < allWordsVA.size(); i++) {
		 * bTreeModelForVA.setRoot(bTreeVA.insertAVL(bTreeModelForVA.getRoot(),
		 * allWordsVA.get(i))); } MyUtils.storeBinaryTreeVA(request.getSession(),
		 * bTreeModelForVA);
		 * 
		 * url="/views/web/home.jsp";
		 * 
		 * } else { String WordSearch=request.getParameter("textboxSearch").trim();
		 * String Choose=request.getParameter("ChooseType");
		 * if(WordSearch==null||Choose==null) url="/views/web/home.jsp"; else {
		 * System.out.println(Choose); if(Choose.equals("1")) { BinaryTree tree=new
		 * BinaryTree(); SearchAV(WordSearch, bTreeModel, request, response);
		 * List<String> list=tree.find_NLR(bTreeModel.getRoot(), WordSearch);
		 * System.out.println(list); // BinaryTree bTree = new BinaryTree(); //
		 * System.out.println(WordSearch); // NodeModel
		 * nodeModel=bTree.Search(bTreeModel.getRoot(),WordSearch); //
		 * request.setAttribute("nodeModel",nodeModel); //
		 * System.out.println(nodeModel); url="/views/web/page2.jsp"; } else {
		 * SearchVA(WordSearch, bTreeModelForVA, request, response);
		 * url="/views/web/page2.jsp"; } }
		 * 
		 * Gson gson=new Gson(); BinaryTree binaryTree=new BinaryTree(); PrintWriter
		 * out=response.getWriter();
		 * out.print(gson.toJson(binaryTree.find_NLR(bTreeModel.getRoot(),
		 * WordSearch))); out.flush(); out.close();
		 * 
		 * } RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		 * rd.forward(request, response);
		 * 
		 */ }

	public void SearchAV(String word, BinaryTreeModel bTreeModel, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BinaryTree bTree = new BinaryTree();
		System.out.println(word);
		NodeModel nodeModel = bTree.Search(bTreeModel.getRoot(), word);
		request.setAttribute("nodeModel", nodeModel);
		System.out.println(nodeModel);

	}

	public void SearchVA(String word, BinaryTreeModel bTreeModelVA, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BinaryTree bTree = new BinaryTree();
		System.out.println(word);
		NodeModel nodeModel = bTree.Search(bTreeModelVA.getRoot(), word);
		request.setAttribute("nodeModel", nodeModel);
		System.out.println(nodeModel);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
