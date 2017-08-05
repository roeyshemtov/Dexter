package connection

import utill.ImpalaQuery

/**
  * Created by ShemTov on 05/08/2017.
  */

/**
  * Connection will use to save the Impala queries into the database
  * base on the configuration db.
  */
trait Connection {

  /**
    * Connecting to the data base, base on the configuration.
    */
  def connect()

  /**
    * Save the impala query into the database.
    *
    * @param impalaQuery object representing an Impala query.
    */
  def saveQuery(impalaQuery: ImpalaQuery)

  /**
    * Close the connection with the data base.
    */
  def close()

}
