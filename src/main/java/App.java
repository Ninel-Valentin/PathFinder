import utils.WindowService;

public class App {
    public static void main(String[] args) {
        // Tries to delete the "sessionStorage" local storage file to assure it is deleted.

        // Anti-aliasing for text and images
        System.setProperty("swing.aatext", "true");
        // WindowService.OpenMainWindow();
        WindowService.OpenEntryDialogue();

        // LocalDataService.SaveLocalData();

        /*
         * UnorientedGraph mainGraph = new UnorientedGraph();
         * Node[] nodes = new Node[] {
         * new Node(0, 0, 0),
         * new Node(1, 0, 100),
         * new Node(2, 50, 150),
         * new Node(3, 200, 250),
         * new Node(4, 250, 200),
         * new Node(5, 150, 100),
         * new Node(6, 100, 50)
         * };
         * for (Node node : nodes) {
         * mainGraph.nodes.add(node.id,node);
         * }
         * LinkedList<LinkedList<Node>> startingEdges = new
         * LinkedList<LinkedList<Node>>() {
         * };
         * 
         * for(int i= 0;i < 6;i++){
         * Node nextNode = Node.getNodeFromId((i+3)%5);
         * LinkedList<Node> startingEdge = new LinkedList<Node>(){
         * };
         * startingEdge.add(nextNode);
         * mainGraph.edges.add(i,startingEdge);
         * }
         * 
         * int nextId = 0;
         * do{
         * System.out.println("Crawling "+nextId);
         * LinkedList<Node> edges = mainGraph.edges.get(nextId);
         * 
         * for (Node node : edges) {
         * System.out.print(node.id + " | ");
         * }
         * 
         * System.out.print("\n");
         * nextId = edges.getFirst().id;
         * }while(nextId != 0);
         */
    }
}
