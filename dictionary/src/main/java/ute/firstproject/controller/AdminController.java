package ute.firstproject.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ute.firstproject.DAO.VocabularyDAO;
import ute.firstproject.model.BinaryTreeModel;
import ute.firstproject.model.NodeModel;
import ute.firstproject.model.OneWordModel;
import ute.firstproject.services.BinaryTree;
import ute.firstproject.services.CheckError;
import ute.firstproject.utils.MyUtils;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
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
		if(treeModel==null)
		{
			List<OneWordModel> allWords = VocabularyDAO.allWords(MyUtils.getStoredConnection(request));
			treeModel = new BinaryTreeModel();
			BinaryTree bTree = new BinaryTree();
			for (int i = 0; i < allWords.size(); i++) {
				treeModel.setRoot(bTree.insertAVL(treeModel.getRoot(), allWords.get(i)));
			}
			MyUtils.storeBinaryTree(request.getSession(), treeModel);
		}
		if(action.equals("Add")&&searchOptions.equals("1"))
		{
			
			String word=request.getParameter("WordAdd").trim();
			String pronunciation=request.getParameter("Pronunciation").trim();
			String wordtype=request.getParameter("WordType").trim();
			String mean=request.getParameter("Mean").trim();
			String mean1=request.getParameter("Mean1");
			String mean2=request.getParameter("Mean2");
			CheckError checkError=new CheckError();
			boolean error=CheckErrorInput(mean, pronunciation, word, wordtype, checkError, treeModel);
			System.out.println("Have error"+error);
			if(error)
			{
				request.setAttribute("checkError", checkError);
				request.setAttribute("word", word);
				request.setAttribute("wordtype", wordtype);
				request.setAttribute("pronunciation", pronunciation);
				request.setAttribute("mean", mean);
				url="/views/web/AddNewWord.jsp";
			}
			else
			{
				//Tạo model phù hợp với cây nhị phân anh việt 
			InsertModelToBinary(mean, pronunciation, word, wordtype, searchOptions, treeModel);
			MyUtils.storeBinaryTree(request.getSession(), treeModel);
			//NodeModel nodeModel=new NodeModel(wordmodel);
			OneWordModel wordmodelInsertDB=SetupModelToInsertDB(mean, pronunciation, word, wordtype, searchOptions);
			VocabularyDAO vocabularyDAO =new VocabularyDAO();
			vocabularyDAO.add(MyUtils.getStoredConnection(request), wordmodelInsertDB);
			//Tạo một model word phù hợp với cây nhị phân việt anh
			InserModelToAnotherBinary(mean, pronunciation, word, wordtype, treeModelVA);
			MyUtils.storeBinaryTreeVA(request.getSession(), treeModelVA);
			url="/views/web/home.jsp";
			}
		}
		else if(action.equals("Add")&&searchOptions.equals("2"))
		{
			String word=request.getParameter("WordAdd").trim();
			String pronunciation=request.getParameter("Pronunciation").trim();
			String wordtype=request.getParameter("WordType").trim();
			String mean=request.getParameter("Mean").trim();
			CheckError checkError=new CheckError();
			boolean error=CheckErrorInput(mean, pronunciation, word, wordtype, checkError, treeModelVA);	;
			
			if(error)
			{
				request.setAttribute("checkError", checkError);
				request.setAttribute("word", word);
				request.setAttribute("wordtype", wordtype);
				request.setAttribute("pronunciation", pronunciation);
				request.setAttribute("mean", mean);
				url="/views/web/AddNewWord.jsp";
			}
			else
			{
				//Tạo model phù hợp với cây nhị phân Việt Anh
				InsertModelToBinary(mean, pronunciation, word, wordtype, searchOptions, treeModelVA);
				MyUtils.storeBinaryTreeVA(request.getSession(), treeModelVA);
				OneWordModel wordmodelInsertDB=SetupModelToInsertDB(mean, pronunciation, word, wordtype, searchOptions);
				VocabularyDAO vocabularyDAO =new VocabularyDAO();
				vocabularyDAO.add(MyUtils.getStoredConnection(request), wordmodelInsertDB);
			//Insert vào cây nhị phân Việt Anh
				InserModelToAnotherBinary(mean, pronunciation, word, wordtype, treeModel);
				MyUtils.storeBinaryTree(request.getSession(), treeModel);
			url="/views/web/home.jsp";
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void InserModelToAnotherBinary(String mean,String pronunciation,String word,String wordtype,BinaryTreeModel treeModel)
	{
				//Ở hàm này ta làm ngược là addmodel vừa tìm được vào cây còn lại (update cây nhị phân còn lại)
				//Cây nhị phân đưa vào sẽ là khác với cây nhị phân với search options
				//Ta có 1 sẽ là số kí hiệu cây nhị phân Anh Việt thì treemodel sẽ là cây nhị phân Việt Anh
					BinaryTree binaryTree=new BinaryTree();		
					OneWordModel wordmodel= new OneWordModel();
					wordmodel.setWord(mean);
					wordmodel.setMean(word);
					wordmodel.setPronunciation(pronunciation);
					wordmodel.setWordType(wordtype);
					binaryTree.insertAVL(treeModel.getRoot(), wordmodel);
	}		
	
	public void InsertModelToBinary(String mean,String pronunciation,String word,String wordtype,String searchOptions,BinaryTreeModel treeModel)
	{
		//Ở hàm này ta thêm cái node vào đúng binary tree mà ta đang làm việc trên đó
		if(searchOptions.equals("1"))
		{
			OneWordModel wordmodelInsertDB= new OneWordModel();
			wordmodelInsertDB.setWord(word);
			wordmodelInsertDB.setMean(mean);
			wordmodelInsertDB.setPronunciation(pronunciation);
			wordmodelInsertDB.setWordType(wordtype);
			BinaryTree binaryTree=new BinaryTree();
			binaryTree.insertAVL(treeModel.getRoot(), wordmodelInsertDB);
		}
		else
		{		
			OneWordModel wordmodel= new OneWordModel();
			wordmodel.setWord(word);
			wordmodel.setMean(mean);
			wordmodel.setPronunciation(pronunciation);
			wordmodel.setWordType(wordtype);
			//Insert vào cây nhị phân Việt Anh
			BinaryTree binaryTree=new BinaryTree();
			binaryTree.insertAVL(treeModel.getRoot(), wordmodel);
		}
	}
	public OneWordModel SetupModelToInsertDB(String mean,String pronunciation,String word,String wordtype,String searchOptions)
	{
		OneWordModel wordmodelInsertDB= new OneWordModel();
		if(searchOptions.equals("1"))
		{
			wordmodelInsertDB.setWord(word);
			wordmodelInsertDB.setMean(mean);
			wordmodelInsertDB.setPronunciation(pronunciation);
			wordmodelInsertDB.setWordType(wordtype);
		}
		else
		{		
			wordmodelInsertDB.setWord(mean);
			wordmodelInsertDB.setMean(word);
			wordmodelInsertDB.setPronunciation(pronunciation);
			wordmodelInsertDB.setWordType(wordtype);
		}
		return wordmodelInsertDB;
	}
	public boolean CheckErrorInput(String mean,String pronunciation,String word,String wordtype,CheckError checkError,BinaryTreeModel treeModel)
	{
		boolean error=false;
		if(!CheckError.CheckMean(mean))
		{
			checkError.setErrorMean("Please enter mean");
			error=true;
		}
		else
		{
			if(!CheckError.HaveNumber(mean))
			{
				error=true;
				checkError.setErrorMean("You must enter word not enter number");
			}
		}
		if(!CheckError.Checkpronunciation(pronunciation))
		{
			checkError.setErrorPro("Please enter pronunciation");
			error=true;
		}
		else
		{
			if(!CheckError.HaveNumber(pronunciation))
			{
				error=true;
				checkError.setErrorPro("Please enter pronunciation word not enter number");
			}
		}
		if(!CheckError.CheckWord(word))
		{
			checkError.setErrorWord("Please enter word");
			error=true;
		}
		else
		{
			if(!CheckError.HaveNumber(word))
			{
				checkError.setErrorWord("Please enter word not enter number");
				error=true;
			}
		}
		if(!CheckError.CheckWordType(wordtype))
		{
			checkError.setErroWordType("Please enter word type");
			error=true;
		}
		else
		{
			if(!CheckError.HaveNumber(wordtype))
			{
				checkError.setErroWordType("Please enter word type with word not enter number");
				error=true;
			}
		}
		if(CheckError.CheckWord(word))
		{
			BinaryTree binaryTree=new BinaryTree();
			NodeModel nodeModel=binaryTree.Search(treeModel.getRoot(), word);
			if(nodeModel!=null)
			{
				error=true;
				checkError.setHaveNode("Word already exist");
			}
		}
		return error;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
