#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main(int argc, char *argv[]) {
    int fd;
    struct stat st;
    char *buffer;
    int i, j;

    /* Your code here */
    if (argc < 2) {
        printf("No file name given.\n");
        return -1; 
    }

    if (argc > 2) {
        printf("Too many arguments received.\n");
        return -1;
    }

    fd = open(argv[1], O_RDONLY);

    if (fd < 0) {
        printf("File not found.\n");
        return -1;
    }

    /* Mirar el tamaño del fichero */
    fstat(fd, &st);

    /* Asignación dinámica de memoria */
    /* Your code here */
    if (st.st_size == 0) {
        printf("Empty file.\n");
        return -1;
    }
    
    if (st.st_size > 50) {
        printf("File too big.\n");
        return -1;
    }

    buffer = (char*)malloc(st.st_size);

    /* Leer el contenido del fichero y mostrarlo por pantalla */
    read(fd, buffer, st.st_size);

    for (i = 0; i < st.st_size; i++) {
        for (j = 0; j <= i; j++) {
            printf("%c", buffer[j]);
        }
        printf("\n");
    }

    /* Your code here */
    free(buffer);

    /* Cerrar el fichero */
    close(fd);

    return 0;
}
