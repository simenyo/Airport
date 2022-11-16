#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/types.h>
#include <unistd.h>

//Your code for global variables(if any)
int signal = 0;
pthread_cond_t generic_condition;
pthread_mutex_t mutex;

void* print_Pfizer () {
//Your code for Thread 1
for (int i = 0; i < 5; i++) {
	pthread_mutex_lock(&mutex);
	while (signal == 0) {
		pthread_cond_wait(&generic_condition, &mutex);
	}
	printf("Pfizer\n");
	signal = 0;
	pthread_cond_signal(&generic_condition);
	pthread_mutex_unlock(&mutex);
	}
}

void* print_Moderna () {
//Your code for Thread 2
for (int j = 0; j < 5; j++) {
	pthread_mutex_lock(&mutex);
	while (signal == 1) {
		pthread_cond_wait(&generic_condition, &mutex);
	}
		printf("Moderna\n");
		signal = 1;
		pthread_cond_signal(&generic_condition);
		pthread_mutex_unlock(&mutex);
	}
}

int main (int argc, char** argv ) {
	//Your code for initializing any local or global variables
	pthread_mutex_init(&mutex, NULL);
	pthread_cond_init(&generic_condition, NULL);

	pthread_t t1,t2;

	pthread_create(&t1, NULL, (void*)print_Pfizer, NULL);
	pthread_create(&t2, NULL, (void*)print_Moderna, NULL);

	//Your code for the rest of the program
	pthread_join(t1, NULL);
	pthread_join(t2, NULL);

	pthread_cond_destroy(&generic_condition);
	pthread_mutex_destroy(&mutex);

	printf("Astrazeneca!\n");
	return 0;
}

