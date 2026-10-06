**why DP states sometimes need to represent “impossible” rather than simply having a default value of 0**

I would explain it to students in four layers:

1. What the problem is asking
2. What `dp[c]` means
3. Why `-INF` is necessary
4. A complete trace of the given example

# Problem 12 — Exact Backpack: Detailed Explanation and Trace

## 1. First, understand what makes this problem different

We have a backpack with capacity `10`.

There are four items:

| Item | Weight | Value |
| ---: | -----: | ----: |
|    1 |      6 |    10 |
|    2 |      4 |     7 |
|    3 |      3 |     5 |
|    4 |      2 |     3 |

Unlike the ordinary 0/1 knapsack problem, we **cannot leave empty space**.

We must have:

$$
\text{total weight} = 10
$$

not merely:

$$
\text{total weight} \leq 10
$$

For example:

* Items 1 + 2 have weight `6 + 4 = 10` → **valid**
* Items 1 + 4 have weight `6 + 2 = 8` → **not valid**
* Items 2 + 3 + 4 have weight `4 + 3 + 2 = 9` → **not valid**

Among all combinations whose weight is **exactly 10**, we want the maximum value.

---

# 2. What does `dp[c]` mean?

This is the most important question.

We define:

> **`dp[c]` = the maximum value we can obtain using the items processed so far, with total weight exactly `c`.**

Notice the word **exactly**.

For example:

```text
dp[6] = 10
```

means:

> We can select some of the items processed so far whose total weight is exactly 6, and the best value we can get is 10.

It does **not** mean:

> We have up to 6 pounds of space.

That distinction is crucial.

---

# 3. Why can't we initialize everything to 0?

This is where this problem differs from ordinary knapsack.

A beginner might write:

```java
long[] dp = new long[capacity + 1];
```

which produces:

```text
dp = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
```

But that would be wrong.

Suppose we are processing the first item:

```text
weight = 6
value = 10
```

We want to create:

```text
dp[6] = 10
```

But what about:

```text
dp[5]
dp[7]
dp[8]
...
```

Are those capacities actually reachable?

At this point, with only an item of weight 6:

```text
weight 0 → possible
weight 6 → possible
weight 1 → impossible
weight 2 → impossible
weight 3 → impossible
weight 4 → impossible
weight 5 → impossible
...
```

If we use `0` for both **“impossible”** and **“possible with value 0,”** we lose important information.

So we use a special value:

```text
-INF
```

meaning:

> This exact weight is currently impossible.

---

# 4. Initial DP table

We start with:

```java
Arrays.fill(dp, NEG_INF);
dp[0] = 0;
```

Therefore:

| Exact weight |     0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |
| -----------: | ----: | -: | -: | -: | -: | -: | -: | -: | -: | -: | -: |
|         `dp` | **0** | -∞ | -∞ | -∞ | -∞ | -∞ | -∞ | -∞ | -∞ | -∞ | -∞ |

Why is `dp[0] = 0`?

Because there is one valid way to achieve weight 0:

> Choose nothing.

Its value is 0.

Everything else is initially impossible.

---

# 5. Process Item 1

Item 1:

```text
weight = 6
value = 10
```

The code is:

```java
for (int c = capacity; c >= w[i]; c--) {
    if (dp[c - w[i]] != NEG_INF) {
        dp[c] = Math.max(
            dp[c],
            dp[c - w[i]] + v[i]
        );
    }
}
```

We work **backward** from 10 to 6.

Why backward?

Because each item can be used **at most once**.

We'll come back to this.

---

## c = 10

We ask:

> Can we make weight 10 by adding this item of weight 6?

We need:

```text
dp[10 - 6]
= dp[4]
```

But:

```text
dp[4] = -∞
```

So weight 10 cannot be formed this way.

Nothing changes.

---

## c = 9

We need:

```text
dp[9 - 6]
= dp[3]
```

But `dp[3]` is impossible.

No change.

---

## c = 8

We need:

```text
dp[2]
```

Impossible.

No change.

---

## c = 7

