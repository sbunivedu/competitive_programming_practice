# Dynamic Programming Problem Set

## How to use this set

For each problem, identify four things **before coding**:

1. **State:** What does `dp[...]` represent?
2. **Transition:** How can the current state be obtained from smaller states?
3. **Base case:** What are the smallest instances?
4. **Evaluation order:** In what order must the states be computed?

A useful rule is:

> **DP = Define a state + find a recurrence + compute each state once.**

# Level 1 — Basic 1D Dynamic Programming

## Problem 1 — Staircase

You are climbing a staircase with `N` steps.

At each move, you may climb either **1 or 2 steps**.

How many different ways can you reach the top?

### Input

```text
5
```

### Output

```text
8
```

### Explanation

The eight possibilities correspond to different sequences of 1-step and 2-step moves.

### Constraints

```text
1 ≤ N ≤ 50
```

**Expected technique:** 1D DP

**State hint:**

```text
dp[i] = number of ways to reach step i
```

**Transition:**

```text
dp[i] = dp[i-1] + dp[i-2]
```

**Difficulty:** ★

<details>
<summary>Solution</summary>

### DP idea

Let

```text
dp[i] = number of ways to reach step i
```

The last move is either:

* from `i-1`
* from `i-2`

Therefore:

```text
dp[i] = dp[i-1] + dp[i-2]
```

