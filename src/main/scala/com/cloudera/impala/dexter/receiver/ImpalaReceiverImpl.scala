package com.cloudera.impala.dexter.receiver

import com.cloudera.impala.dexter.exceptions.DexterAuthException
import com.cloudera.impala.dexter.utill.{ImpalaQueries, ImpalaQuery}
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger

import io.circe.parser.decode

import scalaj.http.Http

/**
  * Created by ShemTov on 09/08/2017.
  */
class ImpalaReceiverImpl() extends ImpalaReceiver {

  private val conf = ConfigFactory.load()
  private val logger = Logger(this.getClass.getSimpleName)
  private val cmUrl = s"http://${conf.getString("CM.HOST")}:${conf.getString("CM.PORT")}" +
    s"/api/v${conf.getString("CM.API.VERSION")}" +
    s"/clusters/${conf.getString("CLUSTER.NAME")}" +
    s"/services/${conf.getString("SERVICES.IMPALA.NAME")}" +
    s"/impalaQueries"
  private val cmUser = conf.getString("CM.USER")
  private val cmPass = conf.getString("CM.PASSWORD")


  def getImpalaQueries(filter: String): Array[ImpalaQuery] = {
    try {
      logger.info(s"Requesting queries matching the filter: $filter")
      val httpResponse = Http(cmUrl)
        .auth(cmUser, cmPass)
        .param("filter", s"($filter)")
        .asString

      // Happens when trying to connect cloudera-manager with wrong username or password
      if (httpResponse.code == 401)
        throw new DexterAuthException("Failed Conneting to CM, Wrong Username or Password")
      // Happens out when Internal server error occur(e.g service monitor down,filter no acceptable).
      if (httpResponse.code == 500)
        throw new RuntimeException(httpResponse.body)

      val impalaQueries = this.createImpalaQueriesFromJson(httpResponse.body)

      impalaQueries
    }
    catch {
      case ex: Exception =>
        logger.error(ex.getMessage)
        logger.error(ex.getStackTrace.mkString("\n"))
        throw ex
    }
  }

  /**
    * Creates from the Json String Sequence Of Impala Queries
    *
    * @param queriesJson String represent Json of impala queries
    * @return seq of Impala queries objects.
    */
  private def createImpalaQueriesFromJson(queriesJson: String): Array[ImpalaQuery] = {
    val queries = decode[ImpalaQueries](queriesJson)
    if (queries.isRight)
      queries.right.get.queries
    else
      Array()
  }


}
