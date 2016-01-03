# Whitespace Cleaner

The tool forces the following whitespace policy on text files:

* Unix line endings (\n)
* No trailing whitespace
* Single \n at the end of file

The text files are assumed to be UTF-8.

# Usage

```
wsc path-to-file-or-dir [filemask]
```

Example 1: clean the file:
```
wsc /path/to/file.java
```

Example 2: clean all *.java files in a directory (recursively):
```
wsc /path/to/project *.java
```

Example 3: clean all text files in a directory (recursively):
```
wsc /path/to/project    # Careful! This can break you binary files. Use your SCM to review changes.  
```
The tool aims to skip binary files based on a simple heuristic.
Once in a while it can be wrong so please be careful and always review the diff. 
