**Longest Increasing Subsequence (LIS)** is an excellent DP problem because it teaches students how to define a state based on the **last element of a partial solution**.

# Problem 14 — Longest Increasing Subsequence

Suppose we have:

```text
8 1 2 3 5 4 7
```

We want to find the length of the longest **strictly increasing subsequence**.

A subsequence:

* keeps the original order,
* does **not** have to use consecutive elements,
* can skip elements.

For example:

```text
1 2 3 5 7
```

is a subsequence.

Its length is 5, so the answer is:

```text
5
```

---

# 1. Subsequence vs. Subarray

This distinction is important.

Given:

```text
8 1 2 3 5 4 7
```

### Subarray

A subarray must be consecutive.

For example:

```text
1 2 3 5
```

is a subarray.

But:

```text
1 2 3 5 7
```

is **not** a subarray because 7 is not immediately after 5.

### Subsequence

A subsequence can skip elements.

```text
1 2 3 5 7
```

is a valid subsequence.

The original array is:

```text
8  1  2  3  5  4  7
   ↑  ↑  ↑  ↑     ↑
```

We skipped 8 and 4.

---

# 2. What does "increasing" mean?

We require:

$$
a_1 < a_2 < a_3 < \cdots
$$

So this is increasing:

```text
1 2 3 5 7
```

But this is **not** strictly increasing:

```text
1 2 2 5
```

because:

```text
2 < 2
```

is false.

For this problem, we use:

```java
a[j] < a[i]
```

not:

```java
a[j] <= a[i]
```

---

# 3. The key DP question

The most important step is deciding what `dp[i]` means.

A common mistake is to say:

> `dp[i]` = length of the longest increasing subsequence in the first `i` elements.

That's a possible DP formulation, but it doesn't lead as naturally to the simple \(O(n^2)\) solution.

Instead, define:

> **`dp[i]` = length of the longest increasing subsequence that ends at index `i`.**

The word **ends** is extremely important.

---

# 4. Example

Consider:

```text
index:  0  1  2  3  4  5  6
value:  8  1  2  3  5  4  7
```

Let's look at index 4:

```text
value = 5
```

What is the longest increasing subsequence that **ends at 5**?

We can have:

```text
1 2 3 5
```

So:

```text
dp[4] = 4
```

Notice that we're not asking:

> "What is the LIS of the entire array?"

We're asking:

> "What is the longest increasing subsequence whose final element is `5`?"

That makes the recurrence possible.

---

# 5. The base case

Every individual number is itself an increasing subsequence.

For example:

```text
8
```

is an increasing subsequence of length 1.

Likewise:

```text
1
2
3
5
4
7
```

are all increasing subsequences of length 1.

Therefore:

```java
dp[i] = 1;
```

for every `i`.

Initially:

|   Index |  0 |  1 |  2 |  3 |  4 |  5 |  6 |
| ------: | -: | -: | -: | -: | -: | -: | -: |
|   Value |  8 |  1 |  2 |  3 |  5 |  4 |  7 |
| `dp[i]` |  1 |  1 |  1 |  1 |  1 |  1 |  1 |

---

# 6. How can we extend a subsequence?

Suppose we're calculating:

```text
dp[i]
```

and the current value is:

```text
a[i]
```

We look at all previous positions:

```text
j < i
```

If:

```text
a[j] < a[i]
```

then we can put `a[i]` after the subsequence ending at `j`.

Therefore:

$$
dp[i] = \max(dp[i], dp[j]+1)
$$

whenever:

$$
j < i
$$

and:

$$
a[j] < a[i]
$$

That's the entire recurrence.

---

# 7. Trace the example

Let's trace:

```text
8 1 2 3 5 4 7
```

with:

```text
dp[i] = longest increasing subsequence ending at i
```

---

## Index 0 — value 8

There are no elements before it.

So the only subsequence ending at 8 is:

```text
8
```

Therefore:

```text
dp[0] = 1
```

| Index |     0 |  1 |  2 |  3 |  4 |  5 |  6 |
| ----: | ----: | -: | -: | -: | -: | -: | -: |
| Value | **8** |  1 |  2 |  3 |  5 |  4 |  7 |
|  `dp` | **1** |  1 |  1 |  1 |  1 |  1 |  1 |

---

# 8. Index 1 — value 1

Previous value:

```text
8
```

Can we put 1 after 8?

We would need:

```text
8 < 1
```

which is false.

Therefore:

```text
dp[1] = 1
```

The subsequence is simply:

```text
1
```

| Index |  0 |     1 |  2 |  3 |  4 |  5 |  6 |
| ----: | -: | ----: | -: | -: | -: | -: | -: |
| Value |  8 | **1** |  2 |  3 |  5 |  4 |  7 |
|  `dp` |  1 | **1** |  1 |  1 |  1 |  1 |  1 |

---

