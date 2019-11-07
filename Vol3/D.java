// Quantas depois

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


class D {
	public static void bfs(Grafo0 g, String s) {
		boolean[] visitado = new boolean[27];
		Queue<Integer> q = new LinkedList<>();
		int l = s.charAt(0)-64;
		q.add(l);
		visitado[l] = true;
		
		int cont=0;
		while (!q.isEmpty()) {
			int v = q.remove();
			LinkedList<Arco> adjs = g.adjs_no(v);
			for (Arco a : adjs) {
				int w = a.extremo_final();
				if (!visitado[w]) {
					visitado[w] = true;
					q.add(w);
					cont++;
				}
			}
		}
		
		System.out.println(cont);
	}
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		Grafo0 g = new Grafo0(26);
		int a, b, size;
		String s = stdin.nextLine();
		String s1 = stdin.nextLine();
		while (s1.compareTo("#") != 0) {
			if (s.length()>s1.length())
				size = s1.length();
			else size = s.length();
			
			for (int i=0; i<size; i++) {
				a = s.charAt(i)-64;
				b = s1.charAt(i)-64;
				if (a!=b) {
					if (g.find_arc(a,b) == null)
						g.insert_new_arc(a,b);
					break;
				}
			}

			s = s1;
			s1 = stdin.nextLine();
		}
		
		String l = stdin.nextLine();
		bfs(g,l);
	}
}