We need:

```text
dp[1]
```

Impossible.

No change.

---

## c = 6

We need:

```text
dp[0]
```

And:

```text
dp[0] = 0
```

So we can form weight 6:

$$
dp[6] = dp[0] + 10
$$

$$
dp[6] = 10
$$

The table becomes:

| Exact weight |     0 |  1 |  2 |  3 |  4 |  5 |      6 |  7 |  8 |  9 | 10 |
| -----------: | ----: | -: | -: | -: | -: | -: | -----: | -: | -: | -: | -: |
| After item 1 | **0** | -∞ | -∞ | -∞ | -∞ | -∞ | **10** | -∞ | -∞ | -∞ | -∞ |

Only weight 0 and weight 6 are possible.

---

# 6. Process Item 2

Item 2:

```text
weight = 4
value = 7
```

Before processing it:

|  Exact weight |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |
| ------------: | -: | -: | -: | -: | -: | -: | -: | -: | -: | -: | -: |
| Before item 2 |  0 | -∞ | -∞ | -∞ | -∞ | -∞ | 10 | -∞ | -∞ | -∞ | -∞ |

Now work backward.

---

## c = 10

We need:

```text
dp[10 - 4]
= dp[6]
```

`dp[6] = 10`.

Therefore:

```text
dp[10] = dp[6] + 7
       = 10 + 7
       = 17
```

This represents:

```text
Item 1 + Item 2
```

with:

```text
weight = 6 + 4 = 10
value  = 10 + 7 = 17
```

Excellent.

---

## c = 9

We need:

```text
dp[5]
```

But `dp[5] = -∞`.

So weight 9 is still impossible.

---

## c = 8

We need:

```text
dp[4]
```

At this point `dp[4]` is still impossible.

So weight 8 remains impossible.

---

## c = 7

We need:

```text
dp[3]
```

Impossible.

---

## c = 6

We need:

```text
dp[2]
```

Impossible.

---

## c = 5

We need:

```text
dp[1]
```

Impossible.

---

## c = 4

We need:

```text
dp[0]
```

which is 0.

Therefore:

```text
dp[4] = 0 + 7 = 7
```

This represents selecting Item 2 by itself.

The table is now:

| Exact weight |  0 |  1 |  2 |  3 |     4 |  5 |      6 |  7 |  8 |  9 |     10 |
| -----------: | -: | -: | -: | -: | ----: | -: | -----: | -: | -: | -: | -----: |
| After item 2 |  0 | -∞ | -∞ | -∞ | **7** | -∞ | **10** | -∞ | -∞ | -∞ | **17** |

Notice something important:

```text
dp[10] = 17
```

already gives us a solution to the problem.

But we still process the remaining items because perhaps another combination can achieve weight 10 with an even higher value.

---

# 7. Process Item 3

Item 3:

```text
weight = 3
value = 5
```

Before:

|        Weight |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |
| ------------: | -: | -: | -: | -: | -: | -: | -: | -: | -: | -: | -: |
| Before item 3 |  0 | -∞ | -∞ | -∞ |  7 | -∞ | 10 | -∞ | -∞ | -∞ | 17 |

Now consider the reachable states.

### c = 10

We need:

```text
dp[7]
```

But `dp[7]` is impossible.

So no new solution.

### c = 9

We need:

```text
dp[6] = 10
```

Therefore:

```text
dp[9] = 10 + 5 = 15
```

This represents:

```text
Item 1 + Item 3
```

Weight:

```text
6 + 3 = 9
```

Value:

```text
10 + 5 = 15
```

### c = 7

We need:

```text
dp[4] = 7
```

Therefore:

```text
dp[7] = 7 + 5 = 12
```

This represents:

```text
Item 2 + Item 3
```

### c = 6

We need:

```text
dp[3]
```

which was impossible.

### c = 4

We need:

```text
dp[1]
```

impossible.

### c = 3

We need:

```text
dp[0] = 0
```

Therefore:

```text
dp[3] = 5
```

The table becomes:

