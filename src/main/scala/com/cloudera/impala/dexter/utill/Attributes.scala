package com.cloudera.impala.dexter.utill

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

/**
  * Created by ShemTov on 09/08/2017.
  */
case class Attributes(planning_wait_time_percentagte: Option[Int]) extends AnyVal

object Attributes {
  implicit val decoder: Decoder[Attributes] = deriveDecoder
}
