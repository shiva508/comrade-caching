# Hazelcast
### Hazelcast is an open-source distributed in-memory object store supporting a wide variety of data structures.
### Hazelcast used to store your data in RAM, spread and replicate it across your cluster of machines, and perform computations on it. Replication gives you the resilience to failures of cluster members.
### Hazelcast is highly scalable and available. Distributed applications can use it for distributed caching, synchronization, clustering, processing, pub/sub messaging, etc.
## Hazelcast is a fit when you need:
### analytic applications requiring big data processing by partitioning the data
### to retain frequently accessed data in the grid
### a cache, particularly an open source JCache provider with elastic distributed scalability
### a primary data store for applications with utmost performance, scalability and low-latency requirements
### an In-Memory NoSQL Key Value Store
### publish/subscribe communication at highest speed and scalability between applications
### applications that need to scale elastically in distributed and cloud environments
### a highly available distributed cache for applications
### an alternative to Coherence and Terracotta.
## Architecture
## Topology
## Embedded
### Goal : Asynchronous or High performance computing and lots of task Executions.
### How : Embedded deployment, members include both the application and Hazelcast data and services.
### Pros : The advantage of the Embedded deployment is having a low-latency data access.
## Client/Server
###  Hazelcast data and services are centralized in one or more server members and they are accessed by the application through clients
### Cluster of server members that can be independently created and scaled
## Data Partitioning
### The memory segments in Hazelcast are called partitions. 
### amount of data entries they can store, is limited by the physical capacity of your system.
### The partitions are distributed equally among the members of the cluster. 
### Hazelcast also creates backups of these partitions which are also distributed in the cluster.
### By default, Hazelcast creates a single copy/replica of each partition.
### You can configure Hazelcast so that each partition can have multiple replicas(271). 
### One of these replicas is called "primary" and others are called "backups". 
### The cluster member which owns the "primary" replica of a partition is called the "partition owner".
### When you read or write a particular data entry, you transparently talk to the partition owner that contains the data entry.
## How the Data is Partitioned
### Hazelcast distributes data entries into the partitions using a hashing algorithm. Given an object key or an object name
### the key or name is serialized (converted into a byte array)
### this byte array is hashed
### the result of the hash is mod by the number of partitions.
### The result of this modulo - MOD(hash result, partition count) - is the partition in which the data will be stored.
### Partition Table
### The partition table stores the partition IDs and the addresses of cluster members to which they belong. 
### The purpose of this table is to make all members (including lite members) in the cluster aware of this information, making sure that each member knows where the data is
### When you start your first member, a partition table is created within it. As you start additional members, that first member becomes the "oldest" member and updates the partition table accordingly.
### It periodically sends the partition table to all members. In this way each member in the cluster is informed about any changes to partition ownership.
### The ownerships may be changed when, for example, a new member joins the cluster, or when a member leaves the cluster.
## Repartitioning
### Repartitioning is the process of redistribution of partition ownerships. Hazelcast performs the repartitioning when a member joins or leaves the cluster.




