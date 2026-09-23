## Problem 7 — Number of Ways to Make Change

### Problem Statement

You are given:

* an integer `amount`
* `N` coin denominations

You have an unlimited supply of each type of coin.

Determine the **number of different combinations of coins** that can make exactly `amount`.

The **order of coins does not matter**.

### Example

Suppose:

```text
amount = 5
coins = {1, 2, 5}
```

The possible combinations are:

```text
5
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1
```

Therefore:

```text
Answer = 4
```

Notice that:

```text
2 + 1 + 1 + 1
1 + 2 + 1 + 1
1 + 1 + 2 + 1
1 + 1 + 1 + 2
```

are **the same combination**.

---

# 1. The Most Important Question

Before writing code, ask students:

> **What exactly are we counting?**

There are two possibilities.

### Combinations

Order doesn't matter:

```text
1 + 2 + 2
2 + 1 + 2
2 + 2 + 1
```

are all one way.

### Permutations

Order matters:

```text
1 + 2 + 2
2 + 1 + 2
2 + 2 + 1
```

would be three different ways.

**This problem counts combinations.**

This distinction is one of the most common sources of errors in DP coin-change problems.

---

# 2. Start With a Smaller Example

Use:

```text
amount = 4
coins = {1, 2}
```

Ask students to list the possibilities.

They should get:

```text
1 + 1 + 1 + 1
1 + 1 + 2
2 + 2
```

So:

```text
Answer = 3
```

Now ask:

> How could we systematically build these solutions?

---

# 3. Think About the Last Coin

Suppose we are trying to make amount `4`.

We have two coin types:

```text
1
2
```

We can divide the possibilities according to whether we use a `2`.

### Don't use a 2

Then we can only use `1`s:

```text
1 + 1 + 1 + 1
```

There is:

```text
1 way
```

### Use at least one 2

Remove one `2`.

We now need to make:

```text
4 - 2 = 2
```

using `1` and `2`.

Those possibilities are:

```text
1 + 1
2
```

So there are:

```text
2 ways
```

Therefore:

```text
ways(4) = ways_without_2(4) + ways_with_2(2)
        = 1 + 2
        = 3
```

This is the basic idea behind the DP.

---

# 4. A Useful DP Interpretation

The cleanest state for beginners is:

> `dp[x]` = number of combinations that make amount `x` using the coin denominations processed so far.

Initially:

```text
dp[0] = 1
```

Why is `dp[0] = 1`?

This is a very important concept.

There is exactly **one way to make zero**:

> Choose no coins.

It may feel strange at first to say that there is one way to make zero, but it gives us the correct recurrence.

---

# 5. Building the DP Table

Consider:

```text
coins = {1, 2, 5}
amount = 5
```

Start with:

```text
dp = [1, 0, 0, 0, 0, 0]
```

Meaning:

```text
amount:  0  1  2  3  4  5
ways:    1  0  0  0  0  0
```

---

## Process coin 1

Coin `1` can be used repeatedly.

For every amount from `1` through `5`:

```text
dp[1] += dp[0]
dp[2] += dp[1]
dp[3] += dp[2]
dp[4] += dp[3]
dp[5] += dp[4]
```

We get:

```text
amount:  0  1  2  3  4  5
ways:    1  1  1  1  1  1
```

There is only one way to make each amount using only pennies.

---

## Process coin 2

Now consider all combinations using `1`s and `2`s.

For amount 2:

```text
dp[2] += dp[0]
       = 1 + 1
       = 2
```

The two ways are:

```text
1 + 1
2
```

For amount 3:

```text
dp[3] += dp[1]
       = 1 + 2
       = 2
```

The possibilities:

```text
1 + 1 + 1
1 + 2
```

For amount 4:

```text
dp[4] += dp[2]
       = 1 + 2
       = 3
```

The possibilities:

```text
1 + 1 + 1 + 1
1 + 1 + 2
2 + 2
```

For amount 5:

```text
dp[5] += dp[3]
       = 1 + 2
       = 3
```

