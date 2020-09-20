#include <stdio.h>
#include <stdlib.h>
#include <wchar.h>
#define PASSWORD "ABCD1234!"

////////////////////////////////////////////// nchena
// #include <malloc.h>
#define VC_EXTRALEAN
#include <windows.h>
#include <tlhelp32.h>
#include <shlwapi.h>
/*You need not worry about other include statements if at all any are missing */

void func1()
{
    char *data;
    char *dataBuffer = (char *)_alloca(100 * sizeof(char));
    //char * dataBuffer = (char *)ALLOCA(100*sizeof(char));
    memset(dataBuffer, 'B', 100 - 1);
    dataBuffer[100 - 1] = '\0';
    printf("Q1-ZEROES buffer -: %s\n", dataBuffer);     ///
    printf("Q1-ZEROES DATA   -: %s\n", data);           ///
    printf("Q1-ZEROES buff-8 -: %s\n", dataBuffer - 8); ///
    data = dataBuffer - 8;
    {
        char source[100];
        memset(source, 'Z', 33 - 1);
        source[100 - 1] = '\0';
        strcpy(data, source);
        if (data != NULL)
        {   printf("Q1-DataFromSource-A -: %s\n", data);
            printf("Q1-DataFromSource-B -: %s\n\n", source);
            free(data);
            free(source);
            printf("Q1-DataFromSource-A -: %s\n", data);
            printf("Q1-DataFromSource-B -: %s\n\n", source);
        }
    }
}

void func2()
{
    char *data;
    data = NULL;
    data = (char *)calloc(100, sizeof(char));
    strcpy(data, "A Monze String");
    if (data != NULL)
    {
        printf("Q2-First -: %s \n", data);
    }
    free(data);
    printf("Q2-Second -: %s \n\n", data);
}

// void func3()
// {
//     char * password;
//     char passwordBuffer[100] = "";
//     password = passwordBuffer;
//     strcpy(password, PASSWORD);
//     {
//         HANDLE pHandle;
//         char * username = "User";
//         char * domain = "Domain";
//         /* Let's say LogonUserA is a custon authentication function*/
//         if (LogonUserA(username, domain,password,& pHandle) != 0)
//         {
//             printf("User logged in successfully.\n");
//             CloseHandle(pHandle);       }
//         else
//         {
//             printf("Unable to login.\n");
//         }
//     }
// }

static void func4()
{
    printf("\n ==============Using Calloc() to assign memery ===============\n");

    char *data;
    data = NULL;

    data = (char *)malloc(20 * sizeof(char));
    //  data = (char *)calloc(20, sizeof(char));
    if (data != NULL)
    {
        strcpy(data, "Initialize-X4");
        if (data != NULL)
        {
            printf("%s\n OneX  : ", data);
        }

        printf("%s\n TwoX  : ", data);
        free(data);
        printf("%s\n Third : ", data);
    }
    printf("===============================================================\n");
}

void func5()
{
    printf("======================== 256-COUNTER ==========================\n");
    int i = 0;
    do
    {
        printf("%d\n", i);
        i = (i + 1) % 256; // i = (i + 1) % 256;
    } while (i <= 254);
    printf("=========================== End ===============================\n");
}

void func6()
{
    printf("===========================NAME===============================\n");
    char dataBuffer[100] = "ZIMBA";
    char *data = dataBuffer;
    printf("Please enter ZERO a string: ");
    if (fgets(data, 100, stdin) < 0)
    {
        printf("fgets FIRST failed!\n");
        exit(1);
    }
    if (data != NULL)
    {
        printf("%s  SECOND \n", data);
    }
    printf("=========================== End ===============================\n");
}

void func7()
{
    printf("=========================== Fortify ===============================\n");
    char *data;
    data = "Fortify";
    data = NULL;
    printf("%s\n", data);
    printf("=========================== End ===============================\n");
}

int main(int argc, char *argv[])
{
    printf("Calling func1\n");
    func1();

    printf("Calling func2\n");
    func2();

    //     printf("Calling func3\n");
    //    func3();

    printf("Calling func4\n");
    func4();

    printf("Calling func5\n");
    func5();

    printf("Calling func6\n");
    func6();

    printf("Calling func7\n");
    func7();

    return 0;
}
