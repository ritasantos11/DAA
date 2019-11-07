// Halloween (BFS)

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



class e {
    public static void bfs(Grafo0 g, int no, int nabob[]) {
        boolean visitados[] = new boolean[g.num_vertices()+1];

        for (int i=1; i<=g.num_vertices(); i++) {
            visitados[i] = false;
        }

        visitados[no] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(no);

        int sup=no, j=1;
        while(!q.isEmpty()) {
            int v = q.remove();
            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                if (!visitados[w]) {
                    q.add(w);
                    visitados[w] = true;
                    if (nabob[w]>nabob[sup]) {
                        sup = w;
                        j=2;
                    }

                    else if (nabob[w]==nabob[sup]) {
                        if (w<sup)
                            sup = w;
                    }
                }
            }
        }

        if (j==1) {
            System.out.println("Impossivel");
            return;
        }

        System.out.println(sup);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int nsuperm = stdin.nextInt();
        int nabob[] = new int[nsuperm+1];
        for (int i=1; i<=nsuperm; i++) {
            nabob[i] = stdin.nextInt();
        }

        Grafo0 g = new Grafo0(nsuperm+1);
        int ramos = stdin.nextInt();
        for (int i=0; i<ramos; i++) {
            int x = stdin.nextInt();
            int y = stdin.nextInt();
            g.insert_new_arc(x, y);
            g.insert_new_arc(y, x);
        }

        int ncasos = stdin.nextInt();
        for (int i=0; i<ncasos; i++) {
            int no = stdin.nextInt();
            if (nabob[no]==0) {
                bfs(g, no, nabob);
            }

            else
                System.out.println(no);            
        }
    }
}
