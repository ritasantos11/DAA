// Sobrecarga de trabalhos

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
    public static void es(Grafo g) {
        // algoritmo earliest start
        int es[] = new int[g.num_vertices()+1];
        int grauE[] = new int[g.num_vertices()+1];
        int pai[] = new int[g.num_vertices()+1];

        for (int i=1; i<=g.num_vertices(); i++) {
            es[i]=0;
            grauE[i]=0;
            pai[i]=-1;
        }

        for (int i=1; i<=g.num_vertices(); i++) {
            LinkedList<Arco> adjs = g.adjs_no(i);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                grauE[w]++;
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();
        int durmin=-1, vf=-1;

        for (int i=1; i<=g.num_vertices(); i++)
            if (grauE[i]==0) q.add(i);

        while (!q.isEmpty()) {
            int v = q.remove();
            
            if (durmin<es[v]) {
                durmin=es[v];
                vf=v;
            }

            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                int d = g.find_arc(v, w).valor_arco();
                if (es[w]<es[v]+d) {
                    es[w] = es[v] + d;
                    pai[w]=v;
                }
                
                grauE[w]--;
                if (grauE[w]==0) q.add(w);
            }
        }


        // calcular nº max de tarefas a decorrer ao msm tempo e o 1ºinstante em que isso acontece
        int instantes[] = new int[durmin+1];        
        for (int v=1; v<=g.num_vertices(); v++) {
            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                int d = g.find_arc(v, w).valor_arco();
                for (int j=es[v]; j<es[v]+d; j++)
                    instantes[j]++;
            }
        }

        int maxT=1, maxI=0;
        for (int i=0; i<=durmin; i++) {
            if (instantes[i]>maxT) {
                maxT=instantes[i];
                maxI=i;
            }
        }

        System.out.println(durmin + " " + maxT + " " + maxI);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int nos = stdin.nextInt();
        int ntarefas = stdin.nextInt();

        Grafo g = new Grafo(nos);
        for (int i=0; i<ntarefas; i++) {
            int noA = stdin.nextInt();
            int noB = stdin.nextInt();
            int duracao = stdin.nextInt();
            g.insert_new_arc(noA,noB,duracao);
        }

        es(g);
    }
}