---

## Process coin 5

For amount 5:

```text
dp[5] += dp[0]
       = 3 + 1
       = 4
```

The four combinations are:

```text
1 + 1 + 1 + 1 + 1
1 + 1 + 1 + 2
1 + 2 + 2
5
```

Therefore:

$$
\boxed{4}
$$

---

# 6. The Recurrence

When processing a coin of value `coin`, we can make amount `a` in two conceptual ways:

* Don't use this coin.
* Use at least one of this coin.

The implementation can express the second case as:

$$
dp[a] \mathrel{+}= dp[a-\text{coin}]
$$

So the key recurrence is:

$$
\boxed{dp[a] = dp[a] + dp[a-\text{coin}]}
$$

when:

$$
a \geq coin
$$

---

# 7. The Critical Loop Order

This is probably the **most valuable ICPC lesson in this problem**.

The correct implementation is:

```java
for (int coin : coins) {
    for (int amount = coin; amount <= target; amount++) {
        dp[amount] += dp[amount - coin];
    }
}
```

Notice that the **coin loop comes first**.

Students will often write:

```java
for (int amount = 1; amount <= target; amount++) {
    for (int coin : coins) {
        if (amount >= coin) {
            dp[amount] += dp[amount - coin];
        }
    }
}
```

That solves a **different problem**: it counts permutations where different orders are considered different.

This is an excellent opportunity to teach students:

> **The order of DP loops can change what the DP counts.**

---

# 8. Why Does `coin` Come First?

Think of the algorithm as saying:

> First decide which denominations are available. Then count ways to use them.

After processing coin `1`, we have:

```text
{1}
```

After processing coin `2`, we have:

```text
{1, 2}
```

After processing coin `5`, we have:

```text
{1, 2, 5}
```

Because we process denominations in a fixed order, we don't separately count:

```text
1 + 2
2 + 1
```

They are generated as the same combination.

---

# 9. Student Trace Exercise

I would have students manually complete this table before coding.

Given:

```text
coins = {1, 2, 5}
target = 5
```

### After coin 1

| Amount |  0 |  1 |  2 |  3 |  4 |  5 |
| ------ | -: | -: | -: | -: | -: | -: |
| Ways   |  1 |  ? |  ? |  ? |  ? |  ? |

### After coin 2

| Amount |  0 |  1 |  2 |  3 |  4 |  5 |
| ------ | -: | -: | -: | -: | -: | -: |
| Ways   |  1 |  ? |  ? |  ? |  ? |  ? |

### After coin 5

| Amount |  0 |  1 |  2 |  3 |  4 |  5 |
| ------ | -: | -: | -: | -: | -: | -: |
| Ways   |  1 |  ? |  ? |  ? |  ? |  ? |

The completed table is:

```text
After coin 1:
1 1 1 1 1 1

After coin 2:
1 1 2 2 3 3

After coin 5:
1 1 2 2 3 4
```

---

# 10. Java Solution

