package com.cloudera.impala.dexter

import java.sql.Timestamp
import cats.syntax.either._
import io.circe.Decoder

/**
  * Created by ShemTov on 09/08/2017.
  */
package object utill {
  /**
    * A Decoder for timestamp object.
    */
  implicit val timestampDecoder: Decoder[Timestamp] =
    Decoder[String].emap { str =>
      val cleaned = str.replaceAll("T", " ").replaceAll("Z", "")

      Either
        .catchNonFatal(Timestamp valueOf cleaned)
        .leftMap(_ => "Timestamp")
    }
}
