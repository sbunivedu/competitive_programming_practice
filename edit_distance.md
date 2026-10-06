**Edit Distance** is another classic two-dimensional DP problem, and it follows naturally after LCS. The main new idea is that when two characters don't match, we have **three possible operations**: insert, delete, or replace.

# Problem 16 — Edit Distance

Given two strings, find the minimum number of operations needed to transform the first string into the second.

The allowed operations are:

1. **Insert** one character
2. **Delete** one character
3. **Replace** one character

For example:

```text
cat → cut
```

We can replace `a` with `u`:

```text
cat
 ↓
cut
```

So the answer is:

```text
1
```

This problem is also commonly called **Levenshtein distance**.

---

# 1. The three operations

Let's make sure students understand the operations before introducing DP.

Suppose we have:

```text
cat
```

and want:

```text
cart
```

We can **insert** `r`:

```text
cat
 ↓
cart
```

Cost = 1.

---

Suppose:

```text
cart
```

and we want:

```text
cat
```

We can **delete** `r`:

```text
cart
 ↓
cat
```

Cost = 1.

---

Suppose:

```text
cat
```

and we want:

```text
cut
```

We can **replace** `a` with `u`:

```text
cat
 ↓
cut
```

Cost = 1.

---

# 2. The key DP question

Just like LCS, we compare **prefixes** of the two strings.

Define:

> **`dp[i][j]` = the minimum number of operations required to transform the first `i` characters of String A into the first `j` characters of String B.**

This is the most important thing for students to understand.

For example:

```text
A = kitten
B = sitting
```

Then:

```text
dp[3][4]
```

means:

> What is the minimum number of operations needed to transform `"kit"` into `"sitt"`?

---

# 3. Why do we need a two-dimensional table?

Because there are **two strings**.

We need to keep track of:

```text
i = how much of String A we've processed
j = how much of String B we've processed
```

So we use:

```java
int[][] dp = new int[n + 1][m + 1];
```

The `+1` gives us the empty-string row and column.

---

# 4. The empty-string cases

These are very important.

Suppose we want to transform:

```text
""
```

into:

```text
"abc"
```

We need to insert three characters:

```text
"" → "a" → "ab" → "abc"
```

So:

```text
dp[0][3] = 3
```

In general:

$$
dp[0][j] = j
$$

---

Now suppose we want:

```text
"abc"
```

to become:

```text
""
```

We have to delete all three characters:

```text
"abc" → "ab" → "a" → ""
```

So:

```text
dp[3][0] = 3
```

In general:

$$
dp[i][0] = i
$$

Therefore, our initial table looks like:

|      |  `""` |     A |     B |     C |
| ---- | ----: | ----: | ----: | ----: |
| `""` | **0** | **1** | **2** | **3** |
| A    | **1** |       |       |       |
| B    | **2** |       |       |       |
| C    | **3** |       |       |       |

This is our base case.

---

# 5. The key question: What happens with the last characters?

Suppose we're calculating:

```text
dp[i][j]
```

We compare:

```text
A[i-1]
```

and:

```text
B[j-1]
```

There are two major cases.

---

# 6. Case 1 — The characters are the same

Suppose:

```text
A = cat
B = cat
```

We're comparing the final:

```text
t
t
```

They already match.

We don't need to do anything to those characters.

Therefore:

$$
dp[i][j] = dp[i-1][j-1]
$$

In words:

> If the current characters already match, ignore them and solve the smaller problem.

For example:

```text
cat
cat
```

The final `t`s match, so:

```text
dp[3][3] = dp[2][2]
```

---

# 7. Case 2 — The characters are different

Now suppose:

```text
A = cat
B = cut
```

The final characters:

```text
t
t
```

match, so again we simply use:

```text
dp[2][2]
```

But consider:

```text
A = cat
B = car
```

The final characters are:

```text
t
r
```

They don't match.

What can we do?

There are **three choices**.

---

# 8. Option 1 — Replace

We can replace:

```text
t → r
```

So:

```text
cat
 ↓
car
```

The cost is 1.

Before making the replacement, we need to transform:

```text
ca
```

into:

```text
ca
```

That's:

```text
dp[i-1][j-1]
```

Therefore:

$$
\text{replace} = dp[i-1][j-1]+1
$$

---

# 9. Option 2 — Delete

