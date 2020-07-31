# mutiny-scala

Converts [Scala](https://www.scala-lang.org/) ```Future``` to [Mutiny](https://smallrye.io/smallrye-mutiny/) ```Uni``` and ```Multi```.

## Example

Using Futures and Mutiny

```scala
package de.ungerts.scala.test

import io.smallrye.mutiny.Uni
import de.ungerts.mutiny.scala.MutinyConverters._

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

Using Scala for comprehension

```scala
package de.ungerts.scala.test

import de.ungerts.mutiny.scaladsl.Uni

object MutinyScaladslExample extends App {

    val helloUni = Uni.createFrom().item("Hello")
    val worldUni = Uni.createFrom().item("World")

    val resultUni = for {
        hello <- helloUni
        world <- worldUni
    } yield {
        s"$hello $world!"
    }
    println(resultUni.javaUni.await().indefinitely())

}
```