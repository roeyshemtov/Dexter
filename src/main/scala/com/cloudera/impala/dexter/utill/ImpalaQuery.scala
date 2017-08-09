package com.cloudera.impala.dexter.utill

import java.sql.Timestamp

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

/**
  * Created by ShemTov on 05/08/2017.
  */
case class ImpalaQuery(queryId: String, startTime: Timestamp, attributes: Attributes)

object ImpalaQuery {
  implicit val decoder: Decoder[ImpalaQuery] = deriveDecoder
}
