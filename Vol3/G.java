// Sociologia

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


class G {
    public static void kosaraju(Grafo0 g) {
        Stack<Integer> s = dfs(g);
        Grafo0 gt = transposto(g);
        boolean visitado[] = new boolean[g.num_vertices()+1];

        int comgrupo=0, semgrupo=g.num_vertices();
        while (!s.isEmpty()) {
            int v = s.pop();
            int n = bfs(gt, v, visitado);
            if (n>=4) {
                comgrupo++;
                semgrupo -= n;
            }
        }

        System.out.println(comgrupo + " " + semgrupo);
    }

    public static Grafo0 transposto(Grafo0 g) {
        Grafo0 gt = new Grafo0(g.num_vertices());

        for (int v=1; v<=g.num_vertices(); v++) {
            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                gt.insert_new_arc(w, v);
            }
        }

        return gt;
    }

    public static int bfs(Grafo0 g, int origem, boolean visitado[]) {
        Queue<Integer> q = new LinkedList<>();
        q.add(origem);
        visitado[origem] = true;
        
        int n=0;
        while (!q.isEmpty()) {
            int v = q.remove();
            n++;
            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                if (!visitado[w]) {
                    visitado[w] = true;
                    q.add(w);
                }
            }
            
        }

        return n;
    }

    public static Stack<Integer> dfs(Grafo0 g) {
        boolean visitado[] = new boolean[g.num_vertices()+1];
        Stack<Integer> s = new Stack<Integer>();
        
        for (int i=1; i<=g.num_vertices(); i++)
            if (!visitado[i]) dfs_visit(i, g, visitado, s);
        
        return s;
    }

    public static void dfs_visit(int v, Grafo0 g, boolean visitado[], Stack<Integer> s) {
        visitado[v] = true;
        LinkedList<Arco> adjs = g.adjs_no(v);
        for (Arco a : adjs) {
            int w = a.extremo_final();
            if (!visitado[w]) dfs_visit(w, g, visitado, s);
        }

        s.push(v);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        for (int i=1; i<=n; i++) {
            int nalunos = stdin.nextInt();
            Grafo0 g = new Grafo0(nalunos);
            for (int j=0; j<nalunos; j++) {
                int idalunoA = stdin.nextInt();
                int numalunos = stdin.nextInt();
                for (int k=0; k<numalunos; k++) {
                    int idalunoB = stdin.nextInt();
                    g.insert_new_arc(idalunoA, idalunoB);
                }
            }
            System.out.println("Caso #" + i);
            kosaraju(g);
        }
    }
}