# 9. Index 2 — value 2

Previous elements:

```text
8 1
```

We check each one.

### Check 8

```text
8 < 2
```

False.

### Check 1

```text
1 < 2
```

True!

Therefore, we can extend the subsequence ending at 1:

```text
1
```

to:

```text
1 2
```

So:

$$
dp[2] = dp[1] + 1
$$

$$
dp[2] = 1 + 1 = 2
$$

| Index |  0 |  1 |     2 |  3 |  4 |  5 |  6 |
| ----: | -: | -: | ----: | -: | -: | -: | -: |
| Value |  8 |  1 | **2** |  3 |  5 |  4 |  7 |
|  `dp` |  1 |  1 | **2** |  1 |  1 |  1 |  1 |

---

# 10. Index 3 — value 3

Previous elements:

```text
8 1 2
```

Check them.

### 8

```text
8 < 3
```

No.

### 1

```text
1 < 3
```

Yes.

We can form:

```text
1 3
```

length 2.

### 2

```text
2 < 3
```

Yes.

And the longest subsequence ending at 2 has length 2:

```text
1 2
```

So we can extend it:

```text
1 2 3
```

giving length 3.

Therefore:

```text
dp[3] = dp[2] + 1
      = 2 + 1
      = 3
```

| Index |  0 |  1 |  2 |     3 |  4 |  5 |  6 |
| ----: | -: | -: | -: | ----: | -: | -: | -: |
| Value |  8 |  1 |  2 | **3** |  5 |  4 |  7 |
|  `dp` |  1 |  1 |  2 | **3** |  1 |  1 |  1 |

---

# 11. Index 4 — value 5

Previous values:

```text
8 1 2 3
```

We can extend any subsequence ending in a value less than 5.

The best previous state is:

```text
dp[3] = 3
```

corresponding to:

```text
1 2 3
```

Add 5:

```text
1 2 3 5
```

Therefore:

```text
dp[4] = 4
```

| Index |  0 |  1 |  2 |  3 |     4 |  5 |  6 |
| ----: | -: | -: | -: | -: | ----: | -: | -: |
| Value |  8 |  1 |  2 |  3 | **5** |  4 |  7 |
|  `dp` |  1 |  1 |  2 |  3 | **4** |  1 |  1 |

---

# 12. Index 5 — value 4

Now things get interesting.

Previous values:

```text
8 1 2 3 5
```

We can only extend a subsequence whose last value is less than 4.

So:

```text
8 < 4  ❌
1 < 4  ✓
2 < 4  ✓
3 < 4  ✓
5 < 4  ❌
```

The best previous state is:

```text
dp[3] = 3
```

corresponding to:

```text
1 2 3
```

Add 4:

```text
1 2 3 4
```

Therefore:

```text
dp[5] = 4
```

Notice something interesting:

```text
dp[4] = 4
dp[5] = 4
```

Both are length 4, but they represent different subsequences:

```text
1 2 3 5
```

and:

```text
1 2 3 4
```

| Index |  0 |  1 |  2 |  3 |  4 |     5 |  6 |
| ----: | -: | -: | -: | -: | -: | ----: | -: |
| Value |  8 |  1 |  2 |  3 |  5 | **4** |  7 |
|  `dp` |  1 |  1 |  2 |  3 |  4 | **4** |  1 |

---

# 13. Index 6 — value 7

Finally:

```text
a[6] = 7
```

Previous values:

```text
8 1 2 3 5 4
```

We can extend any subsequence ending with a value less than 7.

The best possibilities include:

```text
1 2 3 5
```

with length 4, and:

```text
1 2 3 4
```

with length 4.

So:

```text
dp[6] = 4 + 1
      = 5
```

One possible LIS is:

```text
1 2 3 5 7
```

Another is:

```text
1 2 3 4 7
```

Therefore:

```text
dp[6] = 5
```

Final table:

|       Index |     0 |     1 |     2 |     3 |     4 |     5 |     6 |
| ----------: | ----: | ----: | ----: | ----: | ----: | ----: | ----: |
|       Value |     8 |     1 |     2 |     3 |     5 |     4 |     7 |
| **`dp[i]`** | **1** | **1** | **2** | **3** | **4** | **4** | **5** |

---

# 14. But which `dp[i]` is the answer?

This is another important point.

Students might look at:

```text
dp[6]
```

and say:

> That's the answer.

That happens to be true for this example, but it is **not generally true**.

Remember:

```text
dp[i] = LIS ending at i
```

The overall LIS could end anywhere.

For example:

```text
10 20 30 5 6 7
```

The longest increasing subsequence is:

```text
10 20 30
```

with length 3.

But the final element is 7, and:

```text
dp[last]
```

would be 3 as well in this case.

Consider:

```text
1 2 3 10 4
```

The LIS is:

```text
1 2 3 10
```

with length 4.