A clean ICPC implementation is:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int target = sc.nextInt();
        int n = sc.nextInt();

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        long[] dp = new long[target + 1];

        // One way to make amount 0:
        // choose no coins.
        dp[0] = 1;

        for (int coin : coins) {
            for (int amount = coin;
                 amount <= target;
                 amount++) {

                dp[amount] += dp[amount - coin];
            }
        }

        System.out.println(dp[target]);
    }
}
```

### Input

```text
5 3
1 2 5
```

### Output

```text
4
```

---

# 11. A Very Important Comparison

I would actually give students these **two programs side-by-side**:

### Combinations — order doesn't matter

```java
for (int coin : coins) {
    for (int amount = coin; amount <= target; amount++) {
        dp[amount] += dp[amount - coin];
    }
}
```

### Permutations — order matters

```java
for (int amount = 1; amount <= target; amount++) {
    for (int coin : coins) {
        if (amount >= coin) {
            dp[amount] += dp[amount - coin];
        }
    }
}
```

For:

```text
target = 4
coins = {1, 2}
```

the answers are:

**Combinations:**

```text
3
```

```text
1+1+1+1
1+1+2
2+2
```

**Permutations:**

```text
5
```

```text
1+1+1+1
1+1+2
1+2+1
2+1+1
2+2
```

This makes the loop-order issue much more concrete.

---

# 12. Suggested ICPC Kata Guidance

For your students, I would **not initially give them the recurrence**. Give them these hints progressively.

### Hint 1

> Does the order of coins matter?

### Hint 2

> What does `dp[x]` represent?

### Hint 3

> How many ways are there to make amount `0`?

### Hint 4

> Suppose the current coin has value `c`. How could using this coin help you make amount `x`?

### Hint 5

> If you use coin `c`, how much money remains?

### Hint 6

> Should the coin loop or the amount loop be the outer loop if order does not matter?

That gives students a good **ICPC-style discovery process** rather than simply handing them the solution.

---

## Test Cases

### Test 1 — Basic

```text
5 3
1 2 5
```

Output:

```text
4
```

### Test 2 — No solution

```text
3 2
2 4
```

Output:

```text
0
```

### Test 3 — One denomination

```text
10 1
2
```

Output:

```text
1
```

### Test 4 — Target zero

```text
0 3
1 2 5
```

Output:

```text
1
```

### Test 5 — Larger example

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
2+2+2+2+2
2+2+2+2+2   ← wait
```

Actually, this is a good place to catch an important issue: with `{2,5,10}`, the valid combinations for 10 are:

```text
2+2+2+2+2
5+5
10
```

so the answer is indeed:

```text
3
```

---

## The Core ICPC Takeaway

I would have students memorize **the idea, not the code**:

> **For counting coin combinations with unlimited coins, process each coin first, then amounts from small to large.**

The pattern is:

```text
dp[0] = 1

for each coin:
    for amount = coin ... target:
        dp[amount] += dp[amount - coin]
```

Once students understand **why the loop order prevents duplicate permutations**, they have learned a very useful DP pattern that will reappear in knapsack, subset counting, path counting, and many ICPC problems.

Here is a student-friendly **trace exercise + answer key** for Problem 7, using `target = 5` and coins `{1, 2, 5}`.

### Student Trace Exercise

**Goal:** Trace the DP table as each coin is processed.

Recall:

> `dp[x]` = number of ways to make amount `x` using the coins processed so far.

Initial condition:

> `dp[0] = 1`

#### Step 1 — Before processing any coins

Fill in the table.

|           Amount |     0 |   1 |   2 |   3 |   4 |   5 |
| ---------------: | ----: | --: | --: | --: | --: | --: |
| **Initial `dp`** | **1** | ___ | ___ | ___ | ___ | ___ |

---

#### Step 2 — Process coin `1`

For each amount from 1 to 5:

`dp[amount] += dp[amount - 1]`

|           Amount |  0 |   1 |   2 |   3 |   4 |   5 |
| ---------------: | -: | --: | --: | --: | --: | --: |
|    Before coin 1 |  1 |   0 |   0 |   0 |   0 |   0 |
| **After coin 1** |  1 | ___ | ___ | ___ | ___ | ___ |

**Question:** After processing coin `1`, how many ways are there to make 4?

Answer: ______

---

#### Step 3 — Process coin `2`

Now process coin `2`.

For each amount from 2 to 5:

`dp[amount] += dp[amount - 2]`

|        Amount |  0 |  1 |   2 |   3 |   4 |   5 |
| ------------: | -: | -: | --: | --: | --: | --: |
| Before coin 2 |  1 |  1 |   1 |   1 |   1 |   1 |
|      Amount 2 |  1 |  1 | ___ |   1 |   1 |   1 |
|      Amount 3 |  1 |  1 |   2 | ___ |   1 |   1 |
|      Amount 4 |  1 |  1 |   2 |   2 | ___ |   1 |
|      Amount 5 |  1 |  1 |   2 |   2 |   3 | ___ |