| Exact weight |  0 |  1 |  2 |     3 |     4 |  5 |      6 |      7 |  8 |      9 |     10 |
| -----------: | -: | -: | -: | ----: | ----: | -: | -----: | -----: | -: | -----: | -----: |
| After item 3 |  0 | -∞ | -∞ | **5** | **7** | -∞ | **10** | **12** | -∞ | **15** | **17** |

---

# 8. Process Item 4

Item 4:

```text
weight = 2
value = 3
```

Now things get more interesting.

Before Item 4:

|        Weight |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |
| ------------: | -: | -: | -: | -: | -: | -: | -: | -: | -: | -: | -: |
| Before item 4 |  0 | -∞ | -∞ |  5 |  7 | -∞ | 10 | 12 | -∞ | 15 | 17 |

We process backward.

### c = 10

We need:

```text
dp[8]
```

But:

```text
dp[8] = -∞
```

So Item 4 cannot create a weight-10 solution from weight 8.

The existing:

```text
dp[10] = 17
```

remains unchanged.

---

### c = 9

We need:

```text
dp[7] = 12
```

Therefore:

```text
dp[9] = 12 + 3 = 15
```

No improvement over the existing 15.

---

### c = 8

We need:

```text
dp[6] = 10
```

Therefore:

```text
dp[8] = 10 + 3 = 13
```

So weight 8 becomes reachable.

This corresponds to:

```text
Item 1 + Item 4
```

Weight:

```text
6 + 2 = 8
```

Value:

```text
10 + 3 = 13
```

---

### c = 6

We need:

```text
dp[4] = 7
```

Therefore:

```text
dp[6] = 7 + 3 = 10
```

No improvement; `dp[6]` was already 10.

---

### c = 5

We need:

```text
dp[3] = 5
```

Therefore:

```text
dp[5] = 5 + 3 = 8
```

---

### c = 4

We need:

```text
dp[2]
```

which is impossible.

---

### c = 3

We need:

```text
dp[1]
```

impossible.

---

### c = 2

We need:

```text
dp[0] = 0
```

Therefore:

```text
dp[2] = 3
```

The final table is:

|         Weight |     0 |  1 |     2 |     3 |     4 |     5 |      6 |      7 |      8 |      9 |     10 |
| -------------: | ----: | -: | ----: | ----: | ----: | ----: | -----: | -----: | -----: | -----: | -----: |
| **Final `dp`** | **0** | -∞ | **3** | **5** | **7** | **8** | **10** | **12** | **13** | **15** | **17** |

Therefore:

```text
dp[10] = 17
```

and the answer is:

```text
17
```

---

# 9. Where did the answer 17 come from?

The optimal combination is simply:

```text
Item 1 + Item 2
```

|           | Weight |  Value |
| --------- | -----: | -----: |
| Item 1    |      6 |     10 |
| Item 2    |      4 |      7 |
| **Total** | **10** | **17** |

So:

$$
\boxed{17}
$$

---

# 10. Why is `-INF` so important?

Consider this situation after processing Item 1.

We have:

```text
dp[6] = 10
dp[5] = -INF
```

Suppose we are considering an item with weight 4.

To create weight 9:

```text
dp[9] = dp[5] + value
```

But weight 5 is impossible.

We **must not** treat it as a valid state.

This is why the code says:

```java
if (dp[c - w[i]] != NEG_INF) {
    dp[c] = Math.max(
        dp[c],
        dp[c - w[i]] + v[i]
    );
}
```

The condition means:

> Only build a new solution from a state that is actually reachable.

---

# 11. Why do we loop backward?

This is another important teaching point.

The loop is:

```java
for (int c = capacity; c >= w[i]; c--)
```

rather than:

```java
for (int c = w[i]; c <= capacity; c++)
```

because this is a **0/1 knapsack** problem.

Each item can be used **at most once**.

Suppose we have:

```text
Item:
weight = 2
value = 3
```

and we process `c` from **small to large**.

We might do:

```text
dp[2] = dp[0] + 3 = 3
```

Then later:

