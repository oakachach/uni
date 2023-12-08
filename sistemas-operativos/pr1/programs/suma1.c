#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>

// argc comprueba el número de argumentos.
int main(int argc, char *argv[]) {
        int firstNumber = atoi(argv[1]);
        int secondNumber = atoi(argv[2]);        
        // Hace la suma.
        int totalSum = firstNumber + secondNumber;
        
        // Abre el fichero.
        int fd = open("suma1_result.txt", O_APPEND | O_CREAT | O_WRONLY, 0644);
        // Escribe en el fichero.
        dprintf(fd, "El resultado de sumar %d + %d = %d\n", firstNumber, secondNumber, totalSum);
        // Cierra el fichero.
        close(fd);

        // Imprime el resultado.
        printf("El resultado de la suma se ha escrito en el fichero suma1_result.txt\n");

        return 0;
}
