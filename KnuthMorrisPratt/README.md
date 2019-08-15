## KMP (Knuth Morris Pratt)

KMP 算法用于查找一个字符串（pattern）在另一个字符串（text）中出现的位置。KMP 算法中用到了最长公共前后缀的长度，但是很多教程并没有解释为什么要使用最长公共前后缀，而是上来就算 Next 数组。几年前看过一个讲解 KMP 算法的[视频](https://www.youtube.com/watch?v=GTJr8OvyEVQ)，温习了一遍，还是那么通俗易懂。

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

仔细看这个 Pattern 会发现，D 的前面是 **AB**，整个 Pattern 也是以 **AB** 开头，那么是不是可以只回溯 j 呢？将 j 回溯到 Pattern 中 **AB** 的下一个字符所在的位置，即 j=2： 

|         |      |      |      |                                |                                | ↓ i=5     |      |      |      |      |
| ------- | ---- | ---- | ---- | ------------------------------ | ------------------------------ | --------- | ---- | ---- | ---- | ---- |
| Text    | A    | B    | C    | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | C         | A    |      |      |      |
| Pattern |      |      |      | <font color="#0099ff">A</font> | <font color="#0099ff">B</font> | C         | A    | B    | D    | A    |
|         |      |      |      |                                |                                | **↑ j=2** |      |      |      |      |

虽然我们可以一眼看出这个位置所在，那么这个 j=2 是怎么算的呢？这就涉及到了两个概念：前缀和后缀。

对于 Pattern 中字符 D 前面的字符串：**ABCAB**，它的前、后缀如下表所示：

| 前缀             | 后缀             |
| ---------------- | ---------------- |
| A, AB, ABC, ABCA | B, AB, CAB, BCAB |

一个长度为 N 的字符串，有 N - 1 个前缀和 N - 1 个后缀，因此 **ABCAB** 有四个前缀和四个后缀。即出现在前缀中，也出现在后缀中的最长的字符串（最长公共前后缀）就是：**AB**，<font color="red">所以 j 应该回溯到这个前缀的后面进行重新匹配</font>。

由上可知，当 Pattern 中某个字符与 Text 不匹配时，j 可以根据 Pattern 自身的特定进行回溯，而不需要回到 Pattern 的第一个字符。这就是 KMP 算法的思想，利用 Pattern 先计算一个 next 数据，用于不匹配时回溯。

### Next 数组

当 D 不匹配时，j=2，这个 2 是 **D 前面的字符串的最长公共前后缀的长度**。那么其他字符不匹配时呢？我们需要为每一个字符计算一个回溯的值，即每个字符前面的字符串的最长公共前后缀的长度。

这个例子公共前后缀只有一个，因此也就是最长公共前后缀：

| Pattern                | A    | B    | C    | A     | B          | D                | A                       |
| ---------------------- | ---- | ---- | ---- | ----- | ---------- | ---------------- | ----------------------- |
| **字符前面的**字符串   |  | A    | AB   | ABC   | ABCA       | ABCAB            | ABCABD                  |
| 字符前面的字符串的前缀 |      |  | <font color="#0099ff">A</font> | <font color="#006400">A, AB</font> | <font color="FF0000">A, AB, ABC</font> | <font color="#808000">A, AB, ABC, ABCA</font> | <font color="#800080">A, AB, ABC, ABCA, ABCAB</font> |
| 字符前面的字符串的后缀 |      |  | <font color="#0099ff">B</font> | <font color="#006400">C, BC</font> | <font color="FF0000">A, CA, BCA</font> | <font color="#808000">B, AB, CAB, BCAB</font> | <font color="#800080">D, BD, ABD, CABD, BCABD</font> |
| 公共前后缀 | | |  |  | <font color="FF0000">A</font> | <font color="#808000">AB</font> |  |
| 最长公共前后缀 | | |  |  | <font color="FF0000">A</font> | <font color="#808000">AB</font> |  |
| 最长公共前后缀的长度 | 0 | 0 | 0 | 0 | 1 | 2 | 0 |

因此就得到了 next 数组为：`[0, 0, 0, 0, 1, 2, 0]`。因为 pattern 前两个字符的前面的字符串没有前后缀，所以其最长公共前后缀的长度为 0。不同的教程还会为了代码方便对这个数组进行修改，此处为了更加易于理解，不进行任何修改。

#### 动态规划

如何编写求解 next 数组的代码呢？next[i] 存放的是字符串 `pattern.substring(0, i)` 的最长公共前后缀的长度

### KMP 搜索

有了 next 数组，我们就可以写出 KMP 搜索的代码：

``` java
public static int kmpSearch(String text, String pattern) {
    int M = text.length();
    int N = pattern.length();
    if (N == 0 || N > M) return 0;

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