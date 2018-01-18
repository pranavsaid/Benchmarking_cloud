/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: pranav sai deenumsetti
 *
 * Created on October 8, 2017, 1:28 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <time.h>

#define Max_Size (1024*1024*1024)
//int Max_Size = 1024*1024*1024;
/*
 * 
 */

char a[Max_Size];
char b[Max_Size];
pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;

///////////////////////////////////////////////////
///////////////////////////////////////////////////
/////////////////Functions to Read and write///////
///////////////////////////////////////////////////
///////////////////////////////////////////////////
void *Seq_rw_byte(void *arg)
{   
    //printf("Seq_rw_byte");
    pthread_mutex_lock( &mutex1 ); int i,j;
    int blockSize = 8;
    int data_size = Max_Size/blockSize;
    for(i=0;i<data_size;i++)
    {
        
      memcpy(&a[i*blockSize],&b[i*blockSize],blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}

void *Seq_w_byte(void *arg)
{   //printf("Seq_w_byte");
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    int blockSize = 8;
    int data_size = Max_Size/blockSize;
    //For Sequential Write & read
    
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Rand_write_byte(void *arg)
{   
    //printf("Rand_write_byte");
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    
    
    
    int blockSize = 8;
    int data_size = Max_Size/blockSize;
    int r = rand()%data_size;
    //For Sequential Write & read
    
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Seq_rw_kb(void *arg)
{   
    //printf("Seq_rw_kb");
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    
    
    
    int blockSize = 8*1024;
    int data_size = Max_Size/blockSize;
    //For Sequential Write & read
   
    for(i=0;i<data_size;i++)
    {
      memcpy(&a[i*blockSize],&b[i*blockSize],blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
    
}
void *Seq_w_kb(void *arg)
{   
    //printf("Seq_w_kb");
    
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    int blockSize = 8*1024;
    int data_size = Max_Size/blockSize;
    
    //For Sequential Write & read
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Rand_write_kb(void *arg)
{
    //printf("Rand_write_kb");
    
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    int blockSize = 8*1024;
    int data_size = Max_Size/blockSize;
    int r = rand()%data_size;
    //For Sequential Write & read
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Seq_rw_mb(void *arg)
{
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    
    
    int blockSize = 8*1024*1024;
    int data_size = Max_Size/blockSize;
    //For Sequential Write & read
    
    for(i=0;i<data_size;i++)
    {
      memcpy(&a[i*blockSize],&b[i*blockSize],blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Seq_w_mb(void *arg)
{
    
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    int blockSize = 8*1024*1024;
    int data_size = Max_Size/blockSize;
    
    //For Sequential Write & read
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Rand_write_mb(void *arg)
{
   
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    int blockSize = 8*1024*1024;
    int data_size = Max_Size/blockSize;
    int r = rand()%data_size;
    //For Sequential Write & read
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Seq_rw_mmb(void *arg)
{
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    
    
    int blockSize = 8*1024*1024*10;
    int data_size = Max_Size/blockSize;
    //For Sequential Write & read
    
    for(i=0;i<data_size;i++)
    {
      memcpy(&a[i*blockSize],&b[i*blockSize],blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Seq_w_mmb(void *arg)
{
    
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    int blockSize = 8*1024*1024*10;
    int data_size = Max_Size/blockSize;
    
    //For Sequential Write & read
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}
void *Rand_write_mmb(void *arg)
{
    
    pthread_mutex_lock( &mutex1 ); int i,j;
    
    int blockSize = 8*1024*1024*10;
    int data_size = Max_Size/blockSize;
    int r = rand()%data_size;
    //For Sequential Write & read
    for(i=0;i<data_size;i++)
    {
      memset(&a[i*blockSize],'$',blockSize);
    }
    pthread_mutex_unlock( &mutex1 );pthread_exit(NULL);
}


///////////////////////////////////////////////////
///////////////////////////////////////////////////
/////////////////Functions for MultiThreading//////
///////////////////////////////////////////////////
///////////////////////////////////////////////////



void *(*func_ptr[12])(void *arg) = {Seq_rw_byte, Rand_write_byte, Seq_w_byte,Seq_rw_kb, 
                        Rand_write_kb, Seq_w_kb,Seq_rw_mb, Rand_write_mb, 
                        Seq_w_mb,Seq_rw_mmb, Rand_write_mmb, Seq_w_mmb};

int main(int argc, char** argv) {
    int i,j,k,m,l,threadcount,temp;
    float total_time;
    clock_t start,end;
    for(j=0;j<Max_Size;j++)
        b[j]='p';
    float throughput=0, latency=0;  
        for(m=0;m<12;m++)
        {
            if(m==0)
                printf("\nSequential read+ Write with Block Size of 8 Byte \n\n\n");
           else if(m==1)
               printf("\n Random Write with Block Size of 8 Byte\n\n\n");
           else if(m==2)
               printf("\n Sequential write with Block Size of 8 Byte\n\n\n");
           else if(m==3)
                printf("\nSequential read+ Write with Block Size of 8 Kilo Byte \n\n\n");
           else if(m==4)
               printf("\n Random Write with Block Size of 8 Kilo Byte\n\n\n");
           else if(m==5)
               printf("\n Sequential write with Block Size of 8 Kilo Byte\n\n\n");
           else if(m==6)
                printf("\nSequential read+ Write with Block Size of 8 Mega Byte \n\n\n");
           else if(m==7)
               printf("\n Random Write with Block Size of 8 Mega Byte\n\n\n");
           else if(m==8)
               printf("\n Sequential write with Block Size of 8 Mega Byte\n\n\n");
           else if(m==9)
                printf("\nSequential read+ Write with Block Size of 80 Mega Byte \n\n\n");
           else if(m==10)
               printf("\n Random Write with Block Size of 80 Mega Byte\n\n\n");
           else 
               printf("\n Sequential write with Block Size of 80 Mega Byte\n\n\n");
          
        pthread_t t[10];
        int threads[]= {1,2,4,8};
        void *status;
        for(i=0;i<4;i++)
        {
           
           threadcount = threads[i];
           //Max_Size = ((1024*1024*1024)/threadcount);
           printf("For %d Thread",threadcount);
           
           start = clock();
           
           for(j=0;j<threadcount;j++)
           {
              
            if(m==0)
                pthread_create(&t[j],NULL,Seq_rw_byte,NULL);
           else if(m==1)
              pthread_create(&t[j],NULL,Rand_write_byte,NULL);
           else if(m==2)
             pthread_create(&t[j],NULL,Seq_w_byte,NULL);
           else if(m==3)
             pthread_create(&t[j],NULL,Seq_rw_kb,NULL);
           else if(m==4)
             pthread_create(&t[j],NULL,Rand_write_kb,NULL);
           else if(m==5)
             pthread_create(&t[j],NULL,Seq_w_kb,NULL);
           else if(m==6)
              pthread_create(&t[j],NULL,Seq_rw_mb,NULL);
           else if(m==7)
             pthread_create(&t[j],NULL,Rand_write_mb,NULL);
           else if(m==8)
             pthread_create(&t[j],NULL,Seq_w_mb,NULL);
           else if(m==9)
              pthread_create(&t[j],NULL,Seq_rw_mmb,NULL);
           else if(m==10)
             pthread_create(&t[j],NULL,Rand_write_mmb,NULL);
           else 
               
             pthread_create(&t[j],NULL,Seq_w_mmb,NULL);
              
           }
           for(k=0;k<threadcount;k++)
           {
     pthread_join(t[k],NULL);
           
           }
     end= clock();
           total_time = (float)((end-start)/CLOCKS_PER_SEC);
           //printf("\nTotal time is %f",total_time);
           if(m<3)
           {
           latency = (float)(total_time/(100*1024*1024)*1000);
           printf("\nTotal Latency for 800 MB data %f\n", latency);
           }
           else
           {
           throughput = (float)(Max_Size/(1024*1024*total_time));
           printf("\nTotal Throughput for 800 MB data %f\n", throughput);
           }
           
           
         }
        }
    
    
    return (Max_Size);
}
