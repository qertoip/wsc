package whitespacecleaner

import whitespacecleaner.filetools.isBinFile
import java.io.File
import java.util.*

class FileWhitespaceCleaner(val file: File) {

  private var rows: List<String> = ArrayList()
  private var sizeBefore: Long = 0
  private var cleaned: String = ""
  private var sizeAfter: Long = 0

  /*
    Returns if changed
   */
  fun run(): Boolean {
    if (isBinFile(file)) return false
    readFile()
    clean()
    saveFile()
    return fileHasChanged()
  }

  private fun readFile() {
    sizeBefore = file.length();
    rows = file.readLines()
  }

  private fun clean() {
    val rowsWithNoTrailingSpace = rows.map { it.trimEnd() }
    val concatenated = rowsWithNoTrailingSpace.joinToString("\n")
    val withNoTrailingRows = concatenated.trimEnd()
    cleaned = withNoTrailingRows + "\n"
  }

  private fun saveFile() {
    file.writeText(cleaned, Charsets.UTF_8)
    sizeAfter = file.length()
  }

  private fun fileHasChanged() = sizeAfter != sizeBefore

}
