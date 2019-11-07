// Transporte de Luxo com BFS

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


class C {
    public static void bfs(Grafo g, int origem, int destino) {
        int pai[] = new int[g.num_vertices()+1];
        boolean visitado[] = new boolean[g.num_vertices()+1];

        Queue<Integer> q = new LinkedList<>();
        q.add(origem);
        visitado[origem] = true;

        for (int i=1; i<=g.num_vertices(); i++) pai[i] = -1;

        while (!q.isEmpty()) {
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

        int n=destino, cont=0, flag=0;
        while (n!=origem) {
            if (n==-1) {
                flag=1;
                break;
            }
            n = pai[n];
            cont++;
        }

        if (flag==1) System.out.println("Nao");
        else System.out.println("Sim " + cont);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int tempmin = stdin.nextInt();
        int tempmax = stdin.nextInt();
        int origem = stdin.nextInt();
        int destino = stdin.nextInt();

        int nos = stdin.nextInt();
        int ramos = stdin.nextInt();
        Grafo g = new Grafo(nos);
        for (int i=0; i<ramos; i++) {
            int noA = stdin.nextInt();
            int noB = stdin.nextInt();
            int temp = stdin.nextInt();
            int custo = stdin.nextInt();

            if (temp>=tempmin && temp<=tempmax) {
                g.insert_new_arc(noA, noB, temp);
                g.insert_new_arc(noB, noA, temp);
            }
        }

        bfs(g, origem, destino);
    }
}