// Sopa de Letras

import java.util.*;

class B {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        String s1 = stdin.nextLine();
        String s2 = stdin.nextLine();
        
        char c = ' ', c1= ' ';
        int i=0, v=0, j=0;
		for (i=0; i<s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				c = s1.charAt(i);
				c1 = s2.charAt(i);
				v=1;
				break;
			}
		}
		
		if (v==0)
			System.out.println("Nenhum");
		
		else {
			System.out.print(c + "" + c1);
			System.out.println();
		}
    }
}
