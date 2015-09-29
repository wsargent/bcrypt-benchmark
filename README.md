# BCrypt Benchmark

This is a simple benchmarking project that runs [jbCrypt](http://www.mindrot.org/projects/jBCrypt) through [JMH](http://openjdk.java.net/projects/code-tools/jmh/) microbenchmark to see how long BCrypt takes to hash a password.

It comes packaged with the [jBCrypt 0.4 source](http://www.mindrot.org/projects/jBCrypt/news/rel04.html) -- unfortunately, because there is no Maven package, jBCrypt can't be included as a library dependency.  If you have questions about the implementation, you can easily download and pop in the source directly from the original zip / tar.gz file.

## Prerequisites

Download [SBT](http://www.scala-sbt.org/download.html).  Homebrew works great if you're on Mac.

## How do I run it?

```
cd bcrypt-benchmark
sbt benchmark
```

## Why benchmark BCrypt?

BCrypt is an adaptive hashing algorithm.  Adaptive, in this case, means that you can adapt BCrypt with a custom work factor.  By benchmarking,  you can determine your work factor.

## What's a work factor?

A work factor increases the number of iterations that bcrypt runs through by powers of two.  For example, a work factor of 10 means 2**10 iterations.  Choosing a work factor depends on your threat model: too cheap and your passwords can be brute forced, too expensive and it can put [unnecessary load on the system](http://wildlyinaccurate.com/bcrypt-choosing-a-work-factor/).

When BCrypt was first designed in 1999, it was created using a work factor of 6, and a password would be hashed in roughly ~0.5 to 1 second.  Now, in 2015, a work factor of 10 is considered standard, and some people advocate [a work factor of 12](http://security.stackexchange.com/a/83382/6714).

BCrypt was designed with the assumption that hardware always gets faster, and that developers will increase the work factor every few years.  By running this program, you can see how long a password take to get hashed, and adjust the work factor so the hashing time is constant.

## My work factor is too low!  Now what???

Ideally, you have your work factor set in a configuration file. In addition, you should have a hook in your authentication logic.  

When a user logs in successfully:

* Check the user's password hash vs the configured work factor.  
* If the configured work factor is higher than password hash, then rehash.

How do you figure out what the hash's work factor is?  It's part of the string: all BCrypt hashes start with "$<version>$<work factor>", so "$2a$10" means a work factor of 10.

Once you have these two pieces, then you can bounce servers with the new configuration, and passwords will be seamlessly upgraded.  If you want to ensure everyone is using a minimum work factor, then look for weak password hashes, expire their credentials, and notify the users that they must reset their passwords.

## Does BCrypt mean I have strong passwords?

No.  Using BCrypt means that in the event that a hacker gets access to user credentials, cracking the entire database is expensive.  

Crackers will still try easily guessable passwords like "password1" though, and the technology for guessing what humans pick as passwords has [improved dramatically](https://labs.mwrinfosecurity.com/blog/2015/09/25/a-practical-guide-to-cracking-password-hashes/).  

The bottom line is that your users will still have to pick strong passwords even if you use BCrypt.  You should check for guessable passwords using a password strength checker, and run a password cracker periodically to see users have guessable passwords.

## Community Results

If you are community minded, you can edit [results.md](results.md) and add your benchmarks to the end of the list.

## Questions

Email <will.sargent@gmail.com>.
