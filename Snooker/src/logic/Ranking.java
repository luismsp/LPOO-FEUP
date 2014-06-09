package logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Ranking implements Serializable {
	private static final long serialVersionUID = 1L;

	ArrayList<Pair> listElements = new ArrayList<Pair>();

	public Ranking() {}

	public Ranking(Ranking obj) {
		this.listElements = obj.listElements;
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

	public Ranking load() {

		Ranking r = null;

		try {
			FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+ "/rank_info");
			ObjectInputStream is = new ObjectInputStream(fin);

			Object obj = is.readObject();

			if (obj instanceof Ranking)
				r = new Ranking((Ranking) obj);

			is.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return r;
	}

	public void save() {

		ObjectOutputStream file = null;

		try {
			file = new ObjectOutputStream(new FileOutputStream("rank_info"));
			file.writeObject(this);
			file.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
