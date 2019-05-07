package com.demo;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.google.common.collect.Lists;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo class.
 * 
 * @author Niranjan Nanda
 */
public class CouchbaseIssueDemo {
	private static final Logger logger = LoggerFactory.getLogger(CouchbaseIssueDemo.class);
	public static void main(final String[] args) {
		final CouchbaseEnvironment cbEnv = DefaultCouchbaseEnvironment.builder()
				.connectTimeout(50000L)
				.kvTimeout(30000L)
				.queryTimeout(30000L)
				.build()
				;
		
		final Cluster cbCluster = CouchbaseCluster.create(cbEnv, Lists.newArrayList("localhost"));
		
		cbCluster.authenticate("tmp_user", "pdp@123");
		logger.debug("Authenticated with cluster.");
		
		final Bucket bucket = cbCluster.openBucket("MY_META");
		logger.debug("Bucket '{}' opened successfully.", bucket.name());
		
		executeQuery(bucket);
	}
	
	private static void executeQuery(final Bucket bucket) {
		final N1qlQuery query = N1qlQuery.simple("Select _meta.id from #CURRENT_BUCKET# where meta().id = 'users-115'");
		logger.debug("Executing query -> {}", Objects.toString(query));
		final N1qlQueryResult queryResult = bucket.query(query);
		logger.debug("Query Result --> {}", queryResult);
	}
}
