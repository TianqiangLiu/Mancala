package Utility;

import java.util.ArrayList;

public class Utility {
	public static String getStringTemplate(ArrayList<Integer> arrayList) {
		String string = String.format("%10d\n"
									+ "%9d|%-2d\n"
									+ "%9d|%-2d\n"
									+ "%3d|%2d|%2d|%-2d|%-2d|%-2d\n"
									+ "%d% 18d\n"
									+ "%3d|%2d|%2d|%-2d|%-2d|%-2d\n"
									+ "%9d|%-2d\n"
									+ "%9d|%-2d\n"
									+ "%10d\n", 
										arrayList.get(0), 
									arrayList.get(1), arrayList.get(23), 
									arrayList.get(2), arrayList.get(22), 
			arrayList.get(5), arrayList.get(4), arrayList.get(3), arrayList.get(21), arrayList.get(20), arrayList.get(19),
			arrayList.get(6), 																			arrayList.get(18), 
			arrayList.get(7), arrayList.get(8), arrayList.get(9), arrayList.get(15), arrayList.get(16), arrayList.get(17), 
			                  		arrayList.get(10), arrayList.get(14), 
			                  		arrayList.get(11), arrayList.get(13)
										, arrayList.get(12));
		
		return string;
	}

}
