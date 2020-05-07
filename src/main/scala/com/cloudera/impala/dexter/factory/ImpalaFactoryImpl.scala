package com.cloudera.impala.dexter.factory

import com.cloudera.impala.dexter.connection.Connection
import com.cloudera.impala.dexter.exceptions.DexterUnsupportedDBType
import com.cloudera.impala.dexter.killer.{ImpalaKiller, ImpalaKillerImpl}
import com.cloudera.impala.dexter.receiver.{ImpalaReceiver, ImpalaReceiverImpl}
import com.typesafe.config.ConfigFactory

/**
  * Created by ShemTov on 10/08/2017.
  */
class ImpalaFactoryImpl extends ImpalaFactory {

  private val conf = ConfigFactory.load()


  def createImpalaKiller: ImpalaKiller = {
    val impalaKiller: ImpalaKiller = new ImpalaKillerImpl()
    impalaKiller
  }

  def createImpalaReceiver: ImpalaReceiver = {
    val impalaReceiver: ImpalaReceiver = new ImpalaReceiverImpl()
    impalaReceiver
  }

  def createConnection: Connection = {
    val dbType = conf.getString("DATABASE.TYPE")

    if (dbType == "JDBC")
    //TODO: Create JDBC Connection Impl
      Unit
    if (dbType == "FILE")
    //TODO: Create FILE Connection Impl
      Unit
    if (dbType.isEmpty)
      return null

    throw new DexterUnsupportedDBType(s"Database Type:$dbType is not supported")
  }

}
