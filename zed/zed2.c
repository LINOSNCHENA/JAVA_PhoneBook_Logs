#include <stdio.h>

int main()
{
   const char* names[] = {"apples", "oranges", "grapes"};

   const char* first = names[0];
   const char* second = names[1];
   const char* third = names[2];

   const char* foo = &(*names[0]);

   printf("%s", foo);
   printf("%s", second);
   printf("%s", third);

}