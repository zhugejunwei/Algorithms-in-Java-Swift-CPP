/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
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
