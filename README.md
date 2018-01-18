# Benchmarking_cloud
This project aims to benchmark different parts of a computer system, from the CPU, GPU, memory, disk, and network.

• CPUBenchmarking Programming – Java

Problem Statement – To find GFLOPS and GIOPS of CPU

Requirements – CentOS/Windows/Linux, Java 1.7.0
Navigate to Code/CPU folder Run java *.java
Run java CpuBenchmarking
Sample output from chameleon instance is available as CPU_Benchmarking_Output.txt and also screenshots are available are a proof of execution
Method:
Instructions are given for CPU to RUN
Start and End time are noted
Total elapsed time is calculated
Performance is evaluated using – (no of instructions/total time)


• DiskBenchmarking Programming – Java

Problem Statement – Calculate Latency and throughput for Disk Benchmarking

Strong Scaling is Used
Sequential Read write, Sequential read, Random read are Measured
Block sizes are 8Byte, 8KB, 8 MB, , 80 MB
Threads are 1,2,4,8
Measurement value for Throughput is MB per Sec and latency is Milli Second

Requirements – CentOS/Windows/Linux, Java 1.7.0
Navigate to Code/Disk folder Run java *.java
Run java DiskBenchmarking
Sample output from chameleon instance is available as Disk_Benchmarking_Output.txt and screenshots are available are a proof of execution

• MemoryBenchmarking Programming – Java

Problem Statement – Calculate Latency and throughput for Memory Benchmarking

Strong Scaling is Used
Sequential Read write, Sequential write, Random write are Measured
Block sizes are 8Byte, 8KB, 8 MB, , 80 MB
Threads are 1,2,4,8
Measurement value for Throughput is MB per Sec and latency is Milli Second

Requirements – CentOS/Windows/Linux, Java 1.7.0
Navigate to Code/memory folder Run –gcc –pthread memory.c Run ./a.out
Sample output from chameleon instance is available as Output_Memory_Benchmarking.txt and also screenshots are available are a proof of execution
   
• NetworkBenchmarking Programming – Java

Problem Statement – Calculate Loopback throughput and latency for Memory Benchmarking

Strong Scaling is Used
TCP, UDP Protocols are used
Block sizes are 8Byte, 8KB, 8 MB, , 80 MB
Threads are 1,2,4,8
Measurement value for Throughput is MB per Sec and latency is Milli Second
Buffer Size , Packet Size through-out the experiment – 64kb

Requirements – CentOS/Windows/Linux, Java 1.7.0 Run TCP Server
Navigate to Code/network folder Run javac *.java
Run java TCPServer
Run TCP Client (without terminating off TCP server)
Open other command prompt Navigate to Code/network folder Run java TCPclient
Run UDP Server
Navigate to Code/network folder Run java UDPServer
Run UDP Client (without terminating UDP server)
Open other command prompt Navigate to Code/network folder Run java UDPClient
Sample output from chameleon instance is available as Network_Output _Benchmarking.txt and also screenshots are available are a proof of execution