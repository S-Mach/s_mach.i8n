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
package s_mach.i18n.test

import java.util.Locale

import org.scalatest.{FlatSpec, Matchers}

class i18nTest extends FlatSpec with Matchers {
  val sym1 = 'hello
  val sym2 = 'hello_$name
  val sym3 = 'hello_$fname_$lname

  "i(String) for EN US" should "internationalize arguments correctly using JVM default locale (EN_US)" in {
    import s_mach.i18n.default._
    val name = "Lance"
    val qty = 10000.1

    i"hello $name test $qty" should equal("hello Lance test 10,000.1")
  }

  "i(String) for EN US" should "internationalize arguments correctly for custom locale" in {
    implicit val i18nTranslator = s_mach.i18n.i18nTranslator(Locale.FRENCH)
    import s_mach.i18n.default.Implicits._

    val name = "Lance"
    val qty = 10000.1

    // Note: not a space between '10' and '000' below
    i"hello $name test $qty" should equal("hello Lance test 10 000,1")
  }

}