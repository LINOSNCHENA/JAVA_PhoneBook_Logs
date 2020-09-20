#include <stdio.h>
#include <stdlib.h>
// Extra
#include <string.h>

int main () {
   char *str;
   printf("======================= START=====================\n");

   /* Initial memory allocation */
   str = (char *) malloc(15);
   strcpy(str, "pembahighschool");
   printf("String1 = %s,  Address1 = %u\n", str, str);

   /* Reallocating memory */
   str = (char *) realloc(str, 25);
   strcat(str, ".com");
   printf("String2 = %s,  Address1 = %u\n", str, str);

   free(str);
    printf("===================== FINISH ======================\n");
   
   return(0);
}