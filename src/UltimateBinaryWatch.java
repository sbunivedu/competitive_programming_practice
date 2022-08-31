package northcentral;

import java.util.*;

public class UltimateBinaryWatch {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    char[] num = scan.nextLine().toCharArray();
    char[][] output = new char[4][4];
    int curNum;
    for (int i = 0; i < 4; i++) {
      curNum = num[i] - 48; //48 is ASCII code for '0'
      for (int j = 0; j < 4; j++) {
        output[j][i] = '.';
        if (curNum - (Math.pow(2, 3 - j)) >= 0) {
          curNum -= Math.pow(2, 3 - j);
          output[j][i] = '*';
        }

      }
    }
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        System.out.print(output[i][j]);
        if (j == 0 || j == 2) {
          System.out.print(" ");
        } else if (j == 1) {
          System.out.print("   ");
        } else {
          System.out.println("");
        }
      }
    }
  }
}