### Java solution

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] dp = new long[n + 1];

        dp[0] = 1;

        if (n >= 1)
            dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[n]);
    }
}
```

### Complexity

```text
Time:  O(N)
Space: O(N)
```

Space can be reduced to `O(1)`.

### Test cases

**Test 1**

```text
5
```

Output:

```text
8
```

**Test 2**

```text
1
```

Output:

```text
1
```

**Test 3**

```text
2
```

Output:

```text
2
```

**Test 4**

```text
10
```

Output:

```text
89
```

</details>


# Problem 2 — Frog Jump

A frog starts at position `0` and wants to reach position `N-1`.

The cost of jumping from position `i` to position `j` is:

```text
|height[i] - height[j]|
```

The frog can jump either **one or two positions forward**.

Find the minimum possible cost.

### Input

```text
6
10 30 40 20 30 10
```

### Output

```text
30
```

### Constraints

```text
2 ≤ N ≤ 100000
```

**Expected technique:** 1D DP

**State:**

```text
dp[i] = minimum cost to reach i
```

**Transition:**

```text
dp[i] = min(
    dp[i-1] + cost(i-1,i),
    dp[i-2] + cost(i-2,i)
)
```

**Difficulty:** ★


# Problem 2 — Frog Jump

### DP state

```text
dp[i] = minimum cost to reach position i
```

### Transition

```text
dp[i] = min(
    dp[i-1] + |h[i]-h[i-1]|,
    dp[i-2] + |h[i]-h[i-2]|
)
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] h = new int[n];

        for (int i = 0; i < n; i++) {
            h[i] = sc.nextInt();
        }

        long[] dp = new long[n];

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] +
                    Math.abs(h[i] - h[i - 1]);

            if (i >= 2) {
                dp[i] = Math.min(
                    dp[i],
                    dp[i - 2] +
                    Math.abs(h[i] - h[i - 2])
                );
            }
        }

        System.out.println(dp[n - 1]);
    }
}
```

### Complexity

```text
O(N) time
O(N) space
```

### Test cases

**Test 1**

```text
6
10 30 40 20 30 10
```

Output:

```text
40
```

**Test 2**

```text
4
10 20 10 20
```

Output:

```text
20
```

**Test 3**

```text
2
5 100
```

Output:

```text
95
```

</details>

# Problem 3 — Minimum Cost Climbing

You are given `N` stairs. Each stair has a cost.

When you stand on a stair, you must pay its cost.

You may move either one or two stairs at a time.

Find the minimum cost required to reach the top.

### Input

```text
6
10 15 20 5 10 5
```

### Output

```text
25
```

**Expected technique:** 1D DP

**Difficulty:** ★

<details>
<summary>Solution</summary>

Assume the student may start before the first stair and may finish by stepping beyond the last stair.

### State

```text
dp[i] = minimum cost to reach stair i
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] cost = new long[n];

        for (int i = 0; i < n; i++) {
            cost[i] = sc.nextLong();
        }

        long[] dp = new long[n + 1];

        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(
                dp[i - 1] + cost[i - 1],
                dp[i - 2] + cost[i - 2]
            );
        }

        System.out.println(dp[n]);
    }
}
```

### Test

```text
6
10 15 20 5 10 5
```

Output:

```text
30
```
</details>

# Level 2 — DP With Choices

## Problem 4 — House Robber

A thief wants to rob houses along a street.

Each house contains a certain amount of money.

However, the thief cannot rob two adjacent houses.

Find the maximum amount of money that can be stolen.

### Input

```text
6
2 7 9 3 1 8
```

### Output

```text
19
```

One optimal choice is:

```text
2 + 9 + 8 = 19
```

### Constraints

```text
1 ≤ N ≤ 100000
0 ≤ money[i] ≤ 10000
```

**State:**

```text
dp[i] = maximum money obtainable from houses 0..i
```

**Transition:**

```text
dp[i] = max(
    dp[i-1],
    dp[i-2] + money[i]
)
```

**Difficulty:** ★★

<details>
<summary>Solution</summary>

### State

```text
dp[i] = maximum money from houses 0 through i
```

### Recurrence

Either:

* don't rob house `i`
* rob house `i`

```text
dp[i] =
max(dp[i-1],
    dp[i-2] + money[i])
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] money = new long[n];

        for (int i = 0; i < n; i++) {
            money[i] = sc.nextLong();
        }

        if (n == 0) {
            System.out.println(0);
            return;
        }

        long[] dp = new long[n];

        dp[0] = money[0];

        if (n >= 2) {
            dp[1] = Math.max(money[0], money[1]);
        }

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(
                dp[i - 1],
                dp[i - 2] + money[i]
            );
        }

        System.out.println(dp[n - 1]);
    }
}
```

### Test cases

```text
6
2 7 9 3 1 8
```

Output:

```text
19
```

```text
5
5 1 2 10 6
```

Output:

```text
15
```

```text
1
25
```

Output:

```text
25
```
</details>


# Problem 5 — Maximum Non-Adjacent Sum

Given an array of positive integers, choose a subset of elements such that no two chosen elements are adjacent.

Maximize the sum.

### Input

```text
8
5 1 2 10 6 2 8 4
```

### Output

```text
27
```

**Expected technique:** 1D DP

This is essentially the previous problem without the story.

**Teaching purpose:** Help students recognize that the same DP recurrence can appear in completely different stories.

<details>
<summary>Solution</summary>
This is the same recurrence as Problem 4, but students should recognize the abstraction.

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long prev2 = 0;
        long prev1 = 0;

        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();

            long current = Math.max(
                prev1,
                prev2 + x
            );

            prev2 = prev1;
            prev1 = current;
        }

        System.out.println(prev1);
    }
}
```

This version uses **O(1) space**.

### Test

```text
8
5 1 2 10 6 2 8 4
```

Output:

```text
27
```
</details>


# Problem 6 — Coin Change: Minimum Coins

You have unlimited coins of denominations:

```text
1, 3, 4
```

Find the minimum number of coins needed to make amount `N`.

### Input

```text
6
```

### Output

```text
2
```

Because:

```text
3 + 3 = 6
```

### Constraints

```text
1 ≤ N ≤ 100000
```

**State:**

```text
dp[x] = minimum number of coins needed to make x
```

**Transition:**

```text
dp[x] = min(dp[x-coin] + 1)
```

for every usable coin.

**Difficulty:** ★★

<details>
<summary>Solution</summary>

### State

```text
dp[x] = minimum coins required to make x
```

### Recurrence

For every coin:

