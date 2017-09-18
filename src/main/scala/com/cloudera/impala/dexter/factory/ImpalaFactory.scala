package com.cloudera.impala.dexter.factory

import com.cloudera.impala.dexter.connection.Connection
import com.cloudera.impala.dexter.killer.ImpalaKiller
import com.cloudera.impala.dexter.receiver.ImpalaReceiver

/**
  * Created by ShemTov on 06/08/2017.
  */

/**
  * Class response create Impls.
  */
trait ImpalaFactory {

  /**
    * Creates ImpalaKiller Implementation.
    *
    * @return ImpalaKiller Object
    */
  def createImpalaKiller: ImpalaKiller

  /**
    * Creates Impala Receiver Implementation.
    *
    * @return ImpalaReceiver Object
    */
  def createImpalaReceiver: ImpalaReceiver

  /**
    * Creates Connection Implementation, base on configuration.
    *
    * @return Connection Object
    */
  def createConnection: Connection


}
