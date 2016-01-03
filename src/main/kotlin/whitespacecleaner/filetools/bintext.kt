package whitespacecleaner.filetools

import java.io.File

public fun isBinFile(path: String): Boolean = !isTextFile(path)

public fun isBinFile(file: File): Boolean = !isTextFile(file)

public fun isTextFile(path: String): Boolean {
  return isTextFile(File(path))
}

public fun isTextFile(file: File): Boolean {
  val content = file.readBytes()
  return !content.isEmpty() && !hasZero(content) && vastMajorityIsPrintable(content)
}

private fun hasZero(content: ByteArray) = content.any{ it.equals(0) }

private fun vastMajorityIsPrintable(content: ByteArray) = content.count{ it > ASCII_31 } / content.size > 0.85

private val ASCII_31 = -95
private val ASCII_128 = 1
