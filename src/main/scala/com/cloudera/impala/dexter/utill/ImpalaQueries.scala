package com.cloudera.impala.dexter.utill

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

/**
  * Created by ShemTov on 09/08/2017.
  */
case class ImpalaQueries(queries: Seq[ImpalaQuery]) extends AnyVal

object ImpalaQueries {
  implicit val decoder: Decoder[ImpalaQueries] = deriveDecoder
}
