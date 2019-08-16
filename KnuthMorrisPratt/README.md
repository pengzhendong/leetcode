## KMP (Knuth Morris Pratt)

KMP 算法用于查找一个字符串（pattern）在另一个字符串（text）中出现的第一个位置。KMP 算法中用到了最长公共前后缀的长度，但是很多教程并没有解释为什么要使用最长公共前后缀，而是上来就算 Next 数组。几年前看过一个讲解 KMP 算法的[视频](https://www.youtube.com/watch?v=GTJr8OvyEVQ)，温习了一遍，还是那么通俗易懂。

### 暴力搜索

JDK 中自带字符串搜索算法 `text.indexOf()`，其实现原理如下：

``` java
public static int violentSearch(String text, String pattern) {
    int M = text.length();
    int N = pattern.length();
    if (N == 0 || N > M) return 0;

    int i = 0;
    int j = 0;
    while (i < M && j < N) {
        if (text.charAt(i) == pattern.charAt(j)) {
            i++;
            j++;
        } else {
            i = i - (j - 1);
            j = 0;
        }
    }
    if (j == N) return i - j;
    return -1;
}
```

时间复杂度为 O(MN)，空间复杂度 O(1)。KMP 算法可以将时间复杂度缩小为 O(M+N)，但是需要额外 O(N) 的空间复杂度。由于通常情况下字符串比较短，所以 JDK 中直接使用了暴力搜索的方法。暴力搜索例子如下：

|         |                                |                                |                                |                                |                                | ↓ i=5                      |      |
| ------- | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | -------------------------- | ---- |
| Text    | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | <font color="#0099ff">C</font> | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | <font color="red">C</font> | A    |
| Pattern | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | <font color="#0099ff">C</font> | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | <font color="red">D</font> | A    |
|         |                                |                                |                                |                                |                                | **↑ j=5**                  |      |

假设 Text 为 ABCABCA，Pattern 为 ABCABDA。当匹配到第六个字母时，发现不匹配。暴力搜索的方法是，让 i 和 j 都回溯，i 回到开始匹配的位置的下一个位置，j 回到 Pattern 的开始位置。从头开始重新匹配：

|         |                                | ↓ i=1     |   |   |   |   |   |   |
| ------- | ------------------------------ | --------- | - | - | - | - | - | - |
| Text    | <font color="#0099ff">A</font> | B         | C | A | B | C | A |   |
| Pattern |                                | A         | B | C | A | B | D | A |
|         |                                | **↑ j=0** |   |   |   |   |   |   |

然后会发现这两个字符不匹配，再继续上述操作，直到遍历完 Pattern。思考一下：在 D 字符没有匹配的时候，有必要让 i 回溯吗？

### 最长公共前后缀

仔细看这个 Pattern 会发现，D 的前面是 **AB**，整个 Pattern 也是以 **AB** 开头，那么是不是可以只回溯 j 呢？将 j 回溯到 Pattern 中 **AB** 的下一个字符所在的位置，即 j = 2： 

|         |      |      |      |                                |                                | ↓ i=5     |      |      |      |      |
| ------- | ---- | ---- | ---- | ------------------------------ | ------------------------------ | --------- | ---- | ---- | ---- | ---- |
| Text    | A    | B    | C    | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | C         | A    |      |      |      |
| Pattern |      |      |      | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | C         | A    | B    | D    | A    |
|         |      |      |      |                                |                                | **↑ j=2** |      |      |      |      |

虽然我们可以一眼看出这个位置所在，那么这个 j = 2 是怎么算的呢？这就涉及到了一个概念：最长公共前后缀。

对于 Pattern 中字符 D 前面的字符串：**ABCAB**，它的前、后缀如下表所示：

| 前缀             | 后缀             |
| ---------------- | ---------------- |
| A, AB, ABC, ABCA | B, AB, CAB, BCAB |

一个长度为 N 的字符串，有 N - 1 个前缀和 N - 1 个后缀，因此 **ABCAB** 有四个前缀和四个后缀。同时出现在前缀和后缀中的最长字符串（最长公共前后缀）就是：**AB**，<font color="red">所以 j 应该回溯到这个前缀的后面进行重新匹配</font>。由上可知，当 Pattern 中某个字符与 Text 不匹配时，j 可以根据 Pattern 自身的特定进行回溯，回溯到 j 前面的字符串的最长公共前后缀后面的位置。

这就是 KMP 算法的思想，利用 Pattern 先计算一个 next 数组，next[i] 保存 `pattern.substring(0, i)` 的最长公共前后缀的长度，用于不匹配时回溯。

### Next 数组

`ABCABDA` 的 next 数组计算过程如下所示：

| Pattern                | A    | B    | C    | A     | B          | D                | A                       |
| ---------------------- | ---- | ---- | ---- | ----- | ---------- | ---------------- | ----------------------- |
| **该字符前面的**字符串   |  | A    | AB   | ABC   | ABCA       | ABCAB            | ABCABD                  |
| 字符串的前缀 |      |  | <font color="#0099ff">A</font> | <font color="#006400">A, AB</font> | <font color="FF0000">A, AB, ABC</font> | <font color="#808000">A, AB, ABC, ABCA</font> | <font color="#800080">A, AB, ABC, ABCA, ABCAB</font> |
| 字符串的后缀 |      |  | <font color="#0099ff">B</font> | <font color="#006400">C, BC</font> | <font color="FF0000">A, CA, BCA</font> | <font color="#808000">B, AB, CAB, BCAB</font> | <font color="#800080">D, BD, ABD, CABD, BCABD</font> |
| 公共前后缀 | | |  |  | <font color="FF0000">A</font> | <font color="#808000">AB</font> |  |
| 最长公共前后缀 | | |  |  | <font color="FF0000">A</font> | <font color="#808000">AB</font> |  |
| 最长公共前后缀的长度 | 0 | 0 | 0 | 0 | 1 | 2 | 0 |

因此就得到了 next 数组为：`[0, 0, 0, 0, 1, 2, 0]`。也就是说：

* 当匹配到第一个字符 A 时，如果不匹配，那么 j = 0；
* 当匹配到第二个字符 B 时，如果不匹配，那么 j = 0；
* 当匹配到第三个字符 C 时，如果不匹配，那么 j = 0；
* 当匹配到第四个字符 A 时，如果不匹配，那么 j = 0；
* 当匹配到第五个字符 B 时，如果不匹配，由于 B 的前面的字符串 `ABCA` 的最长公共前后缀为 `A`，所以 j = 1，不需要重新匹配开头的 `A`；
* 当匹配到第六个字符 D 时，如果不匹配，由于 D 的前面的字符串 `ABCAB` 的最长公共前后缀为 `AB`，所以 j = 2，不需要重新匹配开头的 `AB`；
* 当匹配到第七个字符 A 时，如果不匹配，那么 j = 0。

不同的教程还会为了代码方便对这个数组进行修改，此处为了更加易于理解，不进行任何修改。

计算公共前缀后缀，也就是在 pattern 中间开始匹配 pattern 本身，计算 next 数组的代码如下：

``` java
private static int maxCPSLen(String str) {
    int len = str.length();
    int ret = 0;

    int i = 0;
    int j = 1;
    while (i < len - 1 && j < len) {
        if (str.charAt(i) == str.charAt(j)) {
            i++;
            j++;
            ret++;
        } else {
            i = 0;
            // 回溯到首个匹配的字符的下一个位置
            j = j - ret + 1;
            // 重新开始匹配
            ret = 0;
        }
    }
    return ret;
}

private static int[] getNext(String pattern) {
    int len = pattern.length();
    int[] next = new int[len];
    next[0] = 0;
    next[1] = 0;

    for (int i = 2; i < len; i++) {
        String str = pattern.substring(0, i);
        next[i] = maxCPSLen(str);
    }
    return next;
}
```

我们希望计算一个 next 数组来解决字符串匹配的问题，但是计算这个 next 数组的过程又是一个字符串匹配问题。

#### 动态规划

如何快速求解 next 数组呢？这也是 KMP 算法的难点所在。仔细分析不难发现，可以利用 next[0], ..., next[i-1] 的值来计算 next[i]，具备动态规划的要求，只不过这里的状态转移方程有点复杂。这一部分内容不好描述清楚，首先我们设置两个循环不变量 i 和 j：

* i: 当前最长公共前后缀的长度，初始化为 0，即指向这个前缀的下一个字符
* j: 待求的 next 元素的索引，初始化为 2

为了便于说明各种情况，这里使用的 pattern 为 `ABABACA`

这里先不解释 next[2] 的计算过程。当计算到 next[3] 时，计算过程如下所示：

|         | ↓i |   |   | ↓j |   |   |   |
| ------- | -- | - | - | -- | - | - | - |
| Index   | 0  | 1 | 2 | 3  | 4 | 5 | 6 |
| Pattern | A  | B | A | B  | A | C | A |
| Next    | 0  | 0 | 0 | ?  |   |   |   |

由于 next[2] = 0 对应的是 `AB` 的最长公共前后缀的长度，所以当前最长公共前后缀的长度 i 为 0。next[3] 对应的是 `ABA` 的最长公共前后缀的长度，只需要判断 `ABA` 的最后一个字符是否等于 i 所指向的字符：

``` java
if (pattern.charAt(i) == pattern.charAt(j - 1)) {
    i++;
    next[j] = i;
    j++;
}
```

这两个字符相等，那么当前最长公共前后缀的长度 i 加一，同时记录到 next[j] 中，随后 j 也加一：

|         |   | ↓i |   |   | ↓j |   |   |
| ------- | - | -- | - | - | -- | - | - |
| Index   | 0 | 1  | 2 | 3 | 4  | 5 | 6 |
| Pattern | A | B  | A | B | A  | C | A |
| Next    | 0 | 0  | 0 | 1 | ?  |   |   |

同样，可以得到 next[5] = 2，next[6] = 3：

|         |   |   |   | ↓i |   |   |   | ↓j |
| ------- | - | - | - | -- | - | - | - | -- |
| Index   | 0 | 1 | 2 | 3  | 4 | 5 | 6 | 7  |
| Pattern | A | B | C | A  | B | C | B | A  |
| Next    | 0 | 0 | 0 | 0  | 1 | 2 | 3 | ?  |

此时 `pattern.charAt(i) != pattern.charAt(j - 1)`，因为

#### 有限状态机

对于 `ABCABDA` 我们可以画出求解过程的状态转移图：


#### 优化



### 代码

有了 next 数组，我们就可以写出 KMP 搜索的代码：

``` java
public static int kmpSearch(String text, String pattern) {
    int M = text.length();
    int N = pattern.length();
    if (N > M) return 0;
    if (N < 2) return text.indexOf(pattern);

    int[] next = getNext(pattern);

    int i = 0;
    int j = 0;
    while (i < M && j < N) {
        if (text.charAt(i) == pattern.charAt(j)) {
            i++;
            j++;
        } else {
            // 当 j 为 0 时，表示 j 已经处于 pattern 的开头，需要移动 i
            if (j == 0) i++;
            // 否则回溯 j
            else j = next[j];
        }
    }
    if (j == N) return i - j;
    return -1;
}
```