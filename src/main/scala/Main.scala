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

@main
def _01_main(): Unit = println(Input("123456789",2).remaining)