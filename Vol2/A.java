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
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		int nos = stdin.nextInt();
		int ntraj = stdin.nextInt();
		Grafo0 g = new Grafo0(nos+1);
		
		for (int i=0; i<ntraj; i++) {
			int nostraj = stdin.nextInt();
			int noA = stdin.nextInt();
			for (int j=1; j<nostraj; j++) {
				int noB = stdin.nextInt();
				if(g.find_arc(noA,noB)==null)
					g.insert_new_arc(noA,noB);
				noA = noB;
			}			
		}
		
		int cont=0;
		for (int i=1; i<=nos; i++) {
			LinkedList<Arco> adjs = g.adjs_no(i);
			for (Arco a : adjs) 
				cont++;
				
			System.out.println(cont);
			cont=0;
		}
	}
}
