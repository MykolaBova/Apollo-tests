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


class PerformanceSimulation {

/*


	val users = 50
	val duration = 10

	  val httpProtocol = http.baseUrl("http://apl-t3-mix.testnet3.apollowallet.org")
	//val httpProtocol = http.baseUrl("http://51.15.37.165:7876")


	val scn = scenario("Send Money")
		.exec(http("Send Money")
		.post(session => "/apl?" +
			"requestType=sendMoney&" +
			"feeATM=3000000000&" +
			"deadline=1440&" +
			"amountATM="+(Random.nextInt(200)+1).toString+"00000000&" +
			"recipient=APL-NZKH-MZRE-2CTT-98NPZ&secretPhrase="+Random.nextInt(200).toString)
			.check(jsonPath("$.errorDescription").notExists.saveAs("errorDescription"))
		)

	val inject =	constantUsersPerSec(users) during (duration minutes)


	setUp(scn.inject(inject)).protocols(httpProtocol)

 */
}