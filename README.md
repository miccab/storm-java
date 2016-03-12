#storm-java

Demo on how to setup and play with Storm

DONE

- Create sample setup for local (in-process) cluster
- Run the same setup but against remote cluster. Steps
  - Start storm (https://storm.apache.org/documentation/Setting-up-a-Storm-cluster.html): start zookeper, nimbus, supervisor, UI
  - build a single JAR with the code
  - submit JAR to storm (http://storm.apache.org/documentation/Running-topologies-on-a-production-cluster.html)
  - observe in UI that it works
- review common patterns http://storm.apache.org/documentation/Common-patterns.html
- read concepts (http://storm.apache.org/documentation/Concepts.html)
- read http://storm.apache.org/documentation/Guaranteeing-message-processing.html
- read http://storm.apache.org/documentation/Understanding-the-parallelism-of-a-Storm-topology.html
  

TODO:

- use real Spout (e.g. Kafka or JDBC)
- review exactOnce delivery (trident)

