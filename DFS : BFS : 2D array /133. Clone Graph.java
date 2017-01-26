/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */


// recursion
public class Solution {
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        
        if (map.containsKey(node)) return map.get(node);
        
        UndirectedGraphNode myNode = new UndirectedGraphNode(node.label);
        map.put(node, myNode);
        
        for (UndirectedGraphNode n : node.neighbors) {
            myNode.neighbors.add(cloneGraph(n));
        }
        return myNode;
    }
}


// iteration
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap(); // origin node : copied node
        UndirectedGraphNode myNode = new UndirectedGraphNode(node.label); // copy
        
        map.put(node, myNode);
        
        Queue<UndirectedGraphNode> q = new ArrayDeque(); // origin nodes
        q.add(node);
        
        // bfs traverse graph
        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            // all neighbors of current origin node
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                // if the origin node is not visited
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    // to avoid loop, we just add the unvisited node to queue
                    q.offer(neighbor);
                }
                // add neighbors to the copied node
                // copied node: map.get(cur) -> copied node of cur
                // neighbors: map.get(neighbor) -> copied node of neighbor
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return myNode;
    }
}
