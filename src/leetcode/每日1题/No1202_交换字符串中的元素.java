package leetcode.每日1题;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by xieli on 2021/1/11.
 */
public class No1202_交换字符串中的元素 {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        // 第 1 步：将任意交换的节点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key: 连通分量代表元, value: 同一个连通分量的字符集合(保存在一个优先队列中)
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if (hashMap.containsKey(root)) {
                hashMap.get(root).offer(charArray[i]);
            } else {
                PriorityQueue<Character> minHeap = new PriorityQueue<>();
                minHeap.offer(charArray[i]);
                hashMap.put(root, minHeap);
            }
        }

        // 第 2 步：重组字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            sb.append(hashMap.get(root).poll());
        }
        return sb.toString();

    }

    private class UnionFind {
        private int[] parent;
        /**
         * 以 i 为根节点的子树的高度(引入了路径压缩以后该定义并不准确)
         */
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = i;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根节点的树的高度仅加了 1
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根节点的树的高度不变
            } else {
                // 同理, 此时以 rootX 为根节点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        private int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

    }

}
