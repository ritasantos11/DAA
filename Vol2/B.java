// Bacalhaus Congelados

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


class B {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int nnos = stdin.nextInt();
        int ntroços = stdin.nextInt();
        Grafo g = new Grafo(nnos+1);
        for (int i=0; i<ntroços; i++)  {
            int noA = stdin.nextInt();
            int noB = stdin.nextInt();
            int temp = stdin.nextInt();
            int custo = stdin.nextInt();
            g.insert_new_arc(noA, noB, temp);
            g.insert_new_arc(noB, noA, temp);
        }

        int max, min;
        int nnospercurso = stdin.nextInt();
        while (nnospercurso!=0) {
            int a = stdin.nextInt();
            int b = stdin.nextInt();
            max = g.find_arc(a,b).valor_arco();
            min = g.find_arc(a,b).valor_arco();
            for (int i=2; i<nnospercurso; i++) {
                a = b;
                b = stdin.nextInt();

                if (g.find_arc(a, b).valor_arco()>max) {
                    max = g.find_arc(a,b).valor_arco();
                }
                else if (g.find_arc(a,b).valor_arco()<min) {
                    min = g.find_arc(a, b).valor_arco();
                }
            }

            System.out.println(min + " " + max);
            
            nnospercurso = stdin.nextInt();
        }
    }
}
