import java.util.*;
/*
Find the minimum number of coins needed to make amount N.

6 3
1 3 4
2

7 2
2 4
-1
*/
public class CoinChange {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int m = sc.nextInt();

    int[] coins = new int[m];
    for(int i=0; i<m; i++){
      coins[i]=sc.nextInt();
    }
    
    final int INF = 1_000_000;

    int[] dp = new int[n+1];
    Arrays.fill(dp, INF);
    
    dp[0] = 0;

    for(int amount = 1; amount<=n; amount++){
      for(int coin : coins){
        int min = dp[amount];
        if(amount-coin >= 0){
          dp[amount] = Math.min(dp[amount],
            dp[amount - coin]+1);
        }
      }
    }
    if(dp[n] == INF){
      System.out.println(-1);
    }else{
      System.out.println(dp[n]);
    }
  }
}
