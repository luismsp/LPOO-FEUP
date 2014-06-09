package logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Ranking implements Serializable {
	private static final long serialVersionUID = 1L;
	
	ArrayList<Pair> listElements = new ArrayList<Pair>();
	
	public Ranking()  {
		
	}
	
	public void addElement(Pair p) {
		listElements.add(p);
		Collections.sort(listElements);
		if(listElements.size() > 10) {
			listElements.remove(listElements.size() - 1);
		}
	}

	@Override
	public String toString() {
		String finalStr = new String();
		finalStr += "Ranking Snooker\n\n";
		finalStr += ("  " + "Rank" + "   Name   " + "Score   \n"); 
		for(int i = 0; i < listElements.size(); i++) {
			finalStr += ("   " + (i+1) + ".        " + listElements.get(i).getName() + "      " + listElements.get(i).getScore() + "\n"); 
		}
		
		return finalStr;
	}
	
	
}
