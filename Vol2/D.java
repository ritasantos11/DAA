// Reservas

import java.util.*;

class Arco {
    int no_final;
    int valor0;
    int valor1;
    
    Arco(int fim, int v0, int v1){
	no_final = fim;
	valor0  = v0;
	valor1 = v1;
    }

    int extremo_final() {
	return no_final;
    }

    int valor0_arco() {
	return valor0;
    }

    int valor1_arco() {
	return valor1;
    }

    void novo_valor0(int v) {
	valor0 = v;
    }

    void novo_valor1(int v) {
	valor1 = v;
    }
}


class No {
    //int label;
    LinkedList<Arco> adjs;

    No() {
	adjs = new LinkedList<Arco>();
    }
}


class Grafo2 {
    No verts[];
    int nvs, narcos;
			
    public Grafo2(int n) {
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
    
    public void insert_new_arc(int i, int j, int valor0, int valor1){
	verts[i].adjs.addFirst(new Arco(j,valor0,valor1));
        narcos++;
    }

    public Arco find_arc(int i, int j){
	for (Arco adj: adjs_no(i))
	    if (adj.extremo_final() == j) return adj;
	return null;
    }
}


class D {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		int nos = stdin.nextInt();
        int ramos = stdin.nextInt();
        Grafo2 g = new Grafo2(nos+1);
        for (int i=0; i<ramos; i++) {
            int origem = stdin.nextInt();
            int dest = stdin.nextInt();
            int nlug = stdin.nextInt();
            int preço = stdin.nextInt();
            g.insert_new_arc(origem, dest, nlug, preço);
        }

		int v=0, v1=0, total=0;
        int totalreservas = stdin.nextInt();
        for (int i=0; i<totalreservas; i++) {
            int nlugnecessarios = stdin.nextInt();
            int nospercurso = stdin.nextInt();
            int percurso[] = new int[nospercurso];
            int noA = stdin.nextInt();
            percurso[0] = noA;
            
            for (int j=1; j<nospercurso; j++) {
                int noB = stdin.nextInt();
                percurso[j] = noB;
                
                if (g.find_arc(noA,noB)==null && v==0 && v1==0) {
					v=1;
                    System.out.println("(" + noA + "," + noB + ") inexistente");
				}
				
				else if (g.find_arc(noA,noB)!=null) {
					if (nlugnecessarios>g.find_arc(noA,noB).valor0_arco() && v==0 && v1==0) {
						v1=1;
                        System.out.println("Sem lugares suficientes em (" + noA + "," + noB + ")");
					}
				}
                
                noA = noB;
            }

			if (v==0 && v1==0) {
                for (int j=1; j<nospercurso; j++) {
                    Arco arc = g.find_arc(percurso[j-1], percurso[j]);
                    total += arc.valor1_arco();
                    int lug = g.find_arc(percurso[j-1],percurso[j]).valor0_arco() - nlugnecessarios;
                    g.find_arc(percurso[j-1], percurso[j]).novo_valor0(lug);
                }
                System.out.println("Total a pagar: " + total*nlugnecessarios);
            }

			total=0;
			v=0;
			v1=0;
        }
	}
}