import java.util.Queue;

class bfs {
    public static void bfs(Grafo0 g, int origem) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean visitado[] = new boolean[g.num_vertices()+1];
        int pai[] = new int[g.num_vertices()+1];

        for (int i=1; i<=g.num_vertices(); i++) pai[i]=-1;

        q.add(origem);
        visitado[origem] = true;

        while (!q.isEmpty()) {
            int v = q.remove();
            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                if (!visitado[w]) {
                    visitado[w] = true;
                    q.add(w);
                }
            }
        }
    }
}