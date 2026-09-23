import java.util.*;

/*
You have unlimited coins of denominations. 
How many different combinations of coins can make amount `N`?

Input:
5 3
1 2 5

Output:
4

*/

public class CoinChange1 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int target = scan.nextInt();
    int N = scan.nextInt();
    int[] coins = new int[N];
    for(int i=0; i<N; i++){
      coins[i] = scan.nextInt();
    }
    int[] dp = new int[target+1];
    dp[0] = 1;
    for(int coin : coins){
      for(int amount=coin; amount<=target; amount++){
        dp[amount] += dp[amount-coin];
      }
    }
    System.out.println(dp[target]);
  }
}