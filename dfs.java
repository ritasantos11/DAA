class dfs {
    public static Stack<Integer> dfs(Grafo0 g) {
        Stack<Integer> s = new Stack<Integer>();
        boolean visitado[] = new boolean[g.num_vertices()+1];
        int pai[] = new int[g.num_vertices()+1];

        for (int v=1; v<=g.num_vertices(); v++) {
            if (!visitado[i]) {
                dfs_visit(g, v, visitado, pai, s);
            }
        }

        return s;
    }

    public static void dfs_visit(Grafo0 g, int v, boolean visitado[], int pai[], Stack<Inetger> s) {
        visitado[v] = true;

        LikedList<Arco> adjs = g.adjs_no(v);
        for (Arco a : adjs) {
            int w = a.extremo_final();
            if (!visitado[w]) {
                pai[w] = v;
                dfs_visit(g, w, visitado, pai, s);
            }
        }

        s.push(v);
    }
}