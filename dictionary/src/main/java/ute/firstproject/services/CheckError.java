package ute.firstproject.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckError {
	public String errorWord;
	public String errorPro;
	public String erroWordType;
	public String errorMean;
	public String HaveNode;
	
	public String getHaveNode() {
		return HaveNode;
	}
	public void setHaveNode(String haveNode) {
		HaveNode = haveNode;
	}
	public String getErrorWord() {
		return errorWord;
	}
	public void setErrorWord(String errorWord) {
		this.errorWord = errorWord;
	}
	public String getErrorPro() {
		return errorPro;
	}
	public void setErrorPro(String errorPro) {
		this.errorPro = errorPro;
	}
	public String getErroWordType() {
		return erroWordType;
	}
	public void setErroWordType(String erroWordType) {
		this.erroWordType = erroWordType;
	}
	public String getErrorMean() {
		return errorMean;
	}
	public void setErrorMean(String errorMean) {
		this.errorMean = errorMean;
	}
	public CheckError()
	{
		
	}
	public static boolean  CheckWord(String word)
	{
		if(word==null||word.equals(""))
			return false;
		else
			return true;
	}
	public static boolean  Checkpronunciation(String pronunciation)
	{
		if(pronunciation==null||pronunciation.equals(""))
			return false;
		else
			return true;
	}
	public static boolean  CheckWordType(String wordType)
	{
		if(wordType==null||wordType.equals(""))
			return false;
		else
			return true;
	}
	public static boolean  CheckMean(String mean)
	{
		if(mean==null||mean.equals(""))
			return false;
		else
			return true;
	}
	public static boolean HaveNumber(String wordinput)
	{
		Pattern pattern=Pattern.compile("\\d");
		Matcher matcher=pattern.matcher(wordinput);
		if(matcher.matches())
		{
			System.out.println("Matcher is false");
			return false;
		}
		else 
			return true;
	}
}