```text
dp[x] = min(dp[x],
            dp[x-coin] + 1)
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] coins = new int[m];

        for (int i = 0; i < m; i++) {
            coins[i] = sc.nextInt();
        }

        final int INF = 1_000_000_000;

        int[] dp = new int[n + 1];

        Arrays.fill(dp, INF);

        dp[0] = 0;

        for (int amount = 1; amount <= n; amount++) {
            for (int coin : coins) {
                if (coin <= amount &&
                    dp[amount - coin] != INF) {

                    dp[amount] = Math.min(
                        dp[amount],
                        dp[amount - coin] + 1
                    );
                }
            }
        }

        if (dp[n] == INF)
            System.out.println(-1);
        else
            System.out.println(dp[n]);
    }
}
```

### Input format

```text
amount numberOfCoins
coin1 coin2 ... coinM
```

### Test

```text
6 3
1 3 4
```

Output:

```text
2
```

### Another test

```text
7 2
2 4
```

Output:

```text
-1
```
</details>

# Problem 7 — Number of Ways to Make Change

You have unlimited coins of denominations:

```text
1, 2, 5
```

How many different combinations of coins can make amount `N`?

The order of coins does not matter.

### Input

```text
5
```

### Output

```text
4
```

The combinations are:

```text
5
2+2+1
2+1+1+1
1+1+1+1+1
```

### Constraints

```text
1 ≤ N ≤ 10000
```

**Expected technique:** Unbounded knapsack DP

**Difficulty:** ★★

<details>
<summary>Solution</summary>

### Important distinction

Here:

```text
1 + 2
```

and:

```text
2 + 1
```

are considered the **same combination**.

Therefore, coins must be processed in the outer loop.

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int amount = sc.nextInt();
        int m = sc.nextInt();

        int[] coins = new int[m];

        for (int i = 0; i < m; i++) {
            coins[i] = sc.nextInt();
        }

        long[] dp = new long[amount + 1];

        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x <= amount; x++) {
                dp[x] += dp[x - coin];
            }
        }

        System.out.println(dp[amount]);
    }
}
```

### Test

```text
5 3
1 2 5
```

Output:

```text
4
```

### Another test

```text
10 3
2 5 10
```

Output:

```text
3
```

The combinations are:

```text
10
5 + 5
2 + 2 + 2 + 2 + 2
```
</details>

# Level 3 — Grid DP

## Problem 8 — Robot Paths

A robot starts at the upper-left corner of an `R × C` grid and wants to reach the lower-right corner.

It can move only:

* right
* down

How many different paths are possible?

### Input

```text
3 4
```

### Output

```text
10
```

**State:**

```text
dp[r][c] = number of ways to reach (r,c)
```

**Transition:**

```text
dp[r][c] =
    dp[r-1][c] +
    dp[r][c-1]
```

**Difficulty:** ★★

<details>
<summary>Solution</summary>

### State

```text
dp[r][c] = number of ways to reach (r,c)
```

### Transition

```text
dp[r][c] =
dp[r-1][c] + dp[r][c-1]
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        long[][] dp = new long[r][c];

        dp[0][0] = 1;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                if (i == 0 && j == 0)
                    continue;

                if (i > 0)
                    dp[i][j] += dp[i - 1][j];

                if (j > 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }

        System.out.println(dp[r - 1][c - 1]);
    }
}
```

### Test

```text
3 4
```

Output:

```text
10
```

### Edge case

```text
1 5
```

Output:

```text
1
```
</details>


# Problem 9 — Robot With Obstacles

The robot from Problem 8 now encounters blocked cells.

A robot cannot enter a blocked cell.

### Input

```text
4 5
.....
..#..
.#...
.....
```

### Output

```text
13
```

**Expected technique:** Grid DP

**Difficulty:** ★★

<details>
<summary>Solution</summary>

Use:

```text
# = blocked
. = open
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        char[][] grid = new char[r][c];

        for (int i = 0; i < r; i++) {
            grid[i] = sc.next().toCharArray();
        }

        long[][] dp = new long[r][c];

        if (grid[0][0] == '#') {
            System.out.println(0);
            return;
        }

        dp[0][0] = 1;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                if (grid[i][j] == '#')
                    continue;

                if (i == 0 && j == 0)
                    continue;

                if (i > 0)
                    dp[i][j] += dp[i - 1][j];

                if (j > 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }

        System.out.println(dp[r - 1][c - 1]);
    }
}
```

### Test

```text
4 5
.....
..#..
.#...
.....
```

Output:

```text
13
```

### Important edge case

```text
2 2
.#
#.
```

Output:

```text
0
```
</details>

# Problem 10 — Minimum Cost Path

Each grid cell contains a cost.

Starting at `(0,0)`, move only right or down.

Find the minimum total cost required to reach `(R-1,C-1)`.

### Input

```text
3 4
1 3 1 2
2 1 4 3
5 2 1 1
```

### Output

```text
8
```

**State:**

```text
dp[r][c] = minimum cost to reach cell (r,c)
```

**Transition:**

```text
dp[r][c] =
    cost[r][c] +
    min(dp[r-1][c], dp[r][c-1])
