## kscript - Having fun with Kotlin scripting


Easy-to-use scripting support for [Kotlin](https://kotlinlang.org/) on *nix-based systems.

Kotlin has a limited support for scripting already but it's not (yet) feature-rich enough to be fun.

In particular this wrapper around `kotlinc-jvm -script` adds
* Complied script caching (using md checksums)
* Automatic depedendency resolution via gradle-style resource locators

## Installation

Simply download [kscript](kscript)  to your `~/bin` with:
```bash
curl -s https://raw.githubusercontent.com/holgerbrandl/kscript/master/kscript > ~/bin/kscript && chmod u+x ~/bin/kscript
```

`kotlinc-jvm` is required to be in your `PATH`. It will be once you have [installed Kotlin](https://kotlinlang.org/docs/tutorials/command-line.html). Also `ruby` is required for the dependency resolution bits.

## Usage

To use `kscript` just specify it in the shebang line of your Kotlin scripts:

```kotlin
#!/usr/bin/env kscript ""

println("Hello from Kotlin!")
for (arg in args) {
    println("arg: $arg")
}
```
Since no additional dependencies are required in this example, the kscript dependencies argument is empty (but still required).

To specify dependencies simply use gradle-style locators. Multiple dependencies need to be separated by comma. Here's an example using [docopt](https://github.com/docopt/docopt.java) and [log4j](http://logging.apache.org/log4j/2.x/)

```kotlin
#!/usr/bin/env kscript org.docopt:docopt:0.6.0-SNAPSHOT,log4j:log4j:1.2.14

import org.docopt.Docopt
import java.util.*


val usage = """
Usage: cooltool <command> [options] [<input_file>]

Supported commands are
  submit    Submits a job to the underlying queuing system and adds it to the list
  add       Extracts job-ids from stdin and adds them to the list
  wait      Wait for a list of jobs to finish

If no args are provided cooltool is likely to do nothing
"""

val doArgs = Docopt(usage).parse(args.toList())

println("parsed args are: \n" + doArgs)

println("Hello from Kotlin!")
for (arg in args) {
    println("arg: $arg")
}
```


References
============

`kscript` is inspired (and bluntly borrows `mvncp` for dependency resolution) by [kotlin-script](https://github.com/andrewoma/kotlin-script) which is another great way to do scripting in Kotlin. `kotlin-script` has more options compared to `kscript`, but the latter is conceptually cleaner (no code wrapping) and more simplistic.


`kscript` works better with Intellij as IDE, because extended multi-line shebang-headers are not (yet?) supported by Intellij Kotlin plugin (even if the kotlin-script parser seems to be able to handle them).  However in order to use `mvncp` for dependency resolution, `kotlin script` relies on such mutli-line shebang headers (see [here](https://github.com/andrewoma/kotlin-script#mvncp)). In contrast, since `kscript` just works with just a standard shebang line, code parsing works very well in Intellij.





Isssues
=======

Feel welcome to submit tickets.

In general, there are several Kotlin language level issues associated with scripting. In particular
 * [Allow running class files with '.class' extension in 'kotlin' script](https://youtrack.jetbrains.com/issue/KT-11164)
 * [Add menu entry to create new kotlin .kts scripts](https://youtrack.jetbrains.com/issue/KT-10935)
* [Better support for compiled scripts](https://youtrack.jetbrains.com/issue/KT-10533)  (see comments section)


So if you like to make scripting with Kotlin even more awesome consider to upvote these issues in JetBrains' isssue tracker.




