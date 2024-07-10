
// ข้อที่ 3

// ### โจทย์ 3 : การหาทางเดินที่สั้นที่สุดในกราฟ (Shortest Path in Graph)
// **รายละเอียด:** เขียนโปรแกรมที่รับกราฟที่เป็นการเชื่อมต่อระหว่างจุดต่างๆ และหาทางเดินที่สั้นที่สุดระหว่างจุดเริ่มต้นและจุดสิ้นสุดโดยใช้ Dijkstra's Algorithm
// - **อินพุต:** กราฟที่เป็นลิสต์ของคู่จุดเชื่อมต่อ (edges) และน้ำหนัก (weight) ของการเชื่อมต่อแต่ละคู่ จุดเริ่มต้นและจุดสิ้นสุด
// - **เอาท์พุต:** ความยาวของทางเดินที่สั้นที่สุด

// **ตัวอย่าง:**
// - อินพุต: [(1, 2, 1), (2, 3, 2), (1, 3, 4), (3, 4, 1)], จุดเริ่มต้น: 1, จุดสิ้นสุด: 4
// - เอาท์พุต: 4

// ในข้อนี้ ใช้ ChatGPT หาคำตอบให้ แล้วลองทดสอบ ผลลัพท์ออกมาเป็น 4 หากป้อนจุดเริ่มต้น เป็น 1
// ผมลองวาดภาพออกมา
//
//  (1)-(2)
//   |\ /
//   | X
//   |/ \
//  (3)-(4)   จาก 1 มา 4 จะใช้เส้นทาง ในแนว เฉียงขวาล่าง(1,4) เป็นเส้นทางที่ใก้กว่า 1,3,4
//


import java.util.*;

class DijkstraAlgorithm {

    static class Edge { // <--- คลลาส ของเส้น
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph { // <--- คลาสของ กราฟ
        int vertices;
        LinkedList<Edge>[] adjacencyList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) { // <--- เพิ่มเส้นที่ต่อไปยัง node ต่อ node
            Edge edge = new Edge(source, destination, weight);
            adjacencyList[source].addFirst(edge);
        }

        public int dijkstra_GetMinDistances(int startVertex, int endVertex) { // <--- หาเส้นทางที่ไก้ที่สุด
            boolean[] SPT = new boolean[vertices];
            int[] distance = new int[vertices];
            Arrays.fill(distance, Integer.MAX_VALUE);
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(Pair::getKey));

            distance[startVertex] = 0;
            pq.offer(new Pair<>(0, startVertex));

            while (!pq.isEmpty()) {
                int extractedVertex = pq.poll().getValue();
                if (extractedVertex == endVertex) {
                    return distance[endVertex];
                }

                if (!SPT[extractedVertex]) {
                    SPT[extractedVertex] = true;

                    LinkedList<Edge> list = adjacencyList[extractedVertex];
                    for (Edge edge : list) {
                        int destination = edge.destination;
                        if (!SPT[destination]) {
                            int newKey = distance[extractedVertex] + edge.weight;
                            int currentKey = distance[destination];
                            if (currentKey > newKey) {
                                pq.offer(new Pair<>(newKey, destination));
                                distance[destination] = newKey;
                            }
                        }
                    }
                }
            }
            return -1; // <---- นอกเหนือขอบเขต ให้ ส่ง output ไปเป็น -1
        }
    }

    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        int vertices = 5; // <---- พิจารณา index 1 สำหรับ node สุดท้าย
        Graph graph = new Graph(vertices);

        int[][] edges = {
            {1, 2, 1},
            {2, 3, 2},
            {1, 3, 4},
            {3, 4, 1}
        };

        for (int[] edge : edges) {
            graph.addEdge(edge[0] - 1, edge[1] - 1, edge[2]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start node: ");
        int startNode = scanner.nextInt() - 1;
        System.out.print("Enter end node: ");
        int endNode = scanner.nextInt() - 1;
        scanner.close();

        int result = graph.dijkstra_GetMinDistances(startNode, endNode);
        System.out.println("The shortest path distance from node " + (startNode + 1) + " to node " + (endNode + 1) + " is: " + result);
    }
}
