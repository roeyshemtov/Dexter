package com.cloudera.impala.dexter.receiver

import com.cloudera.impala.dexter.factory.ImpalaFactory
import com.cloudera.impala.dexter.utill.ImpalaQuery

/**
  * Created by ShemTov on 05/08/2017.
  */
/**
  * Response to get impala queries.
  */
trait ImpalaReceiver {

  protected val impalaFactory: ImpalaFactory

  /**
    * This function gets filter, and return all impala queries
    * that match this filter.
    *
    * @param filter The filter that used on impala queries (e.g duration>1h)
    * @return Array of Impala queries that matched the filter.
    */
  def getImpalaQueries(filter: String): Array[ImpalaQuery]

}