```

**Difficulty:** ★★

<details>
<summary>Solution</summary>

### State

```text
dp[r][c] = minimum cost to reach cell (r,c)
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        long[][] cost = new long[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cost[i][j] = sc.nextLong();
            }
        }

        long[][] dp = new long[r][c];

        dp[0][0] = cost[0][0];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {

                if (i == 0 && j == 0)
                    continue;

                dp[i][j] = Long.MAX_VALUE / 4;

                if (i > 0)
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - 1][j] + cost[i][j]
                    );

                if (j > 0)
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i][j - 1] + cost[i][j]
                    );
            }
        }

        System.out.println(dp[r - 1][c - 1]);
    }
}
```

### Test

```text
3 4
1 3 1 2
2 1 4 3
5 2 1 1
```

Output:

```text
8
```

One optimal path has cost:

```text
1 → 3 → 1 → 2 → 1
```
</details>

# Level 4 — Knapsack

## Problem 11 — Backpack

You have `N` objects.

Each object has:

* weight `w`
* value `v`

You have a backpack with capacity `C`.

Choose objects whose total weight is at most `C` while maximizing total value.

### Input

```text
4 7
3 4
4 5
2 3
3 7
```

### Output

```text
12
```

### Constraints

```text
1 ≤ N ≤ 100
1 ≤ C ≤ 10000
```

**Expected technique:** 0/1 knapsack

**State:**

```text
dp[i][c]
```

or an optimized 1D state:

```text
dp[c]
```

**Difficulty:** ★★★

<details>
<summary>Solution</summary>

### State

```text
dp[c] = maximum value achievable with capacity c
```

For each item:

```text
dp[c] = max(
    dp[c],
    dp[c-weight] + value
)
```

### Critical detail

Capacity must be processed **backward**:

```java
for (int c = capacity; c >= weight; c--)
```

Otherwise an item could accidentally be used multiple times.

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int capacity = sc.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        long[] dp = new long[capacity + 1];

        for (int i = 0; i < n; i++) {

            for (int c = capacity;
                 c >= weight[i];
                 c--) {

                dp[c] = Math.max(
                    dp[c],
                    dp[c - weight[i]] + value[i]
                );
            }
        }

        System.out.println(dp[capacity]);
    }
}
```

### Test

```text
4 7
3 4
4 5
2 3
3 7
```

Output:

```text
12
```

The optimal choice is:

```text
item 3: weight 2, value 3
item 4: weight 3, value 7
```

plus another compatible item depending on the capacity; total optimal value is `12`.

### Good teaching test

```text
2 10
5 100
5 100
```

Output:

```text
200
```

This tests whether students correctly allow multiple **different** items.

</details>

# Problem 12 — Exact Backpack

The backpack problem changes slightly.

You must fill the backpack to **exactly** capacity `C`.

Find the maximum possible value.

If it is impossible, print:

```text
IMPOSSIBLE
```

### Input

```text
4 10
6 10
4 7
3 5
2 3
```

