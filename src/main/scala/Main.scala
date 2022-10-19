import ParseResult.*
// Common interface for parsers
trait Parser[A]:
  /** parse with this and then parse with pb. */
  def ~[B](pb: => Parser[B]): Parser[(A, B)] = ???
  /** parse with this, or parse with pb if this fails. */
  def |[B](pb: Parser[B]): Parser[Either[A, B]] = ???
  /** try to parse with this. It does not fail if the parsing did not work. */
  def ? : Parser[Option[A]] = ???
  /** use this to parse multiple times, until it does not apply. */
  def repeat: Parser[List[A]] = ???
  /** convert the value output by the parser. */
  def map[B](f: A => B): Parser[B] = ???
  def flatMap[B](f: A => Parser[B]): Parser[B] = ???

  final def parse(s: String): ParseResult[A] = parse(Input(s))
  def parse(input: Input): ParseResult[A]

// Input of a parser
// * data: represents the input string
// * offset: is the pointer on the string, that the parser increases during its processing
case class Input(data: String, offset: Int = 0):
  def current(n: Int): String = data.substring(offset,offset+n)
  def next(n: Int): Input = copy(offset = offset+n)
  def remaining: String = data.substring(offset)

// parser.string("A") tild parser.string("BCD")
// Input("ABCD", 0)
// first parser => Input("AABCD", 1)
// deuxième parser => input.current("BC".length)

object Parser:
  def createParser[A](f: Input => ParseResult[A]): Parser[A] = input => f(input)
  /** parse an integer. */
  def Parser_Int(input: Input): ParseResult[Int]= {
    strInt(input.remaining) match {
      case Some(value) => {
        if (value.length == input.data.length)
          ParseSucceed(value.toInt, input.copy(offset = input.data.length-1))
        else
          ParseSucceed(value.toInt, input.copy(offset = value.length))
      }
      case None => ParseFailure(input)
    }
  }
  def strInt(string:String) : Option[String] = {
    string.headOption.flatMap(firstCharacter => firstCharacter match {
      case '-' => strInt(string.tail).map("-"+_)
      case _ => string.takeWhile(_.isDigit) match {
        case "" => None
        case v => Some(v)
      }
    } )
  }
  def int: Parser[Int] = createParser(Parser_Int)
  /** parse exactly the string s */
  def string(s: String): Parser[String] = createParser(???)
  /** parse according to a regular expression */
  def regex(r: String): Parser[String] = createParser(???)

// Result of parse
enum ParseResult[+A]:
  case ParseFailure(onInput: Input) extends ParseResult[Nothing]
  case ParseSucceed(value: A, remainingInput: Input) extends ParseResult[A]

  def map[B](f: A => B): ParseResult[B] = ???
  def flatMap[B](f: A => ParseResult[B]): ParseResult[B] = ???

@main
def _01_main(): Unit = println(Parser.int.parse("12-a"))
