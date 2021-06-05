package com.github.ivetan

import com.github.ivetan.CassandraExample.{cassandraExample, getResults, setResults}

object UsingCassandra extends App {
  println("Using Cassandra connection")
  val host = "cassandra-36524c76-iveta-2b95.aivencloud.com"
  val port = 27416
  val username = "" //FIXME username needed
  val password = "" //FIXME password needed
  val caPath = "./src/resources/certs/ca.pem"

  cassandraExample(host=host,port=port,username=username,password=password, caPath=caPath)
  println("Lets hold our fingers crossed")
  setResults(host=host,
    port=port,
    username=username,
    password=password,
    caPath=caPath,
    keyspace = "example_keyspace",
    id = 9000,
    message = "Hello from Space on Jun 3rd")

  val query = "SELECT id, message FROM example_java"
  val results = getResults(host=host,port=port,username=username,password=password, caPath=caPath, keyspace = "example_keyspace", query = query)
  results.foreach(println)
}
