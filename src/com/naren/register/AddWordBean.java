package com.naren.register;

public class AddWordBean
{

	String word = null, meaning=null, example=null, pos=null;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getPos() {
		System.out.println("inside of get pos method"+pos);
		return pos;
	}

	public void setPos(String pos) {
		System.out.println("inside of set pos method");
		this.pos = pos;
	}
	
	public String validate()
	{
		String msg="";
		if(word == null || word.trim().equals(""))
		{
			return msg = "U dint entered any word please enter it";
		}
		if(meaning == null || meaning.trim().equals(""))
		{
			return msg = msg+"U dint entered meaning to the word";
		}
		if(example == null || example.trim().equals(""))
		{
			return msg =msg+"U dint entered example";
		}
		if(msg.equals(""))
		{
			return Constants.SUCCESS;
		}
		else
			return msg;
	}
}
