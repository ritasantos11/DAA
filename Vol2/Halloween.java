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


class Halloween {
	public static void bfs(Grafo0 g, int no, int numabob[]) {
		boolean visitado[] = new boolean[g.num_vertices()+1]; 
		Queue<Integer> q = new LinkedList<>();
		q.add(no);
		visitado[no] = true;
		
		int supermercado=no, j=0;
		while (!q.isEmpty()) {
			int v = q.remove();
			LinkedList<Arco> adjs = g.adjs_no(v);
			for (Arco a : adjs) {
				int w = a.extremo_final();
				if (!visitado[w]) {
					visitado[w] = true;
					q.add(w);
					
					if (numabob[w] > numabob[supermercado]) {
						supermercado = w;
						j=1;
					}
					
					else if (numabob[w]==numabob[supermercado])
						if (w<supermercado)
							supermercado=w;
				}
			}
		}
		
		if (j==0)
			System.out.println("Impossivel");
		else
			System.out.println(supermercado);
		
	}
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		int nos = stdin.nextInt();
		int numabob[] = new int[nos+1];
		for (int i=1; i<=nos; i++)
			numabob[i] = stdin.nextInt();
			
		Grafo0 g = new Grafo0(nos+1);
		int ramos = stdin.nextInt();
		for (int i=0; i<ramos; i++) {
			int noA = stdin.nextInt();
			int noB = stdin.nextInt();
			g.insert_new_arc(noA,noB);
			g.insert_new_arc(noB,noA);
		}
		
		int ncasos = stdin.nextInt();
		for (int i=0; i<ncasos; i++) {
			int no = stdin.nextInt();
			if (numabob[no]==0)
				bfs(g,no,numabob);
			
			else
				System.out.println(no);
		}
	}
}
