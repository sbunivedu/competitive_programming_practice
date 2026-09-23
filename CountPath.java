import java.util.*;

public class CountPath {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int r = scan.nextInt();
    int c = scan.nextInt();
    
    int[][] grid = new int[r][c];
    grid[0][0] = 1;
    for(int i=0; i<r; i++){
      for(int j=0; j<c; j++){
        if(i-1>=0){
          grid[i][j] += grid[i-1][j];
        }
        if(j-1>=0){
          grid[i][j] += grid[i][j-1];
        }
      }
    }

    System.out.println(grid[r-1][c-1]);
  }
}