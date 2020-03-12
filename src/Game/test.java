package Game;

import java.util.ArrayList;

public class test {
	public static void main(String args[]) {
		ArrayList<Integer> arrayList= new ArrayList<Integer>();
		arrayList.add(0);
		arrayList.add(1);
		ArrayList<Integer>arrayList2 = new ArrayList<Integer>();
		arrayList2.add(arrayList.get(0));
		arrayList2.set(0, 1);
		System.out.println(arrayList);
	}
}