We can delete the last character of String A.

For:

```text
cat
```

we could delete `t`:

```text
cat → ca
```

Then we still need to transform:

```text
ca
```

into:

```text
car
```

That's:

```text
dp[i-1][j]
```

Therefore:

$$
\text{delete} = dp[i-1][j]+1
$$

---

# 10. Option 3 — Insert

Instead, we could insert the final character of String B.

For:

```text
cat → car
```

this isn't the most natural operation, but conceptually we could first transform:

```text
cat
```

into something corresponding to:

```text
ca
```

and then insert `r`.

The DP formulation is:

$$
\text{insert} = dp[i][j-1]+1
$$

---

# 11. The complete recurrence

If the characters match:

$$
\boxed{
dp[i][j]=dp[i-1][j-1]
}
$$

If they don't match:

$$
\boxed{
dp[i][j]=
1+\min
\begin{cases}
dp[i-1][j] & \text{delete}\\
dp[i][j-1] & \text{insert}\\
dp[i-1][j-1] & \text{replace}
\end{cases}
}
$$

This is the heart of the problem.

A good way for students to remember it:

> **Mismatch → three choices → take the minimum.**

---

# 12. Let's trace a simple example

Let's start with:

```text
A = cat
B = cut
```

We expect the answer to be 1 because:

```text
cat
 ↓ replace a with u
cut
```

Our table will be:

|      | `""` |  c |  u |  t |
| ---- | ---: | -: | -: | -: |
| `""` |    0 |  1 |  2 |  3 |
| c    |    1 |    |    |    |
| a    |    2 |    |    |    |
| t    |    3 |    |    |    |

---

# 13. Cell `[1][1]`

Compare:

```text
c
c
```

They match.

Therefore:

$$
dp[1][1]=dp[0][0]=0
$$

|       | `""` |     c |  u |  t |
| ----- | ---: | ----: | -: | -: |
| `""`  |    0 |     1 |  2 |  3 |
| **c** |    1 | **0** |    |    |
| a     |    2 |       |    |    |
| t     |    3 |       |    |    |

---

# 14. Cell `[1][2]`

Compare:

```text
c
u
```

They don't match.

We have three choices:

### Delete

$$
dp[0][2]+1=2+1=3
$$

### Insert

$$
dp[1][1]+1=0+1=1
$$

### Replace

$$
dp[0][1]+1=1+1=2
$$

Take the minimum:

$$
dp[1][2]=1
$$

Why is 1 correct?

We can transform:

```text
c → cu
```

by inserting `u`.

Table:

|       | `""` |  c |     u |  t |
| ----- | ---: | -: | ----: | -: |
| `""`  |    0 |  1 |     2 |  3 |
| **c** |    1 |  0 | **1** |    |
| a     |    2 |    |       |    |
| t     |    3 |    |       |    |

---

# 15. Cell `[1][3]`

Compare:

```text
c
t
```

They don't match.

The three possibilities produce:

```text
delete:   dp[0][3] + 1 = 4
insert:   dp[1][2] + 1 = 2
replace:  dp[0][2] + 1 = 3
```

Minimum = 2.

So:

```text
dp[1][3] = 2
```

---

# 16. Row 1

We now have:

|       | `""` |     c |     u |     t |
| ----- | ---: | ----: | ----: | ----: |
| `""`  |    0 |     1 |     2 |     3 |
| **c** |    1 | **0** | **1** | **2** |

---

# 17. Cell `[2][1]`

Compare:

```text
a
c
```

They don't match.

Three possibilities:

```text
delete:
dp[1][1] + 1 = 0 + 1 = 1

insert:
dp[2][0] + 1 = 2 + 1 = 3

replace:
dp[1][0] + 1 = 1 + 1 = 2
```

Minimum:

```text
dp[2][1] = 1
```

---

# 18. Cell `[2][2]`

Compare:

```text
a
u
```

They don't match.

Three possibilities:

```text
delete:
dp[1][2] + 1 = 1 + 1 = 2

insert:
dp[2][1] + 1 = 1 + 1 = 2

replace:
dp[1][1] + 1 = 0 + 1 = 1
```

So:

```text
dp[2][2] = 1
```

This corresponds to:

```text
cat
 ↓
cut
```

replacing `a` with `u`.

---

