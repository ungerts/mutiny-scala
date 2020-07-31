# mutiny-scala

Converts [Scala](https://www.scala-lang.org/) ```Future``` to [Mutiny](https://smallrye.io/smallrye-mutiny/) ```Uni``` and ```Multi```.

## Example

```scala
package de.ungerts.scala.test

import io.smallrye.mutiny.Uni
import de.ungerts.scala.MutinyConverters._

import scala.concurrent.ExecutionContext

object MutinyScalaExample extends App {
    implicit val ec: ExecutionContext = ExecutionContext.global
    val uni1 = Uni.createFrom().item("Hello")
    val uni2 = Uni.createFrom().item("World")
    val resultFuture = for {
       hello <- uni1.toFuture
       world <- uni2.toFuture
    } yield {
        s"$hello $world!"
    }
    println(resultFuture.toUni.await().indefinitely())
}
```