package algo;

import java.util.LinkedList;
import java.util.Queue;

public class GridBFS {

    public static void main(String[] args) {
        char[][] input = {{'*', '*', '*'}, {'D', '0', '*'}, {'0', '0', 'S'}};
        GridBFS gridBFS = new GridBFS();
        System.out.println(gridBFS.findShortestDistance(input));
    }

    public int findShortestDistance(char[][] input){
        Queue<Node> queue = new LinkedList<>();
        try {
            Node[] nodes = findSourceAndDestination(input);

            queue.offer(nodes[0]);
            int width = input[0].length;
            int height = input.length;

            boolean[][] visited = new boolean[width][height];

            while(!queue.isEmpty()){

                Node currentNode = queue.poll();
                if(input[currentNode.getRow()][currentNode.getCol()] == 'D'){
                    return currentNode.getDistance();
                }

                if(currentNode.getRow() - 1 >= 0 && !visited[currentNode.getRow()-1][currentNode.getCol()]){
                    visited[currentNode.getRow()-1][currentNode.getCol()] = true;
                    if(input[currentNode.getRow()-1][currentNode.getCol()] != '0'){
                        Node up = new Node(currentNode.getRow()-1, currentNode.getCol());
                        up.setDistance(currentNode.getDistance()+1);
                        queue.offer(up);
                    }
                }

                if(currentNode.getRow() + 1 < height && !visited[currentNode.getRow()+1][currentNode.getCol()]){
                    visited[currentNode.getRow()+1][currentNode.getCol()] = true;
                    if(input[currentNode.getRow()+1][currentNode.getCol()] != '0') {
                        Node down = new Node(currentNode.getRow() + 1, currentNode.getCol());
                        down.setDistance(currentNode.getDistance() + 1);
                        queue.offer(down);
                    }
                }

                if(currentNode.getCol() - 1 >= 0 && !visited[currentNode.getRow()][currentNode.getCol()-1]){
                    visited[currentNode.getRow()][currentNode.getCol()-1] = true;
                    if(input[currentNode.getRow()][currentNode.getCol()-1] != '0') {
                        Node left = new Node(currentNode.getRow(), currentNode.getCol() - 1);
                        left.setDistance(currentNode.getDistance() + 1);
                        queue.offer(left);
                    }
                }

                if(currentNode.getCol() + 1 < width && !visited[currentNode.getRow()][currentNode.getCol()+1]){
                    visited[currentNode.getRow()][currentNode.getCol()+1] = true;
                    if(input[currentNode.getRow()][currentNode.getCol()+1] != '0') {
                        Node right = new Node(currentNode.getRow(), currentNode.getCol() + 1);
                        right.setDistance(currentNode.getDistance() + 1);
                        queue.offer(right);
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public Node[] findSourceAndDestination(char[][] input) throws Exception{
        Node[] nodes = new Node[2];
        boolean sourceFound = false, destFound = false;
        boolean invalidValue = false;
        for(int row=0; row<input.length; row++){
            for(int col=0; col<input[row].length; col++){
                if(input[row][col] == 'S'){
                    Node source = new Node(row, col);
                    nodes[0] = source;
                    sourceFound = true;
                }else if(input[row][col] == 'D'){
                    Node dest = new Node(row, col);
                    nodes[1] = dest;
                    destFound = true;
                }else if(input[row][col] != '0' && input[row][col] != '*'){
                    invalidValue = true;
                }
            }
        }

        if(!sourceFound || !destFound){
            throw new Exception("Invalid input (source and destination not provided)!!");
        }else if(invalidValue){
            throw new Exception("Invalid input (only 'S', 'D', '0' and '*' are allowed)!!");
        }
        return nodes;
    }


    class Node {
        int row;
        int col;
        int distance;
        boolean visited;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Node(int row, int col, int distance, boolean visited) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.visited = visited;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }
}


