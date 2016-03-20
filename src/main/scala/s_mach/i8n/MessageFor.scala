package s_mach.i8n

import java.util.Locale

import s_mach.i8n.impl.MessageForOps

trait MessageFor {
  def locale: Locale
  def translate(parts: Seq[String], args: I8NString*) : I8NString
  def translate(value: String) : I8NString

  type MessageKey
  def mkKey(parts: Seq[String]) : MessageKey
  def mkKey(value: String) : MessageKey
  def findByKey(key: MessageKey, args: I8NString*) : I8NString

  final def findByParts(parts: Seq[String], args: I8NString*) : String =
    findByKey(mkKey(parts),args:_*)

  final def findByValue(value: String, args: I8NString*) : String =
    findByKey(mkKey(value),args:_*)
}

object MessageFor {
  def noop(locale: Locale) : MessageFor = MessageForOps.MessageFor_Noop(locale)
  val defaultMessageFor = MessageFor.noop(Locale.getDefault)
 object Implicits {
    implicit val defaultMessageFor = MessageFor.defaultMessageFor
  }
}