# 19. Cell `[2][3]`

Compare:

```text
a
t
```

They don't match.

Calculate:

```text
delete:
dp[1][3] + 1 = 2 + 1 = 3

insert:
dp[2][2] + 1 = 1 + 1 = 2

replace:
dp[1][2] + 1 = 1 + 1 = 2
```

Therefore:

```text
dp[2][3] = 2
```

---

# 20. Cell `[3][1]`

Compare:

```text
t
c
```

No match.

The minimum is:

```text
dp[3][1] = 2
```

---

# 21. Cell `[3][2]`

Compare:

```text
t
u
```

No match.

We get:

```text
delete:
dp[2][2] + 1 = 2

insert:
dp[3][1] + 1 = 3

replace:
dp[2][1] + 1 = 2
```

Therefore:

```text
dp[3][2] = 2
```

---

# 22. Cell `[3][3]`

Now compare:

```text
t
t
```

They match!

Therefore we don't need an operation:

$$
dp[3][3]=dp[2][2]
$$

$$
=1
$$

Final table:

|      |  `""` |     c |     u |     t |
| ---- | ----: | ----: | ----: | ----: |
| `""` | **0** | **1** | **2** | **3** |
| c    | **1** | **0** | **1** | **2** |
| a    | **2** | **1** | **1** | **2** |
| t    | **3** | **2** | **2** | **1** |

Therefore:

$$
\boxed{dp[3][3]=1}
$$

So the edit distance is:

```text
1
```

---

# 23. The most important insight in the table

Look at the final cell:

```text
dp[3][3] = 1
```

It means:

> The minimum number of operations required to transform `"cat"` into `"cut"` is 1.

The DP table is essentially answering hundreds of smaller questions:

```text
"" → ""
"" → "c"
"" → "cu"
"" → "cut"

"c" → ""
"c" → "c"
"c" → "cu"
"c" → "cut"

...
```

Each answer helps us solve a slightly larger problem.

That is exactly the **overlapping subproblems** idea behind dynamic programming.

---

# 24. Java solution

Here is the standard \(O(nm)\) solution:

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

        // Transforming a string into an empty string
        // requires deleting all of its characters.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        // Transforming an empty string into a string
        // requires inserting all of its characters.
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    int delete = dp[i - 1][j] + 1;
                    int insert = dp[i][j - 1] + 1;
                    int replace = dp[i - 1][j - 1] + 1;

                    dp[i][j] = Math.min(
                        delete,
                        Math.min(insert, replace)
                    );
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
```

---

# 25. Connecting the code to the recurrence

The most important section is:

```java
if (a.charAt(i - 1) == b.charAt(j - 1)) {

    dp[i][j] = dp[i - 1][j - 1];

}
```

Think:

> **They match → no operation needed.**

So we move diagonally:

```text
dp[i-1][j-1]
       ↘
        dp[i][j]
```

---

When they don't match:

```java
int delete = dp[i - 1][j] + 1;
int insert = dp[i][j - 1] + 1;
int replace = dp[i - 1][j - 1] + 1;
```

We calculate the cost of all three possibilities.

Then:

```java
dp[i][j] = Math.min(
    delete,
    Math.min(insert, replace)
);
```

means:

> Choose the cheapest operation.

---

# 26. A useful diagram for students

When characters **don't match**, show them this:

```text
                 delete
                   ↓
              dp[i-1][j]
                   |
                   + 1
                   |
                   ↓
                 dp[i][j]


dp[i][j-1]  + 1  →  dp[i][j]  ←  dp[i-1][j-1] + 1
    insert                         replace
```

Or, more compactly:

```text
                 dp[i-1][j]
                     |
                     | +1 delete
                     ↓
dp[i][j-1] +1 →   dp[i][j]   ← dp[i-1][j-1] +1
   insert                          replace
```

The student question should be:

> **Which of these three choices gives the smallest answer?**

---

# 27. A very useful memory trick

For students, I'd summarize the recurrence like this:

### Characters match

**Diagonal, no cost**

```text
dp[i][j] = dp[i-1][j-1]
```

### Characters don't match

**Three neighbors, +1**

```text
dp[i][j] =
    1 + min(
        dp[i-1][j],     // delete
        dp[i][j-1],     // insert
        dp[i-1][j-1]    // replace
    )