But the subsequence ending at 4 is:

```text
1 2 3 4
```

also length 4.

Let's make it clearer with:

```text
1 2 3 100 4
```

The LIS is still length 4, but `dp[4]` is:

```text
1 2 3 4
```

also 4.

More generally, we should not assume the best subsequence ends at the final element.

Therefore:

$$
\text{answer} = \max(dp[0],dp[1],...,dp[n-1])
$$

For our example:

```text
dp = 1 1 2 3 4 4 5
```

so:

```text
max = 5
```

---

# 15. The Java solution

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

        // Every element by itself is an
        // increasing subsequence of length 1.
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {

                    dp[i] = Math.max(
                        dp[i],
                        dp[j] + 1
                    );
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
```

---

# 16. Let's connect the code to the idea

The most important part is:

```java
for (int i = 0; i < n; i++) {

    for (int j = 0; j < i; j++) {

        if (a[j] < a[i]) {

            dp[i] = Math.max(
                dp[i],
                dp[j] + 1
            );
        }
    }
}
```

Think of `i` as:

> **Where I want my subsequence to end.**

And `j` as:

> **Where the previous element of my subsequence might be.**

So we ask:

```text
Can I put a[i] after a[j]?
```

That is exactly:

```java
a[j] < a[i]
```

If yes:

```text
best sequence ending at j
                +
              a[i]
```

becomes:

```java
dp[j] + 1
```

So:

```java
dp[i] = Math.max(dp[i], dp[j] + 1);
```

---

# 17. Why do we need the `max`?

Suppose we're calculating `dp[6]`, where:

```text
a[6] = 7
```

Several previous values can precede 7.

We might have:

```text
j = 1 → dp[1] = 1
j = 2 → dp[2] = 2
j = 3 → dp[3] = 3
j = 4 → dp[4] = 4
j = 5 → dp[5] = 4
```

We want the **best** one.

So:

```text
dp[6] = max(
    1 + 1,
    2 + 1,
    3 + 1,
    4 + 1,
    4 + 1
)
```

Therefore:

```text
dp[6] = 5
```

---

# 18. Visualizing the DP dependencies

For:

```text
8 1 2 3 5 4 7
```

we can think of the relationships like this:

```text
1 → 2 → 3 → 5 → 7
      ↘ 3 → 4 → 7
```

More completely, 7 can follow:

```text
1
2
3
5
4
```

because all of those values are less than 7.

The DP asks:

> Which previous element gives me the longest increasing subsequence?

That is why we examine all `j < i`.

---

# 19. Complexity

There are two loops:

```java
for (int i = 0; i < n; i++)
```

and:

```java
for (int j = 0; j < i; j++)
```

For each `i`, we potentially examine all previous elements.

Therefore:

$$
O(n^2)
$$

time.

The `dp` array has `n` elements:

$$
O(n)
$$

space.

For an introductory DP/ICPC course, this is a very good solution because the recurrence is easy to understand.

There is also an \(O(n\log n)\) LIS algorithm, but I would teach the \(O(n^2)\) DP version first.

---

# 20. A useful student trace table

For students, I would have them fill out this table:

Given:

```text
8 1 2 3 5 4 7
```

| `i` | `a[i]` | Previous smaller values | Best previous `dp[j]` | `dp[i]` |
| --: | -----: | ----------------------- | --------------------: | ------: |
|   0 |      8 | none                    |                     — |       1 |
|   1 |      1 | none                    |                     — |       1 |
|   2 |      2 | 1                       |                     1 |       2 |
|   3 |      3 | 1, 2                    |                     2 |       3 |
|   4 |      5 | 1, 2, 3                 |                     3 |       4 |
|   5 |      4 | 1, 2, 3                 |                     3 |       4 |
|   6 |      7 | 1, 2, 3, 5, 4           |                     4 |   **5** |

This is a nice way to help students see that the recurrence is really just:

> **Find a smaller number before me with the best DP value, then add 1.**

---

## 21. The three questions I would emphasize

For students learning DP, I would have them memorize this **thinking process**, rather than the code:

### 1. What does `dp[i]` represent?

> The longest increasing subsequence **ending at `i`**.

### 2. How can I extend an earlier solution?

Find `j < i` such that:

```text
a[j] < a[i]
```

Then:

```text
dp[j] + 1
```

### 3. What is the final answer?

The subsequence can end anywhere:

```text
max(dp[i])
```

So the complete recurrence is:

$$
\boxed{
dp[i] =
1 + \max_{\substack{j<i\\a[j]<a[i]}} dp[j]
}
$$

with:

$$
\boxed{dp[i]=1}
$$

when no earlier element can precede `a[i]`.

**The big DP insight:** instead of trying to find the entire LIS directly, we solve many smaller problems: **“What is the best increasing subsequence that ends right here?”** Then we take the best of those smaller answers.
