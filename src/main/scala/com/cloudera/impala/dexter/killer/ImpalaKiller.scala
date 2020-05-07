package com.cloudera.impala.dexter.killer

import com.cloudera.impala.dexter.exceptions.ImpalaQueryKillException
import com.cloudera.impala.dexter.utill.ImpalaQuery

/**
  * Created by ShemTov on 05/08/2017.
  */
/**
  * Response to kill impala query.
  */
trait ImpalaKiller {

  /**
    * Kill an impala query.
    *
    * @throws ImpalaQueryKillException when kill impala query is failed.
    * @param impalaQuery Object representing impala query
    */
  def kill(impalaQuery: ImpalaQuery)

}
