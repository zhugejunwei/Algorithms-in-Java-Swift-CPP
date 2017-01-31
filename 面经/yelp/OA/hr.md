1. Linux中grep是什么意思
2. What is the command to check if a host is online?(ping+IP地址). 
3. 网页传输用什么协议(TCP/IP)
4. HTML默认端口号是多少(我回答的是80或者443，应该是80)
5. 64bit的机器是几个byte(8个)
6. SQL里面，删除一行用什么指令(delete)
7. Linkedlist和arraylist的区别(这个简单，CS常识). 
8. sorted array的binary search复杂度(log n)
9. sql的inner join是交并差？
10. ip和域名的映射是怎么样完成的(DNS, domain name system)
11. 举例说明deadlock。我就说了下CC上面那个拿筷子和放筷子的例子，又说了下不同thread访问一个文件。后来引申问有什么么检测的方法。我說了可以 timeout 和用其他的service建graph遍历来检测，后来又问了为什么dfs比bfs检测graph的环好。

12. 中间问了些基础知识，比如像C++ vector list等等数据结构查找删除 size()的时间复杂度.
13. c++和java区别 （本人主要语言C++）
14. java GC 工作机制
15. post get区别 怎么保证post请求不被中间人篡改
16. 数据库index什么原理，B树和hash table优缺点: B tree 的search time 是logN, 但是好處是range search的時候快。Hash table的edit, search time都是O(1), 缺點是range search的時候非常慢
17. HTTP: GET vs POST. Why POST is not cachable..（http://stackoverflow.com/questions/626057/is-it-possible-to-cache-post-methods-in-http）
18. Java: pass by reference or pass by value? what does pass by value mean?
19. Database: Left join and Inner Join.
如果一个Database的查询速度很慢，如何改进。如果denormalization之后还是不给力，怎么办？
20. How many bits does one need to represent a single octal digit? 3
21. Biggest unsigned integer in Java?. （there is no unsigned int in java ）
22.What is the time complexity to insert an element at head of a linked list?
23. SSL stands for?
24. How to map ip to server?
25. What is the result of inner join? intersection, union, or difference?
26. What is your HTTP port number? What is the Protocol?
27. What is the linux command line for searching a method in a source code?


########################### 下面是更详细的问题和答案

There are two parts.
1st part is basic CS knowledge(can be found on glassdoor), 2nd part is one 
coding question(Reverse Words in a String problem from leetcode)
. 1point 3acres 

1st part questions and answers:
2. No. of bytes in a 64 bit machine.
Number of bytes to code 64 bits?
8


3. What is the value of the unsigned integer in a 32 bit machine? Maximum value of 32 bits unsigned int?
unsigned int        4bytes        0 to 4294967295(2^32 - 1)
two to the power of 32 minus one
http://www.tutorialspoint.com/cplusplus/cpp_data_types.htm
. visit 1point3acres.com for more.

4. How many bits are required to represent an octal digit?
- Number of digits needed to code an octet? 3
- What if we're in base 8? 3
http://stackoverflow.com/questions/19575629/how-many-digits-to-code-an-octet.


5. Where can you apply binary search - sorted array or linked list?
sorted array


6. Time complexity of binary search.
O(logn)

7. Time complexity of adding a node at the head of the linked list.
O(1)

8. What is the signal number for kill. -9
http://meinit.nl/the-3-most-important-kill-signals-on-the-linux-unix-command-line


Most Linux or UNIX users know that there is a kill(1) command to stop processes, but what are the options, what do they mean?. 


These options are called signals, which can be expressed in either numbers or words. Some known once are "-1" or "-HUP". Also well known is "-9" (aka "-KILL")

9. How would you find a method in a file.. 
find . -name '*.cpp' | xargs grep XXX
http://unix.stackexchange.com/questions/97488/command-to-highlight-the-occurrence-of-a-method-in-a-file


10. How would you find whether a remote computer is online or not
ping IP/domain name


11. How does the domain name map to ip address
http://www.firewall.cx/networking-topics/protocols/domain-name-system-dns.html

DNS is a very popular and well known protocol. It is used for resolving host names and domain names to IP addresses. The fact is that when you type www.firewall.cx the name is translated into an IP address via a number of queries that take place from your PC towards the DNS server. This DNS Query process (and more) is analysed in great depth in the articles that follow.


12. How to select one row of a table in SQL.
http://www.codeproject.com/Questions/219741/Select-nth-row-of-a-table-in-sql-server




SQL SELECT TOP Clause
http://stackoverflow.com/questions/16568/how-to-select-the-nth-row-in-a-sql-database-table
http://www.w3schools.com/sql/sql_top.asp


13. Port number for HTTP and protocol used by HTTP
80 TCP/IP ?
http://searchnetworking.techtarget.com/answer/How-are-TCP-IP-and-HTTP-related
http://www.firewall.cx/networking-topics/protocols.html

HTTP is an application layer protocol designed within the framework of the Internet Protocol Suite. Its definition presumes an underlying and reliable transport layer protocol,[3] and Transmission Control Protocol (TCP) is commonly used. However HTTP can use unreliable protocols such as the User Datagram Protocol (UDP), for example in Simple Service Discovery Protocol (SSDP).


14. Full form of SSL and what does it do?   
SSL stands for Secure Socket Layer. The protocol's name is now officially TLS but we will continue to use SSL since that's the better known name.

SSL was designed to permit web browsers and web servers to exchange sensitive information and prevent programs that could view the network traffic from reading the sensitive data.
-google 1point3acres



-number of bit in octal. 1point 3acres 
-some command in linuz(grep, kill)
-some sort complexity(binary)
-adding element to first of linked list
-data base question(outerjoin, left joint,..). Waral 
-SSL? TCP?. 


- 3 adjectives coworkers would use to describe you?
- Why would you want to work at Yelp?
. From 1point 3acres bbs

- Port number used by HTTP?
- Protocol underneath HTTP?
- Runtime efficiency of searching an element in an array using a binary search algorithm?
- Complexity of adding an element to the head of a linked list?
- Maximum value of 32 bits unsigned int?
- Number of digits needed to code an octet?
- What if we're in base 8?
- Number of bytes to code 64 bits?
- What does SSL stands for and what does it do?. From 1point 3acres bbs
- What is the Linux kill signal?. 
- Command to highlight the occurrence of a method in a file?
- Would you apply a binary search on a linked list or a sorted array?


- Is an inner join an intersection, a union or something else?
Inner join - An inner join using either of the equivalent queries gives the intersection of the two tables, i.e. the two rows they have in common.
Left outer join - A left outer join will give all rows in A, plus any common rows in B.
Full outer join - A full outer join will give you the union of A and B, i.e. All the rows in A and all the rows in B. If something in A doesn't have a corresponding datum in B, then the B portion is null, and vice versa. 

http://stackoverflow.com/questions/19267238/what-is-difference-between-inner-join-and-outer-join
http://stackoverflow.com/questions/38549/difference-between-inner-and-outer-joins
