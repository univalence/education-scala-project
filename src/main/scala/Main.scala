@main // Common interface for parsers
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

// Declaration of some basic parsers
object Parser:
  def createParser[A](f: Input => ParseResult[A]): Parser[A] =
    input => f(input)

  /** parse an integer. */
  def int: Parser[Int] = createParser(???)

  /** parse exactly the string s */
  def string(s: String): Parser[String] = createParser(???)

  /** parse according to a regular expression */
  def regex(r: String): Parser[String] = createParser(???)

// Result of parse
enum ParseResult[+A]:
  case ParseFailure(onInput: Input) extends ParseResult[Nothing]
  case ParseSucceed(value: A, remainingInput: Input) extends ParseResult[A]

  def map[B](f: A => B): ParseResult[B] =


  def flatMap[B](f: A => ParseResult[B]): ParseResult[B] =

// Input of a parser
// * data: represents the input string
// * offset: is the pointer on the string, that the parser increases during its processing
case class Input(data: String, offset: Int = 0):
  def current(n: Int): String =
    data.slice(n, n+1)

  def next(n: Int): Input =
    Input(data, n)

  def remaining: String = ???
