import java.util.*;

class Arco {
    int no_final;
    
    Arco(int fim){
	no_final = fim;
    }

    int extremo_final() {
	return no_final;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo0 {
    No verts[];
    int nvs, narcos;
			
    public Grafo0(int n) {
	nvs = n;
	narcos = 0;
	verts  = new No[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new No();
        // para vertices numerados de 1 a n (posicao 0 nao vai ser usada)
    }
    
    public int num_vertices(){
	return nvs;
    }

    public int num_arcos(){
	return narcos;
    }

    public LinkedList<Arco> adjs_no(int i) {
	return verts[i].adjs;
    }
    
    public void insert_new_arc(int i, int j){
	verts[i].adjs.addFirst(new Arco(j));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}


class Quantas {
    public static void procurar_caminho(Grafo0 g, String s) {
        int a = s.charAt(0) - 64;

        for (int i=1; i<s.length(); i++) {
            int b = s.charAt(i) - 64;

            if (g.find_arc(a,b) == null) {
                System.out.println("Nao");
                return;
            }

            a=b;
        }

        System.out.println("Sim");
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        Grafo0 g = new Grafo0(26);
        String s = stdin.nextLine();
        String s1 = stdin.nextLine();
        int a, b, size=0;
        while (s1.compareTo("#") != 0) {
            if (s.length()<s1.length()) size = s.length();
            else size = s1.length();

            for (int i=0; i<size; i++) {
                a = s.charAt(i) - 64;
                b = s1.charAt(i) - 64;

                if (a!=b) {
                    if (g.find_arc(a, b) == null)
                        g.insert_new_arc(a, b);
                    break;
                }
            }

            s = s1;
            s1 = stdin.nextLine();
        }

        String s2 = stdin.nextLine();
        while (s2.compareTo("#") != 0) {
            procurar_caminho(g,s2);
            s2 = stdin.nextLine();
        }
    }
}