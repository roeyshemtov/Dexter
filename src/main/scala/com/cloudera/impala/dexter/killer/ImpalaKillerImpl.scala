package com.cloudera.impala.dexter.killer

import com.cloudera.impala.dexter.exceptions.ImpalaQueryKillException
import com.cloudera.impala.dexter.utill.ImpalaQuery
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger

import scalaj.http.Http

/**
  * Created by ShemTov on 10/08/2017.
  */
class ImpalaKillerImpl extends ImpalaKiller {

  private val conf = ConfigFactory.load()
  private val logger = Logger(this.getClass.getSimpleName)
  private val cmUrl = s"http://${conf.getString("CM.HOST")}:${conf.getString("CM.PORT")}" +
    s"/api/v${conf.getString("CM.API.VERSION")}" +
    s"/clusters/${conf.getString("CLUSTER.NAME")}" +
    s"/services/${conf.getString("SERVICES.IMPALA.NAME")}" +
    s"/impalaQueries"
  private val cmUser = conf.getString("CM.USER")
  private val cmPass = conf.getString("CM.PASSWORD")

  def kill(impalaQuery: ImpalaQuery): Unit = {

    try {
      val cmKillUrl = s"$cmUrl/${impalaQuery.queryId}/cancel"
      val httpResponse = Http(cmKillUrl)
        .auth(cmUser, cmPass)
        .header("Content-Type", "application/json")
        .postForm
        .asString

      if (httpResponse.code == 200)
        logger.info(s"Successfully killed Impala Query: ${impalaQuery.queryId}")
      else
        throw new ImpalaQueryKillException(s"Failed to kill Impala Query:${impalaQuery.queryId}, reason:${httpResponse.body}")
    }
    catch {
      case ex: Exception =>
        logger.error(ex.getMessage)
        logger.error(ex.getStackTrace.mkString("\n"))
        throw ex
    }

  }
}
