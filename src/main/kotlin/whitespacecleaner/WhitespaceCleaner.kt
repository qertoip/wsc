package whitespacecleaner

import java.io.File
import java.nio.file.FileSystems

class WhitespaceCleaner(val path: String, val fileMask: String = "*") {

  val report = Report();
  val fileMatcher = FileSystems.getDefault().getPathMatcher("glob:$fileMask");

  fun run(): Report {
    val file = File(path)

    file.walkTopDown().
      onEnter { !isDotDir(it) }.
      filter { it.isFile && !it.name.startsWith(".") && fileMatcher.matches(it.toPath().fileName) }.
      forEach {
        val changed = FileWhitespaceCleaner(it).run();
        if (changed) {
          report.changed += 1
          println("[changed] ${it.path}")
        } else {
          report.skipped += 1
          println("[skipped] ${it.path}")
        }
      }
    return report;
  }

  private fun isDotDir(it: File) = it.name.startsWith(".") && it.name != "."

  class Report(var changed: Int = 0, var skipped: Int = 0) {
    public val processed: Int get() = changed + skipped;
  }

}
