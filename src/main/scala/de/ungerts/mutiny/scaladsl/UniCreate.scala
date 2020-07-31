package de.ungerts.mutiny.scaladsl

import scala.concurrent.Future
import de.ungerts.mutiny.scala.MutinyConverters._


class UniCreate(val javaUniCreate: io.smallrye.mutiny.groups.UniCreate) {

    def item[T](item: T): Uni[T] = {
        new Uni(javaUniCreate.item(item))
    }

    def fromJavaUni[T](javaUni: io.smallrye.mutiny.Uni[T]) : Uni[T] = new Uni[T](javaUni)

    def fromFuture[T](future: Future[T]) : Uni[T] = new Uni[T](future.toUni)
}

