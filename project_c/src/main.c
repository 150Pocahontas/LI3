/**
 * @file main.c
 * @brief Ficheiro que contém a API relativa à implementação da main
 */

#include "interpreter.h"
#include "sgr.h"

int main(){
	SGR sgr = initSGR();
	int running = 1;
	while(running == RUN){
		running = interpreter(sgr);
	}
	destroySGR(sgr);
	return 0;
}