```text
dp[4] = dp[2] + 3 = 6
```

But the `dp[2]` we just created already used the current item!

Therefore we have accidentally used the same item twice.

Backward iteration prevents this.

When we calculate `dp[4]`, the `dp[2]` we're reading is from **before the current item was processed**.

So:

> **0/1 knapsack → process capacity from large to small.**

Whereas:

> **Unlimited/complete knapsack → usually process capacity from small to large.**

That's a very useful pattern for students to remember.

---

# 12. A simpler way to visualize the transition

Every time we consider an item with weight `w` and value `v`, we ask:

> **If I want to end at weight `c`, could I get there by adding this item?**

If yes, we look at:

$$
c-w
$$

So the transition is:

$$
dp[c] =
\max(dp[c], dp[c-w]+v)
$$

But there is an important prerequisite:

$$
dp[c-w] \neq -\infty
$$

because we cannot build a solution on top of an impossible state.

---

# 13. The complete trace at a glance

This is probably the most useful table to show students:

| Items processed | `dp[0]` | `dp[1]` | `dp[2]` | `dp[3]` | `dp[4]` | `dp[5]` | `dp[6]` | `dp[7]` | `dp[8]` | `dp[9]` | `dp[10]` |
| --------------- | ------: | ------: | ------: | ------: | ------: | ------: | ------: | ------: | ------: | ------: | -------: |
| None            |       0 |      -∞ |      -∞ |      -∞ |      -∞ |      -∞ |      -∞ |      -∞ |      -∞ |      -∞ |       -∞ |
| Item 1 `(6,10)` |       0 |      -∞ |      -∞ |      -∞ |      -∞ |      -∞ |      10 |      -∞ |      -∞ |      -∞ |       -∞ |
| Items 1–2       |       0 |      -∞ |      -∞ |      -∞ |       7 |      -∞ |      10 |      -∞ |      -∞ |      -∞ |   **17** |
| Items 1–3       |       0 |      -∞ |      -∞ |       5 |       7 |      -∞ |      10 |      12 |      -∞ |      15 |   **17** |
| Items 1–4       |       0 |      -∞ |       3 |       5 |       7 |       8 |      10 |      12 |      13 |      15 |   **17** |

This table reveals an important idea:

> **A DP array is not just a collection of answers. Each cell represents a state, and some states may not yet be reachable.**

---

## 14. A good student-thinking sequence

When I teach this problem, I would have students ask these questions in order:

### Question 1

What does `dp[c]` represent?

**Answer:**

> The maximum value for an exact total weight of `c`.

### Question 2

What should `dp[0]` be?

**Answer:**

```text
0
```

### Question 3

What should an unreachable state contain?

**Answer:**

```text
-INF
```

### Question 4

If I add item `(w, v)` to a solution of weight `c-w`, what happens?

**Answer:**

```text
new value = dp[c-w] + v
```

### Question 5

What is the transition?

**Answer:**

```text
dp[c] = max(dp[c], dp[c-w] + v)
```

### Question 6

Can I use the same item twice?

**Answer:**

> No.

### Question 7

Therefore, which direction should the capacity loop go?

**Answer:**

> From large to small.

---

### One important contrast for students

It is helpful to put these two problems side-by-side:

| Ordinary 0/1 Knapsack                                       | Exact 0/1 Knapsack                                 |
| ----------------------------------------------------------- | -------------------------------------------------- |
| Weight can be **≤ C**                                       | Weight must be **= C**                             |
| `dp[c]` often means best value using **up to** capacity `c` | `dp[c]` means best value at **exactly** weight `c` |
| Unreachable states can often safely start at `0`            | Must distinguish unreachable states                |
| Answer often `dp[C]`                                        | Answer `dp[C]`, but it may be impossible           |
| Usually backward loop                                       | Backward loop                                      |

The conceptual leap I would emphasize is:

> **In ordinary knapsack, an unused portion of the backpack is okay. In exact knapsack, an unused portion means the state is not a solution.**

That is the main reason `-INF` is needed.
