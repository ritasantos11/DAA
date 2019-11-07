// Seleçção de rota

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
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int npessoas = stdin.nextInt();
        int origem = stdin.nextInt();
        int dest = stdin.nextInt();
        int nos = stdin.nextInt();
        int ramos = stdin.nextInt();

        Grafo g = new Grafo(nos+1);
        for (int i=0; i<ramos; i++) {
            int noA = stdin.nextInt();
            int noB = stdin.nextInt();
            int p = stdin.nextInt();
            g.insert_new_arc(noA, noB, p);
        }

        int nrotas = stdin.nextInt();
        int rota=0, v=0, v1=0, v2=0, nproblemas=0, nproblemasTotal=0, min=1000000, nlug, d, o;
        for (int i=1; i<=nrotas; i++) {
            int nosrota  = stdin.nextInt();
            o = stdin.nextInt();
            
            for (int j=1; j<nosrota; j++) {
                nlug = stdin.nextInt();
                d = stdin.nextInt();

                nproblemasTotal += g.find_arc(o, d).valor_arco();

                if (o==origem)
                    v=1;
                
                if (v==1 && npessoas>nlug && v1==0)
                    v2=1;

                if (d==dest && v==1 && v2==0 && v1==0) {
                    v1=1;
                    nproblemas = nproblemasTotal;
                }

                o = d;
            }
            
            if (v==1 && v1==1 && v2==0) {
                if (nproblemas < min) {
                    min = nproblemas;
                    rota = i;
                }
            }

            v=0;
            v1=0;
            v2=0;
            nproblemasTotal=0;
            nproblemas=0;
        }
        
        if (rota!=0)
        System.out.println("Reserva na rota " + rota + ": " + min);
        else
            System.out.println("Impossivel");
        
    }
}
