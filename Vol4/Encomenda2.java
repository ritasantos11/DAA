import java.util.*;
import java.lang.*;
import java.util.*;

class Qnode {
    int vert;
    int vertkey;
    
    Qnode(int v, int key) {
		vert = v;
		vertkey = key;
    }
}

class Heapmax {
    private static int posinvalida = 0;
    int sizeMax,size;
    
	Qnode[] a;
	// pos_a[] indica a posição de cada nó do grafo no vetor a[]
    int[] pos_a;

    Heapmax(int vec[], int n) {
		a = new Qnode[n + 1];
		pos_a = new int[n + 1];
		sizeMax = n;
		size = n;
		for (int i = 1; i <= n; i++) {
			a[i] = new Qnode(i,vec[i]);
			pos_a[i] = i;
		}

		for (int i = n/2; i >= 1; i--)
			heapify(i);
    }

    boolean isEmpty() {
	if (size == 0) return true;
	return false;
    }

    int extractMax() {
		int vertv = a[1].vert;
		swap(1,size);
		pos_a[vertv] = posinvalida;  // assinala vertv como removido
		size--;
		heapify(1);
		return vertv;
    }

    void increaseKey(int vertv, int newkey) {

		int i = pos_a[vertv];
		a[i].vertkey = newkey;

		while (i > 1 && compare(i, parent(i)) > 0) { 
			swap(i, parent(i));
			i = parent(i);
		}
    }


    void insert(int vertv, int key)
    { 
	if (sizeMax == size)
	    new Error("Heap is full\n");
	
	size++;
	a[size].vert = vertv;
	pos_a[vertv] = size;   // supondo 1 <= vertv <= n
	increaseKey(vertv,key);   // aumenta a chave e corrige posicao se necessario
    }

    void write_heap(){
	System.out.printf("Max size: %d\n",sizeMax);
	System.out.printf("Current size: %d\n",size);
	System.out.printf("(Vert,Key)\n---------\n");
	for(int i=1; i <= size; i++)
	    System.out.printf("(%d,%d)\n",a[i].vert,a[i].vertkey);
	
	System.out.printf("-------\n(Vert,PosVert)\n---------\n");

	for(int i=1; i <= sizeMax; i++)
	    if (pos_valida(pos_a[i]))
		System.out.printf("(%d,%d)\n",i,pos_a[i]);
    }
    
    private int parent(int i){
	return i/2;
    }
    private int left(int i){
	return 2*i;
    }
    private int right(int i){
	return 2*i+1;
    }

    private int compare(int i, int j) {
	if (a[i].vertkey < a[j].vertkey)
	    return -1;
	if (a[i].vertkey == a[j].vertkey)
	    return 0;
	return 1;
    }

  
    private void heapify(int i) {
	int l, r, largest;

	l = left(i);
	if (l > size) l = i;

	r = right(i);
	if (r > size) r = i;

	largest = i;
	if (compare(l,largest) > 0)
	    largest = l;
	if (compare(r,largest) > 0)
	    largest = r;
	
	if (i != largest) {
	    swap(i, largest);
	    heapify(largest);
	}
	
    }

    private void swap(int i, int j) {
	Qnode aux;
	pos_a[a[i].vert] = j;
	pos_a[a[j].vert] = i;
	aux = a[i];
	a[i] = a[j];
	a[j] = aux;
    }
    
    private boolean pos_valida(int i) {
	return (i >= 1 && i <= size);
    }
}



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



class Encomenda2 {
    static final int maxVerts = 1000;

    public static int min(int a, int b) {
        if (a < b) return a;
            return b;
    }

    public static int resolver(Grafo g, int o, int d, int cmax) {
        int numNos = g.num_vertices();
        int[] cap = new int[numNos+1];
        cap[o] = cmax;

        Heapmax h = new Heapmax(cap, numNos);
        while (!h.isEmpty()) {
            int v = h.extractMax();

            if (v==d || cap[v]==0)
                break;
            
            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                int dist = a.valor_arco();

                if (min(cap[v],dist) > cap[w]) {
					cap[w] = min(cap[v],dist);
					h.increaseKey(w, cap[w]);
				}
            }
        }

        return cap[d];
    }


    public static Grafo read(int largmin, int compmin, int altmin, Scanner stdin) {
        int origem = stdin.nextInt();
        int destino = stdin.nextInt();

        //      chave ,  valor
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(origem,1);
        map.put(destino,2);
        int valor=3;

        Grafo g = new Grafo(maxVerts);
        int noA = stdin.nextInt();
        while (noA != -1) {
            int noB = stdin.nextInt();
            int larg = stdin.nextInt();
            int comp = stdin.nextInt();
            int alt = stdin.nextInt();

            if (!map.containsKey(noA)) {
                map.put(noA,valor);
                valor++;
            }

            if (!map.containsKey(noB)) {
                map.put(noB,valor);
                valor++;
            }

            if (larg>=largmin && comp>=compmin && alt>=altmin) {
                int hnoA = map.get(noA);
                int hnoB = map.get(noB);
                g.insert_new_arc(hnoA,hnoB,comp);
                g.insert_new_arc(hnoB,hnoA,comp);
            }

            noA = stdin.nextInt();
        }
        
        return g;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        
        int largmin = stdin.nextInt();
        int largmax = stdin.nextInt();
        int compmin = stdin.nextInt();
        int compmax = stdin.nextInt();
        int altmin = stdin.nextInt();

        Grafo g = read(largmin, compmin, altmin, stdin);

        System.out.println(resolver(g, 1, 2, compmax));
    }
}