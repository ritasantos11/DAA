// Ilhas

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


class A {
	public static int bfs(Grafo0 g, int origem) {
		boolean[] visitado = new boolean[g.num_vertices()+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(origem);
		visitado[origem] = true;
		
		int max = origem, v, w;
		while (!q.isEmpty()) {
			v = q.remove();
			LinkedList<Arco> adjs = g.adjs_no(v);
			for (Arco a : adjs) {
				w = a.extremo_final();
				if (w>max)
					max = w;
				if (!visitado[w]) {
					q.add(w);
					visitado[w] = true;
				}
			}
		}
		
		return max;
	}
		
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		int nos = stdin.nextInt();
		int ramos = stdin.nextInt();
		Grafo0 g = new Grafo0(nos+1);
		
		int noA, noB;
		int i;
		for (i=0; i<ramos; i++) {
			noA = stdin.nextInt();
			noB = stdin.nextInt();
			if (g.find_arc(noA, noB) == null && g.find_arc(noB, noA) == null) {
				g.insert_new_arc(noA,noB);
				g.insert_new_arc(noB,noA);
			}
		}
		
		int id[] = new int[nos+1];
		for (i=1; i<=nos; i++)
			id[i] = 0;
		
		int v;
		int nquestoes = stdin.nextInt();
		for (i=0; i<nquestoes; i++) {
			v = stdin.nextInt();
			
			if (id[v] == 0) {
				id[v] = bfs(g,v);
				System.out.println("No " + v + ": " + id[v]);
			}
			
			else
				System.out.println("No " + v + ": " + id[v]);
		}
	}
}