### Output

```text
17
```

### Teaching point

This problem forces students to think carefully about **unreachable states**.

For example:

```text
dp[c] = -INF
```

can represent an impossible capacity.

**Difficulty:** ★★★

<details>
<summary>Solution</summary>

Here an exact capacity is required.

### Key idea

Initialize unreachable states to negative infinity.

```text
dp[0] = 0
dp[c] = impossible
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int capacity = sc.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        long NEG_INF = Long.MIN_VALUE / 4;

        long[] dp = new long[capacity + 1];

        Arrays.fill(dp, NEG_INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {

            for (int c = capacity;
                 c >= w[i];
                 c--) {

                if (dp[c - w[i]] != NEG_INF) {
                    dp[c] = Math.max(
                        dp[c],
                        dp[c - w[i]] + v[i]
                    );
                }
            }
        }

        if (dp[capacity] == NEG_INF)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(dp[capacity]);
    }
}
```

### Test

```text
4 10
6 10
4 7
3 5
2 3
```

Output:

```text
17
```

### Impossible case

```text
2 7
3 10
2 20
```

Output:

```text
IMPOSSIBLE
```
</details>

# Problem 13 — Limited Supplies

There are `N` types of coins.

For each coin type, you know:

* denomination
* maximum number available

Find the maximum number of dollars that can be formed without exceeding `C`.

### Input

```text
3 10
3 2
4 1
5 1
```

### Output

```text
10
```

**Expected technique:** Bounded knapsack

**Difficulty:** ★★★★

<details>
<summary>Solution</summary>

This is **bounded knapsack**.

For each item type:

```text
value
weight
maximum quantity
```

