# BCrypt Benchmark

This is a simple benchmarking project that runs [jbCrypt](http://www.mindrot.org/projects/jBCrypt) through [JMH](http://openjdk.java.net/projects/code-tools/jmh/) microbenchmark to see how long bcrypt takes to hash a password.

It comes packaged with the [jBCrypt 0.4 source](http://www.mindrot.org/projects/jBCrypt/news/rel04.html) -- unfortunately, because there is no Maven package, jBCrypt can't be included as a library dependency.  If you have questions about the implementation, you can easily download and pop in the source directly from the original zip / tar.gz file.

## Prerequisites

Download [SBT](http://www.scala-sbt.org/download.html).  Homebrew works great if you're on Mac.

## How do I run it?

```
cd bcrypt-benchmark
sbt benchmark
```

## Why benchmark bcrypt?

Bcrypt is an adaptive hashing algorithm.  Adaptive, in this case, means that you can adapt bcrypt with a custom work factor.  By benchmarking,  you can determine your work factor.

## What's a work factor?

A work factor increases the number of iterations that bcrypt runs through by powers of two.  For example, a work factor of 10 means 2**10 iterations.  Choosing a work factor depends on your threat model: too cheap and your passwords can be brute forced, too expensive and it can put [unnecessary load on the system](http://wildlyinaccurate.com/bcrypt-choosing-a-work-factor/).

When bcrypt was first designed in 1999, it was created using a work factor of 6, and a password would be hashed in roughly ~0.5 to 1 second.  Now, in 2015, a work factor of 10 is considered standard, and some people advocate [a work factor of 12](http://security.stackexchange.com/a/83382/6714).

Bcrypt was designed with the assumption that hardware always gets faster, and that developers will up the work factor every few years.  So, if you run this program, you can see and adjust how long password take to get hashed, and adjust the work factor so the hashing time is constant.

## Community Results

If you are community minded, you can edit [results.md](results.md) and add your benchmarks to the end of the list.

## Questions

Email <will.sargent@gmail.com>.
