#include <stdio.h>
#include <stdlib.h>

// argc comprueba el número de argumentos.
int main(int argc, char *argv[]) {
        int firstNumber = atoi(argv[1]);
        int secondNumber = atoi(argv[2]);        
        // Hace la suma.
        int totalSum = firstNumber + secondNumber;
        FILE *filePointer;

        // Abre el fichero.
        filePointer = fopen("suma2_result.txt", "a");
        // Escribe el resultado de la suma en el fichero.
        fprintf(filePointer, "El resultado de sumar %d + %d = %d\n", firstNumber, secondNumber, totalSum);
        // Cierra el fichero.
        fclose(filePointer);

        // Imprime el resultado.
        printf("El resultado de la suma se ha escrito en el fichero suma2_result.txt\n");

        return 0;
}