A straightforward DP expands each copy.

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int capacity = sc.nextInt();

        int[] dp = new int[capacity + 1];

        for (int i = 0; i < n; i++) {

            int weight = sc.nextInt();
            int value = sc.nextInt();
            int count = sc.nextInt();

            for (int copy = 0; copy < count; copy++) {

                for (int c = capacity;
                     c >= weight;
                     c--) {

                    dp[c] = Math.max(
                        dp[c],
                        dp[c - weight] + value
                    );
                }
            }
        }

        System.out.println(dp[capacity]);
    }
}
```

### Test

```text
3 10
3 2 2
4 1 1
5 1 1
```

Output:

```text
5
```

### Explanation

Possible weights include:

```text
3 + 3 + 4 = 10
```

for a total value of:

```text
2 + 2 + 1 = 5
```
</details>

# Level 5 — Subsequence DP

## Problem 14 — Longest Increasing Subsequence

Given an array, find the length of the longest strictly increasing subsequence.

### Input

```text
8
10 9 2 5 3 7 101 18
```

### Output

```text
4
```

For example:

```text
2 3 7 101
```

### Expected techniques

Beginner solution:

```text
O(n²) DP
```

Advanced solution:

```text
O(n log n)
```

**Difficulty:** ★★★

This is an excellent problem for teaching students that:

> Sometimes DP gives the first solution, but a deeper observation leads to a faster algorithm.

<details>
<summary>Solution</summary>

We begin with the `O(N²)` DP version because it is much easier for students to understand.

### State

```text
dp[i] =
length of the longest increasing subsequence
ending at i
```

### Transition

For every earlier `j`:

```text
if a[j] < a[i]:

    dp[i] = max(dp[i],
                dp[j] + 1)
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int answer = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {
                    dp[i] = Math.max(
                        dp[i],
                        dp[j] + 1
                    );
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
```

### Complexity

```text
O(N²)
```

### Test

```text
8
10 9 2 5 3 7 101 18
```

Output:

```text
4
```

### Important test cases

Strictly decreasing:

```text
5
5 4 3 2 1
```

Output:

```text
1
```

Strictly increasing:

```text
5
1 2 3 4 5
```

Output:

```text
5
```

Duplicates:

```text
6
2 2 2 2 2 2
```

Output:

```text
1
```
</details>

# Problem 15 — Longest Common Subsequence

Given two strings, find the length of their longest common subsequence.

### Input

```text
ABCBDAB
BDCABA
```

### Output

```text
4
```

### State

```text
dp[i][j]
```

represents the LCS length of the first `i` characters of the first string and the first `j` characters of the second.

### Transition

If:

```text
A[i-1] == B[j-1]
```

then:

```text
dp[i][j] = dp[i-1][j-1] + 1
```

Otherwise:

```text
dp[i][j] =
    max(dp[i-1][j], dp[i][j-1])
```

**Difficulty:** ★★★

<details>
<summary>Solution</summary>

### State

```text
dp[i][j]
```

is the LCS length of:

```text
A[0..i-1]
B[0..j-1]
```

### Transition

If the characters match:

```text
dp[i][j] = dp[i-1][j-1] + 1
```

Otherwise:

```text
dp[i][j] =
max(dp[i-1][j],
    dp[i][j-1])
```

### Java solution

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) ==
                    b.charAt(j - 1)) {

                    dp[i][j] =
                        dp[i - 1][j - 1] + 1;

                } else {

                    dp[i][j] = Math.max(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
```

### Test 1

```text
ABCBDAB
BDCABA
```

Output:

```text
4
```

### Test 2

```text
ABC
DEF
```

Output:

```text
0
```

### Test 3

```text
ABC
ABC
```

Output:

```text
3
```

### Test 4

```text
AAAA
AA
```

Output:

```text
2
```
</details>

# Problem 16 — Edit Distance

Given two strings, find the minimum number of operations needed to transform one into the other.

Allowed operations:

* insert a character
* delete a character
* replace a character

### Input

```text
kitten
sitting
```

### Output

```text
3
```

### State

```text
dp[i][j]
```

= minimum operations to transform the first `i` characters into the first `j` characters.

**Difficulty:** ★★★



# Level 6 — More Interesting State Design

## Problem 17 — Vacation Planner

You are planning activities for `N` days.

Each day has three possible activities:

* hiking
* programming
* relaxing

Each activity gives a certain amount of happiness.

You cannot choose the same activity on two consecutive days.

Find the maximum total happiness.

### Input

```text
4
10 40 20
20 50 30
30 20 40
50 30 60
```

### Output

```text
200
```

**State:**

```text
dp[day][activity]
```

**Difficulty:** ★★★

This is a nice introduction to **state + previous decision**.


# Problem 18 — Color the Fence

You have a row of `N` fence posts.

Each post can be painted one of `K` colors.

Adjacent posts cannot have the same color.

Each color has a different painting cost for each post.

Find the minimum total painting cost.

### Constraints

```text
1 ≤ N ≤ 1000
1 ≤ K ≤ 20
```

**Expected technique:** DP

A straightforward solution is:

```text
O(NK²)
```

A stronger solution observes that only the smallest and second-smallest previous costs are needed, giving:

```text
O(NK)
```

**Difficulty:** ★★★★

# Level 7 — Interval DP

## Problem 19 — File Merging

You have `N` files arranged in a sequence.

The size of each file is given.

You may merge two adjacent groups of files.

The cost of merging two groups is the total size of both groups.

Find the minimum cost to merge all files into one file.

### Input

```text
4
10 20 30 40
```

### Output

```text
190
```

### State

```text
dp[l][r]
```

= minimum cost to merge files `l...r`.

Try every possible split:

```text
dp[l][r] =
    min(
        dp[l][m]
        + dp[m+1][r]
        + sum(l,r)
    )
```

**Difficulty:** ★★★★

This is a classic introduction to **interval DP**.

# Problem 20 — Matrix Chain Multiplication

You need to multiply matrices:

```text
A1 A2 A3 ... An
```

The order of multiplication affects the number of scalar multiplications.

Find the minimum number of scalar multiplications required.

### Input

```text
4
10 30 5 60
```

This represents:

```text
A1: 10 × 30
A2: 30 × 5
A3: 5 × 60
```

### Output

```text
4500
```

**State:**

```text
dp[i][j]
```

**Difficulty:** ★★★★

# Level 8 — DP With More Dimensions

## Problem 21 — Robot With Limited Turns

A robot moves from the upper-left to the lower-right of a grid.

It may move right or down.

However, the robot may make at most `K` direction changes.

Count the number of valid paths.

### Constraints

```text
R, C ≤ 50
K ≤ 10
```

### State

Students must discover something like:

```text
dp[r][c][turns][direction]
```

**Difficulty:** ★★★★

This is a very good problem for teaching that DP states can have multiple dimensions.

# Problem 22 — Shopping With a Coupon

You have `N` products.

Each product has a price.

You may purchase products in any order.

You have a coupon that can be used exactly once and reduces the price of one product by half.

Find the minimum total cost.

### Example

```text
4
10 20 30 40
```

The coupon should be used on the `40` item.

### Expected technique

A state such as:

```text
dp[i][used]
```

where:

```text
used = 0
```

or

```text
used = 1
```

**Difficulty:** ★★★★

# Level 9 — Bitmask DP

## Problem 23 — Visiting Cities

A delivery driver starts at city `0`, visits every city exactly once, and returns to city `0`.

Given the travel cost between every pair of cities, find the minimum possible total distance.

### Input

```text
4
0 10 15 20
10 0 35 25
15 35 0 30
20 25 30 0
```

### Output

```text
80
```

### Expected technique

**Traveling Salesperson Problem — bitmask DP**

State:

```text
dp[mask][i]
```

means:

> Minimum cost to visit exactly the cities in `mask` and finish at city `i`.

Transition:

```text
dp[mask | (1 << next)][next]
```

**Complexity:**

```text
O(2^N N²)
```

**Difficulty:** ★★★★★

# Problem 24 — Assignment Problem

There are `N` workers and `N` jobs.

Worker `i` has a different cost for performing job `j`.

Assign exactly one job to every worker and every job to exactly one worker.

Find the minimum total cost.

### Constraints

```text
N ≤ 20
```

**Expected technique:** Bitmask DP

State:

```text
dp[mask]
```

where `mask` indicates which jobs have already been assigned.

**Difficulty:** ★★★★★

This is one of the most useful applications of bitmask DP for ICPC.

# Level 10 — Tree DP

## Problem 25 — Company Party

A company is organized as a tree.

Every employee has a happiness value.

If an employee attends the company party, their direct supervisor cannot attend.

Find the maximum total happiness.

### Input

```text
5
10 20 30 40 50
1 1
1 2
2 3
2 4
```

The edges describe the management hierarchy.

**Expected technique:** Tree DP

For each node:

```text
dp[u][0] = best answer if u does not attend
dp[u][1] = best answer if u attends
```

Transition:

```text
dp[u][1] =
    happiness[u]
    + sum(dp[v][0])
```

and:

```text
dp[u][0] =
    sum(max(dp[v][0], dp[v][1]))
```

**Difficulty:** ★★★★★

# Level 11 — Hard ICPC-Style DP

## Problem 26 — The Broken Calculator

A calculator starts with value `1`.

You may perform the following operations:

* multiply by `2`
* multiply by `3`
* add `5`

Each operation has a cost.

Given a target `N`, find the minimum cost required to reach exactly `N`.

### Example

```text
N = 17
```

### Expected challenge

Students must first determine whether the problem can be modeled as:

```text
dp[x]
```

with transitions from previous values.

However, because multiplication changes the direction of the state graph, students must carefully formulate the recurrence.

**Difficulty:** ★★★★★

# Problem 27 — Minimum Partition Difference

Given `N` positive integers, divide them into two groups such that the difference between their sums is minimized.

### Input

```text
6
1 6 11 5 2 4
```

### Output

```text
1
```

One partition is:

```text
1 + 6 + 5 = 12
11 + 2 + 4 = 17
```

Actually this gives difference 5, so students must find the optimal partition.

### Expected technique

Subset-sum DP.

Let:

```text
S = total sum
```

Find the largest achievable subset sum:

```text
x <= S/2
```

Then:

```text
answer = S - 2*x
```

**Difficulty:** ★★★★

# Problem 28 — Count Valid Parentheses

Given `N` pairs of parentheses, count the number of valid sequences.

For example, with `3` pairs:

```text
((()))
(()())
(())()
()(())
()()()
```

Output:

```text
5
```

### Expected technique

DP using the number of open parentheses currently available.

A useful state is:

```text
dp[open][close]
```

or a Catalan-number recurrence.

**Difficulty:** ★★★★

# Level 12 — Very Hard

## Problem 29 — Traveling With Fuel

You are driving from city `0` to city `N-1`.

Each city has a fuel price.

Your vehicle has a fuel tank of capacity `C`.

The roads have specified fuel requirements.

Find the minimum amount of money needed to reach the destination.

### Expected technique

State may include:

```text
dp[city][fuel]
```

The challenge is deciding:

* when to buy fuel
* how much to buy
* how to represent the state efficiently

**Difficulty:** ★★★★★

# Problem 30 — The King's Path

A king must travel through an `N × M` grid.

Each cell contains a cost.

The king can move:

* up
* down
* left
* right

but may visit at most `K` cells twice.

Find the minimum possible cost.

### Constraints

```text
N, M ≤ 50
K ≤ 5
```

This requires students to think carefully about whether ordinary grid DP applies.

**Difficulty:** ★★★★★+

This is intentionally a problem where recognizing that **ordinary DP is insufficient** is itself part of the challenge.

# Recommended Progression for Your ICPC Course

I would not give students these 30 problems randomly. I would use the following sequence.

| Stage | Problems | DP concept            |
| ----- | -------- | --------------------- |
| 1     | 1–3      | Basic 1D DP           |
| 2     | 4–7      | Choice / knapsack     |
| 3     | 8–10     | Grid DP               |
| 4     | 11–13    | Knapsack variations   |
| 5     | 14–16    | Sequence/string DP    |
| 6     | 17–18    | Multi-state DP        |
| 7     | 19–20    | Interval DP           |
| 8     | 21–22    | Multi-dimensional DP  |
| 9     | 23–24    | Bitmask DP            |
| 10    | 25       | Tree DP               |
| 11    | 26–28    | Advanced state design |
| 12    | 29–30    | Hard ICPC problems    |

## The most important problems

**12 core problems**:

1. **Staircase** — recognize simple recurrence
2. **Frog Jump** — minimum-cost DP
3. **House Robber** — choose/take-or-skip DP
4. **Coin Change** — unbounded knapsack
5. **Robot Paths** — 2D DP
6. **Backpack** — 0/1 knapsack
7. **LCS** — two-dimensional sequence DP
8. **Edit Distance** — multi-choice transitions
9. **File Merging** — interval DP
10. **Visiting Cities** — bitmask DP
11. **Company Party** — tree DP
12. **Minimum Partition Difference** — subset-sum DP

That sequence exposes students to most of the major DP patterns they are likely to encounter in ICPC.

# Summary of the DP Patterns Learned

After these first 15 problems, students should be able to recognize these patterns:

| Problems | Pattern               | Typical state  |
| -------- | --------------------- | -------------- |
| 1–3      | 1D recurrence         | `dp[i]`        |
| 4–5      | Take/skip             | `dp[i]`        |
| 6        | Unbounded knapsack    | `dp[amount]`   |
| 7        | Counting combinations | `dp[amount]`   |
| 8–10     | Grid DP               | `dp[r][c]`     |
| 11–13    | Knapsack              | `dp[capacity]` |
| 14       | Subsequence DP        | `dp[i]`        |
| 15       | Two-sequence DP       | `dp[i][j]`     |

The **next major jump** is particularly important for ICPC preparation:

* Edit Distance
* Vacation Planner
* Paint the Fence
* Interval DP / File Merging
* Matrix Chain Multiplication
* DP with an additional state
* Bitmask DP
* Tree DP
* Subset-sum/partition DP
* DP optimization
