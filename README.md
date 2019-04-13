# Test AOT compilation of Instaparse

WARNING: Never got it running with the recent Clojure 1.10. It does
seem as if it may be a dead end without Cognitec behind "native
compilation".

See also blog
[post](https://www.innoq.com/en/blog/native-clojure-and-graalvm/).

    lein uberjar
    time java -jar ./target/uberjar/instaparse-demo-0.1.0-SNAPSHOT-standalone.jar aaabbb

This takes more than a second on my machine ...

Download  [GraalVM](https://github.com/oracle/graal/releases) tarball,
unpack it, and run the native  compiler from the bin/ subdirectory. It
seems to work without being on the $PATH too:

    native-image --report-unsupported-elements-at-runtime -jar ./target/uberjar/instaparse-demo-0.1.0-SNAPSHOT-standalone.jar
    time ./instaparse-demo-0.1.0-SNAPSHOT-standalone aaabbb

This  took 11ms  with  GraalVM  RC7. Hm,  with  GraalVM  RC10 it  runs
21ms. The compilation is lengthy though.

Without

    --report-unsupported-elements-at-runtime

this happens:

    error: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException:
    Unsupported method java.lang.ClassLoader.defineClass(String, byte[], int, int)
    is reachable:
    The declaring class of this element has been substituted, but this
    element is not present in the substitution class To diagnose the
    issue, you can add the option
    --report-unsupported-elements-at-runtime. The unsupported element is
    then reported at run time when it is accessed the first time.

Trace:

            at parsing clojure.lang.DynamicClassLoader.defineClass(DynamicClassLoader.java:46)
    Call path from entry point to clojure.lang.DynamicClassLoader.defineClass(String, byte[], Object):
            at clojure.lang.DynamicClassLoader.defineClass(DynamicClassLoader.java:45)
            at clojure.core$get_proxy_class.invokeStatic(core_proxy.clj:268)
            at clojure.core$get_proxy_class.doInvoke(core_proxy.clj:256)
            at clojure.lang.RestFn.applyTo(RestFn.java:137)
            at instaparse.demo.main(Unknown Source)
            at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:164)
            at com.oracle.svm.core.code.IsolateEnterStub.JavaMainWrapper_run_5087f5482cc9a6abc971913ece43acb471d2631b(generated:0)

## License

Copyright © 2018 Alexei Matveev <alexei.matveev@gmail.com>

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
