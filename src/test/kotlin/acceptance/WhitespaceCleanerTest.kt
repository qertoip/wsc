package acceptance

import base.BaseTest
import org.testng.annotations.Test
import whitespacecleaner.WhitespaceCleaner
import java.io.File

class WhitespaceCleanerTest : BaseTest() {

  @Test
  fun skipsBinaryFile() {
    val wsc = WhitespaceCleaner(pathToBinaryFile())
    assertNotTouched(pathToBinaryFile(), { wsc.run() })
  }

  @Test
  fun skipsEmptyFile() {
    val wsc = WhitespaceCleaner(pathToEmptyFile())
    assertNotTouched(pathToEmptyFile(), { wsc.run() })
  }

  @Test
  fun skipsDotFile() {
    val wsc = WhitespaceCleaner(pathToDotFile())
    assertNotTouched(pathToDotFile(), { wsc.run() })
  }

  @Test
  fun cleansTextFile() {
    val wsc = WhitespaceCleaner(pathToTextFile())
    wsc.run()
    assertCleaned(pathToTextFile())
  }

  @Test
  fun respectsFileMask() {
    val wsc = WhitespaceCleaner(pathToFlatDirectory(), "*.kt")
    assertNotTouched(pathToJavaFile(), { wsc.run() })
    assertCleaned(pathToKtFile())
  }

  @Test
  fun cleansTextFilesRecursively() {
    val wsc = WhitespaceCleaner(pathToDirectory())

    wsc.run()

    assertCleaned(pathToDeepestTextFile())
    assertCleaned(pathToDeepTextFile())
    assertCleaned(pathToShallowTextFile())
  }

  fun pathToTextFile() = javaClass.getResource("/example1/HelloWorld.kt").path
  fun pathToBinaryFile() = javaClass.getResource("/example2/cparse.so").path
  fun pathToEmptyFile() = javaClass.getResource("/example3/empty").path
  fun pathToDotFile() = javaClass.getResource("/example4/.HelloWorld.kt").path
  fun pathToDirectory() = File(javaClass.getResource("/example10/HelloWorld.kt").path).parentFile.path
  fun pathToFlatDirectory() = File(javaClass.getResource("/example5/").path).parentFile.path
  fun pathToDeepestTextFile() = javaClass.getResource("/example10/subdir/subsubdir/HelloWorld.kt").path
  fun pathToDeepTextFile() = javaClass.getResource("/example10/subdir/HelloWorld.kt").path
  fun pathToShallowTextFile() = javaClass.getResource("/example10/HelloWorld.kt").path
  fun pathToKtFile() = javaClass.getResource("/example5/HelloWorld.kt").path
  fun pathToJavaFile() = javaClass.getResource("/example5/HelloWorld.java").path

}
