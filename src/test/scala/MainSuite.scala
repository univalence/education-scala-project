// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MainSuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

  test("Inputs remaining"){
    val input = Input("123456789",3)
    val remaining = "456789"
    assertEquals(input.remaining,remaining)
  }
  test("Inputs current") {
    val input = Input("123456789", 3)
    val current = "456"
    assertEquals(input.current(3), current)
  }
  test("Inputs next") {
    val input = Input("123456789", 3)
    val next = Input("123456789", 5)
    assertEquals(input.next(2), next)
  }
}
