package com.apollocurrency

import com.typesafe.config.ConfigFactory
import collection.JavaConverters._
import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.util.Random
import scalaj.http._
import java.util.UUID.randomUUID
import java.util.concurrent.TimeUnit


class PerformancePControl extends Simulation {

/*
	val env: String = System.getProperty("test.env")
	val users = System.getProperty("users").toDouble
	val duration = System.getProperty("duration").toDouble
 */

	val users = 25
	val duration = 10

	  val httpProtocol = http.baseUrl("http://apl-t3-mix.testnet3.apollowallet.org")
	//val httpProtocol = http.baseUrl("http://51.15.37.165:7876")


	val scn = scenario("Send Money")
		.exec(http("Send Money")
		.post(session => "/apl?" +
			"requestType=sendMoney&" +
			"feeATM=3000000000&" +
			"deadline=1440&" +
			"amountATM="+(Random.nextInt(40000)+1).toString+"00000000&" +
			"recipient=APL-WQPK-9J96-FA4V-H4QS5&secretPhrase=500&phased=true&phasingFinishHeight=640000&phasingVotingModel=0&phasingQuorum=1")
			.check(bodyString.saveAs("Response"))
			.check(jsonPath("$.errorDescription").notExists.saveAs("errorDescription"))
			.check(jsonPath("$.fullHash").find.saveAs("fullHash")))
  		.exec(session => {
			//	println(session("Response").asOption[String])
			  val fullHash = session("fullHash").asOption[String]
				session
		   }).pause(60)
		    .exec(http("Approve Transaction")
				.post("/apl?requestType=approveTransaction&" +
					"transactionFullHash=${fullHash}&" +
					"secretPhrase=1&" +
					"feeATM=3000000000&" +
					"deadline=1440")
				.check(status.is(200))
				.check(jsonPath("$.errorDescription").notExists.saveAs("errorDescription"))
				.check(bodyString.saveAs("Response")))
		    .exec(session => {
		   	// println(session("fullHash").asOption[String])
	     	session
		})




	val inject =	constantUsersPerSec(users) during (duration minutes)


	setUp(scn.inject(inject)).protocols(httpProtocol)
}