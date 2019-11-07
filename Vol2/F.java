// Transporte Rápido

import java.util.*;


class Arco {
    int no_final;
    int valor;
    
    Arco(int fim, int v){
	no_final = fim;
	valor = v;
    }

    int extremo_final() {
	return no_final;
    }

    int valor_arco() {
	return valor;
    }

    void novo_valor(int v) {
	valor = v;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo {
    No verts[];
    int nvs, narcos;
			
    public Grafo(int n) {
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
    
    public void insert_new_arc(int i, int j, int valor_ij){
	verts[i].adjs.addFirst(new Arco(j,valor_ij));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}


class F {
	public static void bfs(Grafo0 g, int origem, int dest) {
		boolean visitado[] = new boolean[g.num_vertices()+1];
		int pai[] = new int[g.num_vertices()+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(origem);
		visitado[origem] = true;

		for (int i=1; i<=g.num_vertices(); i++)
			pai[i] = -1;
		
		int cont=0;
		while(!q.isEmpty()) {
			int v = q.remove();
			LinkedList<Arco> adjs = g.adjs_no(v);
			for (Arco a : adjs) {
				int w = a.extremo_final();
				
				if (!visitado[w]) {
					visitado[w] = true;
					q.add(w);
					pai[w] = v;
				}
			}
		}

		int k=dest;
		while (k!=origem) {
			if (k==-1) {
				System.out.println("Impossivel");
				return;
			}
			k=pai[k];
			cont++;
		}
		
		System.out.println(cont);
	}
	
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        int nlocais = stdin.nextInt();
        int largmin = stdin.nextInt();
        int largmax = stdin.nextInt();
        int compmin = stdin.nextInt();
        int compmax = stdin.nextInt();
        int altmin = stdin.nextInt();
        int origem = stdin.nextInt();
        int dest = stdin.nextInt();
        
        Grafo0 g = new Grafo0(nlocais+1);
        int noA = stdin.nextInt();
        while (noA!=-1) {
			int noB = stdin.nextInt();
			int larg = stdin.nextInt();
			int comp = stdin.nextInt();
			int alt = stdin.nextInt();
			
			if (larg>=largmin && comp>=compmin &&  alt>=altmin) {
				g.insert_new_arc(noA,noB);
				g.insert_new_arc(noB,noA);
			}
			
			noA = stdin.nextInt();
		}
		
        bfs(g,origem,dest);
    }
}
