 //Author: Nicko Tsagogeorgas
 //Date: November 26, 2025
 //Problem: CCC '10 J5 - Knights' Hop
 //Description:
    //Use BFS algorithm to check each legal move from the starting position
    //Check each subsequent legal move, creating a tree of moves
    //Print distance when the position equals the desired location

import java.util.LinkedList;
import java.util.Scanner;

public class main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        LinkedList<Knight> queue = new LinkedList<>(); //make a queue for the bfs algorithm


        //STARTING POSITION  
        int[] startcoordinates = new int[2]; //starting position of knight
        
        for (int i = 0; i < startcoordinates.length; i++){
            startcoordinates[i] = scan.nextInt(); //receive starting coordinates of knight
        }

        int rstart = startcoordinates[0]; //receive starting row (r)
        int cstart = startcoordinates[1]; //receive starting column (c)


        //ENDING POSITION
        int[] endcoordinates = new int[2]; //ending position of knight

        for (int i = 0; i < endcoordinates.length; i++){
            endcoordinates[i] = scan.nextInt(); //receive ending coordinates of knight
        }

        int rend = endcoordinates[0]; //receive ending row (r)
        int cend = endcoordinates[1]; //receive ending column (c)


        //VISITED 2D ARRAY
        //visited grid (1-8 indices - index 0 unused) to prevent revisiting
        boolean[][] visited = new boolean[9][9];
        visited[rstart][cstart] = true; //starting position has been visited already

        queue.add(new Knight(rstart, cstart, 0)); //add starting knight to the bfs queue 
        

        //BFS ALGORITHM
        while(!queue.isEmpty()){
            Knight current = queue.poll(); //take & remove starting knight in queue

            //check if we've reached the target using the current knight position
            if (current.getR() == rend && current.getC() == cend){
                System.out.println(current.getD()); //print distance if it is at desired location
                break;
            }

            //array showing all 8 possible knight moves
            int[] dr = {2, 2, -2, -2, 1, 1, -1, -1};
            int[] dc = {1, -1, 1, -1, 2, -2, 2, -2};

            for (int i = 0; i < 8; i++){ //run through all 8 possible moves by the knight
                int nr = current.getR() + dr[i]; //transformation of the y value
                int nc = current.getC() + dc[i]; //transformation of the x value

                //only queue valid positions
                if (bounds(nr, nc) && !visited[nr][nc]){ //if within bounds and never visited before
                    visited[nr][nc] = true;
                    queue.add(new Knight(nr, nc, current.getD() + 1)); //add knight to the queue to check next legal moves
                }
            }
        }
        scan.close();
    }

    //CHECK BOUNDARIES
    //check if a coordinate is within the 8x8 board
    public static boolean bounds(int nr, int nc){
        if(nr > 0 && nr <= 8 && nc > 0 && nc <= 8){ //if the position of the knight is in the bounds of the 8x8 chessboard, it is a valid move
            return true;
        }
        return false;
    }
}

class Knight {
    //this class is used to store the current position of the Knight
    //includes current row, current column, and distance (how many moves taken so far)

    private int r;
    private int c;
    private int d;

    public Knight(int r, int c, int d){
        this.r = r; //y value
        this.c = c; //x value
        this.d = d; //number of moves since starting position
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }
}