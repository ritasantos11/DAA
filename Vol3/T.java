// Onde está o Wally?

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


class T {
    public static void bfs(Grafo0 g, int origem, int obj[], int k) {
        boolean visitado[] = new boolean[g.num_vertices()+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(origem);
        visitado[origem] = true;

        int u[] = new int[100+1];
        for (int i=1; i<=100; i++) u[i] = -1;
        int cenas[] = new int[k];
        int f=0, i=0, f1=0;
        while (!q.isEmpty()) {
            int v = q.remove();
            
            if (obj[v]==0 && f==0) {
                System.out.println("Wally: " + v);
                f=1;
            }


            if (obj[v]<=k && obj[v]>0 && u[obj[v]]!=1) {
                u[obj[v]] = 1;
                i++;
            }

            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();

                if (!visitado[w]) {
                    q.add(w);
                    visitado[w] = true;
                }
            }
        }

        if (f==0) System.out.println("Wally not found");

        System.out.print(i);

        for (int j=1; j<=100; j++) {
            if (u[j]!=-1)
                System.out.print(" " + j);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int nos = stdin.nextInt();
        int ramos = stdin.nextInt();
        int k = stdin.nextInt();
        int origem = stdin.nextInt();

        int obj[] = new int[nos+1];
        for (int i=1; i<=nos; i++)
            obj[i] = stdin.nextInt();

        Grafo0 g = new Grafo0(nos+1);
        for (int i=0; i<ramos; i++) {
            int noA = stdin.nextInt();
            int noB = stdin.nextInt();
            g.insert_new_arc(noA,noB);
            g.insert_new_arc(noB,noA);
        }

        bfs(g,origem,obj,k);
    }
}