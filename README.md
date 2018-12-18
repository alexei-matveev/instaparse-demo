# Test AOT compilation of Instaparse

    lein uberjar
    time java -jar ./target/uberjar/parse-demo-0.1.0-SNAPSHOT-standalone.jar aaabbb

This takes more than a second on my machine ...

    native-image --report-unsupported-elements-at-runtime -jar ./target/uberjar/parse-demo-0.1.0-SNAPSHOT-standalone.jar
    time ./parse-demo-0.1.0-SNAPSHOT-standalone  aaabbb

This takes 10ms. The compilation is lengthy though.

## License

Copyright © 2018 Alexei Matveev <alexei.matveev@gmail.com>

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
