package de.ungerts.mutiny.scaladsl

import java.util.function.Function

import de.ungerts.mutiny.UniProducerUtils
import de.ungerts.mutiny.scala.MutinyConverters._
import io.smallrye.mutiny

class Uni[T] (val javaUni: io.smallrye.mutiny.Uni[T]) {

    def flatMap[S](f: T => Uni[S]): Uni[S] = {
        val mapperFunction: Function[T, mutiny.Uni[S]] = (t: T) => f(t).javaUni
        UniProducerUtils.produceUniFromMapper[T,S](javaUni, mapperFunction).toScalaUni
    }

    def map[S](f: T => S): Uni[S] = {
        val result = javaUni.onItem().apply[S]((t: T) => f(t))
        result.toScalaUni
    }

}

object Uni {

    def createFrom(): UniCreate = new UniCreate(mutiny.Uni.createFrom())

}

