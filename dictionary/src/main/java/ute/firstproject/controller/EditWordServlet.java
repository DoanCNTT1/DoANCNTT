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
import ute.firstproject.model.NodeModel;
import ute.firstproject.services.BinaryTree;
import ute.firstproject.services.CheckError;
import ute.firstproject.utils.MyUtils;

/**
 * Servlet implementation class EditWordServlet
 */
@WebServlet("/EditWordServlet")
public class EditWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		String searchOptions = request.getParameter("searchOptions");
		BinaryTreeModel treeModel=MyUtils.getBinaryTree(request.getSession());
		BinaryTreeModel treeModelVA=MyUtils.getBinaryTreeForViet_Anh(request.getSession());
		String url="";
		if(action.equals("Edit"))
		{
			if((searchOptions.equals("1")))
					{
			BinaryTree binaryTree=new BinaryTree();
			String word=request.getParameter("wordSearch").trim();
			String pronunciation=request.getParameter("Pronunciation").trim();
			String wordtype=request.getParameter("WordType").trim();
			String mean=request.getParameter("Mean").trim();
			NodeModel nodeModel=binaryTree.Search(treeModel.getRoot(), word);
			nodeModel.getData().setWord(word);
			nodeModel.getData().setMean(mean);
			nodeModel.getData().setPronunciation(pronunciation);
			nodeModel.getData().setWordType(wordtype);
			System.out.println(nodeModel.getData().getPronunciation()+"data of nodes");
			VocabularyDAO vocabularyDAO =new VocabularyDAO();
			boolean rs=vocabularyDAO.edit(MyUtils.getStoredConnection(request), nodeModel.getData(),nodeModel.getData().getWord());
			MyUtils.storeBinaryTree(request.getSession(), treeModel);
			String wordmean=request.getParameter("wordMean").trim();
			NodeModel nodeModelVA=binaryTree.Search(treeModelVA.getRoot(), wordmean);
			nodeModelVA.getData().setWord(mean);
			nodeModelVA.getData().setMean(word);
			nodeModelVA.getData().setPronunciation(pronunciation);
			nodeModelVA.getData().setWordType(wordtype);
			MyUtils.storeBinaryTreeVA(request.getSession(), treeModelVA);
			url="/views/web/home.jsp";
					}
			else
			{
				System.out.println("Viet-Anh edit");
				BinaryTree binaryTree=new BinaryTree();
				String word=request.getParameter("wordSearch").trim();
				String pronunciation=request.getParameter("Pronunciation").trim();
				String wordtype=request.getParameter("WordType").trim();
				String mean=request.getParameter("Mean").trim();
				NodeModel nodeModelVA=binaryTree.Search(treeModelVA.getRoot(), word);
				nodeModelVA.getData().setWord(word);
				nodeModelVA.getData().setMean(mean);
				nodeModelVA.getData().setPronunciation(pronunciation);
				nodeModelVA.getData().setWordType(wordtype);
				System.out.println(nodeModelVA.getData().getWord()+"------------");
				VocabularyDAO vocabularyDAO =new VocabularyDAO();
				MyUtils.storeBinaryTreeVA(request.getSession(), treeModelVA);
				String wordmean=request.getParameter("wordMean").trim();
				NodeModel nodeModel=binaryTree.Search(treeModel.getRoot(), wordmean);
				nodeModel.getData().setWord(mean);
				nodeModel.getData().setMean(word);
				nodeModel.getData().setPronunciation(pronunciation);
				nodeModel.getData().setWordType(wordtype);
				boolean rs=vocabularyDAO.edit(MyUtils.getStoredConnection(request), nodeModel.getData(),word);
				MyUtils.storeBinaryTree(request.getSession(), treeModel);
				url="/views/web/home2.jsp";
			}
		
		}
		else if(action.equals("update"))
		{
			if(searchOptions.equals("1"))
			{
			System.out.println("Go to Edit Word");
			String word=request.getParameter("WordEdit").trim();
			BinaryTree binaryTree=new BinaryTree();
			NodeModel nodeModel=binaryTree.Search(treeModel.getRoot(), word);
			request.setAttribute("nodeModel", nodeModel);
			url="/views/web/EditWord.jsp";
			}
			else
			{
				//Cây Việt ANh chưa thực hiện update.
				String word=request.getParameter("WordEdit").trim();
				BinaryTree binaryTree=new BinaryTree();
				NodeModel nodeModel=binaryTree.Search(treeModelVA.getRoot(), word);
				request.setAttribute("nodeModel", nodeModel);
				url="/views/web/EditWord2.jsp";
				System.out.println("Go to Edit Word V-A");
			}

		}
		else if(action.equals("Remove"))
		{
			if(searchOptions.equals("1"))
			{
			String word=request.getParameter("WordEdit").trim();
			BinaryTree binaryTree=new BinaryTree();
			NodeModel nodeModel=binaryTree.Search(treeModel.getRoot(), word);
			NodeModel model=binaryTree.deleteNode(treeModel.getRoot(), word);
			VocabularyDAO vocabularyDAO=new VocabularyDAO();
			vocabularyDAO.delete(MyUtils.getStoredConnection(request), nodeModel.getData().getWord());
			MyUtils.storeBinaryTree(request.getSession(), treeModel);//lưu lại cái cây
			NodeModel modelVA=binaryTree.deleteNode(treeModelVA.getRoot(), nodeModel.getData().getMean());
			MyUtils.storeBinaryTreeVA(request.getSession(), treeModelVA);
			url="/views/web/home.jsp";
			}
			else
			{
				String word=request.getParameter("WordEdit").trim();
				BinaryTree binaryTree=new BinaryTree();
				NodeModel nodeModel=binaryTree.Search(treeModelVA.getRoot(), word);
				List<String> list=binaryTree.find_NLR(treeModelVA.getRoot(), word);
				NodeModel model=binaryTree.deleteNode(treeModelVA.getRoot(), word);
				VocabularyDAO vocabularyDAO=new VocabularyDAO();
				vocabularyDAO.delete(MyUtils.getStoredConnection(request), nodeModel.getData().getMean());
				MyUtils.storeBinaryTreeVA(request.getSession(), treeModelVA);//lưu lại cái cây
				NodeModel modelVA=binaryTree.deleteNode(treeModel.getRoot(), nodeModel.getData().getMean());
				MyUtils.storeBinaryTree(request.getSession(), treeModel);
				url="/views/web/home2.jsp";
			}
		
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