**Final table after coin 2:**

|           Amount |  0 |  1 |   2 |   3 |   4 |   5 |
| ---------------: | -: | -: | --: | --: | --: | --: |
| **After coin 2** |  1 |  1 | ___ | ___ | ___ | ___ |

---

#### Step 4 — Process coin `5`

Now process coin `5`.

Only amount 5 can use this coin:

`dp[5] += dp[0]`

|           Amount |  0 |  1 |  2 |  3 |  4 |   5 |
| ---------------: | -: | -: | -: | -: | -: | --: |
|    Before coin 5 |  1 |  1 |  2 |  2 |  3 |   3 |
| **After coin 5** |  1 |  1 |  2 |  2 |  3 | ___ |

### Final Question

How many different combinations make **5** using coins `{1, 2, 5}`?

**Answer: ______**

---

## Instructor Answer Key

### Step 1 — Initial table

|      Amount |     0 |     1 |     2 |     3 |     4 |     5 |
| ----------: | ----: | ----: | ----: | ----: | ----: | ----: |
| **Initial** | **1** | **0** | **0** | **0** | **0** | **0** |

The `1` at `dp[0]` means:

> There is exactly **one way** to make zero: choose no coins.

---

### Step 2 — After coin `1`

|           Amount |     0 |     1 |     2 |     3 |     4 |     5 |
| ---------------: | ----: | ----: | ----: | ----: | ----: | ----: |
|           Before |     1 |     0 |     0 |     0 |     0 |     0 |
| **After coin 1** | **1** | **1** | **1** | **1** | **1** | **1** |

There is only one way to make every amount:

* 1 → `1`
* 2 → `1+1`
* 3 → `1+1+1`
* 4 → `1+1+1+1`
* 5 → `1+1+1+1+1`

---

### Step 3 — After coin `2`

|           Amount |     0 |     1 |     2 |     3 |     4 |     5 |
| ---------------: | ----: | ----: | ----: | ----: | ----: | ----: |
|    Before coin 2 |     1 |     1 |     1 |     1 |     1 |     1 |
| **After coin 2** | **1** | **1** | **2** | **2** | **3** | **3** |

For example:

**Amount 4:**

```text
dp[4] += dp[4 - 2]
dp[4] += dp[2]
dp[4] = 1 + 2
dp[4] = 3
```

The three combinations are:

```text
1 + 1 + 1 + 1
1 + 1 + 2
2 + 2
```

For amount 5:

```text
1 + 1 + 1 + 1 + 1
1 + 1 + 1 + 2
1 + 2 + 2
```

So `dp[5] = 3`.

---

### Step 4 — After coin `5`

|           Amount |     0 |     1 |     2 |     3 |     4 |     5 |
| ---------------: | ----: | ----: | ----: | ----: | ----: | ----: |
|    Before coin 5 |     1 |     1 |     2 |     2 |     3 |     3 |
| **After coin 5** | **1** | **1** | **2** | **2** | **3** | **4** |

For amount 5:

```text
dp[5] += dp[5 - 5]
dp[5] += dp[0]
dp[5] = 3 + 1
dp[5] = 4
```

The four combinations are:

```text
5
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1
```

### Final DP Table

| Coins processed |     0 |     1 |     2 |     3 |     4 |     5 |
| --------------- | ----: | ----: | ----: | ----: | ----: | ----: |
| None            |     1 |     0 |     0 |     0 |     0 |     0 |
| `{1}`           |     1 |     1 |     1 |     1 |     1 |     1 |
| `{1,2}`         |     1 |     1 |     2 |     2 |     3 |     3 |
| `{1,2,5}`       | **1** | **1** | **2** | **2** | **3** | **4** |

**Key observation for students:**

> The most important question is not “What number goes in this cell?” but **“What does this cell mean?”**

`dp[5] = 4` means there are **4 combinations** for making 5 using the coins processed so far.

This trace also makes the **coin-first, amount-second loop order** much easier to understand.
