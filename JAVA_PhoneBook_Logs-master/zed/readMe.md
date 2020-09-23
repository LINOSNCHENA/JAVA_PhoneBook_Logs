
# EXECUTION COMMANDS
===============================================
gcc zed1.c -o zed1
gcc zed2.c -o zed2
gcc zed3.c -o zed3

# RESULTS: VANURABILITIES FOUND
===============================================

1. function allocated memory is only within this stack. Variables and data would be destroyed when function is returned hence the need to watch out of usage of memory in cases of alloca function.
2. The allocated memory is never freed. This can cause memory leakages.  One viable solution would be to use function free(data)
3. .............
4. The function calloc () is slower as it must write to real memory all the zeros. This consumes time and could be avoided by exchanging the function with malloc().
5. The execution limit condition is un-affected. Instead of using (i>=o), a better option would be (i<=254). The variable i would always be greater than zero hence the current condition is redundant.
6. The comparison of null would never exist since the default is "". Condition can only be true if "" is explicitly replace with null which is intentional error. 
7.  Will return the same result because the initialized result is always replaced with the variable value of null. Making the code redundant as it returns same result always.
