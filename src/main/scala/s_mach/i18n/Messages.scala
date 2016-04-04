/*
                    ,i::,
               :;;;;;;;
              ;:,,::;.
            1ft1;::;1tL
              t1;::;1,
               :;::;               _____       __  ___              __
          fCLff ;:: tfLLC         / ___/      /  |/  /____ _ _____ / /_
         CLft11 :,, i1tffLi       \__ \ ____ / /|_/ // __ `// ___// __ \
         1t1i   .;;   .1tf       ___/ //___// /  / // /_/ // /__ / / / /
       CLt1i    :,:    .1tfL.   /____/     /_/  /_/ \__,_/ \___//_/ /_/
       Lft1,:;:       , 1tfL:
       ;it1i ,,,:::;;;::1tti      s_mach.i18n
         .t1i .,::;;; ;1tt        Copyright (c) 2016 S-Mach, Inc.
         Lft11ii;::;ii1tfL:       Author: lance.gatlin@gmail.com
          .L1 1tt1ttt,,Li
            ...1LLLL...
*/
package s_mach.i18n

import s_mach.i18n.impl.DefaultMessages

trait Messages {
  trait Lookup[A] {
    def get(key: String): Option[A]
    def apply(key: String): A
  }

  def keys: Iterable[String]
  def contains(key: String): Boolean

  def literals : Lookup[String]
  def interpolations : Lookup[Seq[Interpolation]]
  def choices : Lookup[BigDecimal => String]
}

object Messages {
  val empty = apply()

  def apply(
    literals: Map[String,String] = Map.empty,
    interpolations: Map[String,Seq[Interpolation]] = Map.empty,
    choices: Map[String,BigDecimal => String] = Map.empty
  ) : Messages =
    new DefaultMessages(literals,interpolations,choices)
}
