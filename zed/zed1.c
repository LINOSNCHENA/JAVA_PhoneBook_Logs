#include <stdio.h>

int main()
{
   // Arrays of characters
   
   const char* names[] = {"LUSAKA", "VASTERAS", "ZLIN","KAZAN"};

   const char* first = names[0];
   const char* second = names[1];
   const char* third = names[2];

   const char* foo = &(*names[0]);

   printf("\n Z=%s", foo);
   printf("\n S=%s", second);
   printf("\n K=%s", third);

}