package base

import java.io.File
import java.nio.file.Files
import org.testng.Assert.*

open class BaseTest {

  fun assertNotTouched(pathS: String, function: () -> Any) {
    val path = File(pathS).toPath()
    val t0 = Files.getLastModifiedTime(path)
    Thread.sleep(1000)
    function();
    val t1 = Files.getLastModifiedTime(path)
    assertEquals(t1, t0)
  }

  fun assertNotBroken(cleaned: String) {
    assertContains(cleaned, "/*\n ")
    assertContains(cleaned, "package example1")
    assertContains(cleaned, "\n\nfun")
    assertContains(cleaned, "fun main(args: Array<String>) {")
    assertContains(cleaned, """    System.out.println("Hello World!\n\r");""")
  }

  fun assertContains(s: String, what: String) {
    assertTrue(s.contains(what))
  }

  fun assertDoesNotContainCarriageReturn(s: String) {
    assertFalse(s.contains("\r"))
  }

  fun assertEndsWithExactlyOneNewLine(s: String) {
    assertTrue(s.endsWith("\n"))
    assertFalse(s.endsWith("\n\n"))
  }

  protected fun assertCleaned(path: String) {
    val cleaned = File(path).readText()
    assertNotBroken(cleaned)
    assertDoesNotContainCarriageReturn(cleaned)
    assertEndsWithExactlyOneNewLine(cleaned)
  }

}
