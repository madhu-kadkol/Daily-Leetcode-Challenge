class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        List<Integer>[] path = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            path[i] = new ArrayList<>();
        }
        int[] nodes = new int[2 * n];
        boolean[] exit = new boolean[2 * n];
        int top = 0;
        nodes[top] = 0;
        exit[top++] = false;
        while (top > 0) {
            top--;
            int u = nodes[top];
            int c = s.charAt(u) - 'a';
            if (!exit[top]) {
                path[c].add(u);
                nodes[top] = u;
                exit[top++] = true;
                for (int v : tree[u]) {
                    nodes[top] = v;
                    exit[top++] = false;
                }
            } else {
                int p = parent[u];
                if (path[c].size() > 1) {
                    p = path[c].get(path[c].size() - 2);
                }
                if (p != -1) {
                    ans[p] += ans[u];
                }
                path[c].remove(path[c].size() - 1);
            }
        }
        return ans;
    }
}