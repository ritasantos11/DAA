import java.util.Queue;

class es {
    public static void es(Grafo0 g) {
        int es[] = new int[g.num_vertices()];
        int pai[] = new int[g.num_vertices()];
        int grauE[] = new int[g.num_vertices()];
        
        for (int i=1; i<=g.num_vertices(); i++) {
            es[i] = 0;
            pai[i] = -1;
            grauE[i] = 0;
        }

        for (int i=1; i<=g.num_vertices(); i++) {
            LinkedList<Arco> adjs = g.adjs_no(i);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                grauE[w]++;
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i=1; i<=g.num_vertices(); i++)
            if (grauE[i]==0)
                q.add(i);

        int durmin=-1, vf=-1;

        while (!q.isEmpty()) {
            int v = q.remove();
            if (durmin<es[v]) {
                durmin = es[v];
                vf = v;
            }

            LinkedList<Arco> adjs = g.adjs_no(v);
            for (Arco a : adjs) {
                int w = a.extremo_final();
                int d = g.find_arc(v, w);
                if (es[w]<es[v]+d) {
                    es[w] = es[v]+d;
                    pai[w] = v;
                }

                grauE[w]--;
                if (grauE[w]==0) q.add(w);
            }
        }
    }
}