package whitespacecleaner

fun main(args: Array<String>) {
  if (args.size > 0) {
    val path = args[0]
    val fileMask = if (args.size > 1) args[1] else "*";
    println("Cleaning text file(s) at $path matching $fileMask ...")
    val report = WhitespaceCleaner(path, fileMask).run();
    println("Files processed: ${report.processed},  cleaned: ${report.changed},  were clean: ${report.skipped}")
  } else {
    println("Usage:\nwsc path/to/dir/or/file   # see project github for details")
  }
}
