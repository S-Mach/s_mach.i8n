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
import s_mach.i18n._
import s_mach.i18n.messages._

class I18NTest extends FlatSpec with Matchers {


  "I18N[Number]" should "format numbers correctly using the US locale" in {
    implicit val cfg = I18NConfig(UTF8Messages(Locale.ENGLISH))

    1000.i18n should equal(I18NString("1,000"))
    (-1000).i18n should equal(I18NString("-1,000"))
    127.toByte.i18n should equal(I18NString("127"))
    (-127).toByte.i18n should equal(I18NString("-127"))
    32767.toShort.i18n should equal(I18NString("32,767"))
    (-32767).toShort.i18n should equal(I18NString("-32,767"))
    999999.i18n should equal(I18NString("999,999")) // Int
    (-999999).i18n should equal(I18NString("-999,999")) // Int
    10000000000l.i18n should equal(I18NString("10,000,000,000")) // Long
    (-10000000000l).i18n should equal(I18NString("-10,000,000,000")) // Long
    10000.1f.i18n should equal(I18NString("10,000.1")) // float
    (-10000.1f).i18n should equal(I18NString("-10,000.1")) // float
    10000.1.i18n should equal(I18NString("10,000.1")) // double
    (-10000.1).i18n should equal(I18NString("-10,000.1"))
    BigInt("10000000000").i18n should equal(I18NString("10,000,000,000"))
    BigInt("-10000000000").i18n should equal(I18NString("-10,000,000,000"))
    BigDecimal("10000000000.1").i18n should equal(I18NString("10,000,000,000.1"))
    BigDecimal("-10000000000.1").i18n should equal(I18NString("-10,000,000,000.1"))
  }

  "I18N[Number]" should "format numbers correctly using the FRENCH locale" in {
    implicit val cfg = I18NConfig(UTF8Messages(Locale.FRENCH))

    1000.i18n should equal(I18NString("1 000"))
    (-1000).i18n should equal(I18NString("-1 000"))
    127.toByte.i18n should equal(I18NString("127"))
    (-127).toByte.i18n should equal(I18NString("-127"))
    32767.toShort.i18n should equal(I18NString("32 767"))
    (-32767).toShort.i18n should equal(I18NString("-32 767"))
    999999.i18n should equal(I18NString("999 999")) // Int
    (-999999).i18n should equal(I18NString("-999 999")) // Int
    10000000000l.i18n should equal(I18NString("10 000 000 000")) // Long
    (-10000000000l).i18n should equal(I18NString("-10 000 000 000")) // Long
    10000.1f.i18n should equal(I18NString("10 000,1")) // float
    (-10000.1f).i18n should equal(I18NString("-10 000,1")) // float
    10000.1.i18n should equal(I18NString("10 000,1")) // double
    (-10000.1).i18n should equal(I18NString("-10 000,1"))
    BigInt("10000000000").i18n should equal(I18NString("10 000 000 000"))
    BigInt("-10000000000").i18n should equal(I18NString("-10 000 000 000"))
    BigDecimal("10000000000.1").i18n should equal(I18NString("10 000 000 000,1"))
    BigDecimal("-10000000000.1").i18n should equal(I18NString("-10 000 000 000,1"))
  }

  "I18N[I18NString]" should "return the same value" in {
    implicit val cfg = I18NConfig(UTF8Messages(Locale.ENGLISH))

    I18NString("test").asI18N should equal(I18NString("test"))
  }

  "I18N[Boolean]" should "format boolean values correctly using the US locale messages" in {
    implicit val cfg = I18NConfig(UTF8Messages(Locale.ENGLISH))

    true.i18n should equal("true")
    false.i18n should equal("false")
  }

  "I18N[Boolean]" should "format boolean values correctly using the FRENCH locale messages" in {
    implicit val cfg = I18NConfig(UTF8Messages(Locale.FRENCH))

    true.i18n should equal("vrai")
    false.i18n should equal("faux")
  }

  "i18nString" should "ensure all interpolated arguments are convertable to I18NString" in {
    val qty = 10000.1
    val name = "Gary"
    val b = false

    {
      implicit val cfg = I18NConfig(UTF8Messages(Locale.ENGLISH))
      i18n"$b test $qty ${name.asI18N}" should equal("false test 10,000.1 Gary")
    }
    {
      implicit val cfg = I18NConfig(UTF8Messages(Locale.FRENCH))

      i18n"$b test $qty ${name.asI18N}" should equal("faux test 10 000,1 Gary")
    }

  }

  // todo: test doesn't work properly
//  "i18nString" should "fail to compile if any interpolated argument is not convertable to I18NString" in {
//    val qty = 10000.1
//    val name = "Gary"
//    val c = 'a'
//    val b = false
//
//    // Name is String which by default can't convert automatically
//    """i18n"$name"""" shouldNot compile
//    """i18n"${name.asI18N}"""" should compile
//    // c is Char which by default can't convert automatically
//    """i18n"$b test $qty $c"""" shouldNot compile
//    """import s_mach.i18n._; i18n"$b test $qty ${c.asI18N}"""" should compile
//  }

  "StdI18N.default.toString" should "return DefaultStdI18N" in {
    StdI18N.default.toString should equal("DefaultStdI18N")
  }

  "Char.toI18N" should "return I18NString" in {
    'a'.toI18N should equal(I18NString("a"))
  }

  "String.asI18N" should "return I18NString" in {
    "abc".asI18N should equal(I18NString("abc"))
  }
}
