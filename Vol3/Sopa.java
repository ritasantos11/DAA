import java.util.*;

class Sopa {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		String s = stdin.nextLine();
		String s1 = stdin.nextLine();
		
		int i=0;
		for (i=0; i<s.length(); i++) {
			if (s.charAt(i) != s1.charAt(i)) {
				System.out.println(s.charAt(i) + "" + s1.charAt(i));
				break;
			}
		}
		
		if (i==s.length()) System.out.println("Nenhum");
	}
}
