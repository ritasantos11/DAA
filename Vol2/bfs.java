import java.util.Queue;

class bfs {
    public static bfs(int s, Grafo0 g) {
        int pai[] = new int[g.num_vertices()+1];
        boolean visitados[] = new boolean[g.num_vertices()+1];

        for (int i=1; i<=g.num_vertices(); i++) {
            pai[i] = 0;
            visitados[i] = false;
        }

        visitados[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while(!q.isEmpty()) {
            int v = q.remove();
            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                if (!visitados[w]) {
                    q.add(w);
                    visitados[w] = true;
                    pai[w] = v;
                }
            }
        }
    }
}