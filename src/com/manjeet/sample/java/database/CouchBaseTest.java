package com.manjeet.sample.java.database;

import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.dsl.Expression.s;
import static com.couchbase.client.java.query.dsl.Expression.x;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Select;
import com.couchbase.client.java.query.Statement;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;



public class CouchBaseTest {
	
	public static void main(String[] args) {
		
		String serverName = "localhost";
		CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
		        .queryEnabled(true)
		        .build();
		Cluster cluster = CouchbaseCluster.create(env, serverName);
		Bucket bucket = cluster.openBucket("beer-sample", "");
		System.out.println(bucket);

		String statement = "SELECT * FROM `beer-sample` limit 1";
		N1qlQuery query = N1qlQuery.simple(statement);
		N1qlQueryResult result = bucket.query(query);
		for (N1qlQueryRow row : result)
		{ 
			System.out.println(row.value());
		}
		cluster.disconnect();

	}


}