```

So:

> **Match → diagonal.**
> **Mismatch → min of three directions + 1.**

---

# 28. Another complete example: `kitten → sitting`

This is a famous test case.

```text
kitten
sitting
```

The minimum sequence of edits is:

```text
kitten
 ↓ replace k → s
sitten
 ↓ replace e → i
sittin
 ↓ insert g
sitting
```

So the answer is:

```text
3
```

The DP table ultimately gives:

|      | `""` |  s |  i |  t |  t |  i |  n |     g |
| ---- | ---: | -: | -: | -: | -: | -: | -: | ----: |
| `""` |    0 |  1 |  2 |  3 |  4 |  5 |  6 |     7 |
| k    |    1 |  1 |  2 |  3 |  4 |  5 |  6 |     7 |
| i    |    2 |  2 |  1 |  2 |  3 |  4 |  5 |     6 |
| t    |    3 |  3 |  2 |  1 |  2 |  3 |  4 |     5 |
| t    |    4 |  4 |  3 |  2 |  1 |  2 |  3 |     4 |
| e    |    5 |  5 |  4 |  3 |  2 |  2 |  3 |     4 |
| n    |    6 |  6 |  5 |  4 |  3 |  3 |  2 | **3** |

Therefore:

$$
\boxed{3}
$$

---

# 29. Why Edit Distance is related to LCS

This is a nice connection to the previous problem.

Both problems use:

```text
dp[i][j]
```

to compare prefixes of two strings.

But they ask different questions.

### LCS

> What is the **longest** sequence that appears in both?

Therefore, we generally want to **maximize**.

```text
max(...)
```

### Edit Distance

> What is the **fewest** operations needed to transform one into the other?

Therefore, we want to **minimize**.

```text
min(...)
```

|          | LCS                 | Edit Distance                      |
| -------- | ------------------- | ---------------------------------- |
| DP state | LCS of two prefixes | Minimum edits between two prefixes |
| Match    | Diagonal + 1        | Diagonal, no cost                  |
| Mismatch | Max of two choices  | Min of three choices + 1           |
| Goal     | Maximize            | Minimize                           |
| Time     | `O(nm)`             | `O(nm)`                            |
| Space    | `O(nm)`             | `O(nm)`                            |

This makes Edit Distance a very natural follow-up to LCS.

---

# 30. Student trace exercise

For an introductory ICPC class, I would give students this smaller problem to trace by hand:

```text
A = abc
B = ac
```

Start with:

|      | `""` |   a |   c |
| ---- | ---: | --: | --: |
| `""` |    0 |   1 |   2 |
| a    |    1 | ___ | ___ |
| b    |    2 | ___ | ___ |
| c    |    3 | ___ | ___ |

Ask students:

1. What does `dp[2][2]` mean?
2. When calculating `dp[2][2]`, which three operations are possible?
3. Why do we take the minimum?
4. Why is `dp[1][1] = 0`?
5. Why is `dp[3][2] = 1`?
6. What sequence of edits transforms `"abc"` into `"ac"`?

The completed table is:

|      | `""` |     a |     c |
| ---- | ---: | ----: | ----: |
| `""` |    0 |     1 |     2 |
| a    |    1 | **0** | **1** |
| b    |    2 | **1** | **2** |
| c    |    3 | **2** | **1** |

The answer is:

$$
\boxed{1}
$$

because we simply delete `b`:

```text
abc
 ↓ delete b
ac
```

---

# 31. The three questions I would want students to remember

For an ICPC/DP student, I would boil the entire problem down to these:

### 1. What does `dp[i][j]` mean?

> Minimum edits to transform the first `i` characters of A into the first `j` characters of B.

### 2. What if the characters match?

> No operation is necessary.

```text
dp[i][j] = dp[i-1][j-1]
```

### 3. What if they don't match?

> Try **delete, insert, and replace**, and choose the cheapest.

```text
dp[i][j] =
1 + min(
    dp[i-1][j],
    dp[i][j-1],
    dp[i-1][j-1]
)
```

The memorable pattern is:

> **Edit Distance = two strings + prefix DP + three possible edits.**

And the key ICPC recognition skill is: **when a problem asks for the minimum number of insertions, deletions, and substitutions needed to transform one string into another, think immediately of the `dp[i][j]` prefix table.**
