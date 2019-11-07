class ks {
    public static void ks(Grafo0 g) {
        Stack<Integer> s = dfs(g);
        boolean visitado[] = new boolean[g.num_vertices()];
        Grafo0 gt = transposto(g);

        while (!s.isEmpty()) {
            int v = s.pop();
            if (!visitado[v])
                bfs(gt, v, visitado);
        }
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
}