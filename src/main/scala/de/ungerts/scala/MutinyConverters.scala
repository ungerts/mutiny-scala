package de.ungerts.scala

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

import scala.concurrent.Future
import scala.compat.java8.FutureConverters._

class MutinyConverters {

    implicit class FutureImprovements[T](val f: Future[T]) {

        def toUni: Uni[T] = Uni.createFrom().completionStage(f.toJava)

        def toMulti: Multi[T] = Multi.createFrom().completionStage(f.toJava)

    }

    implicit class UniImprovements[T](val f: Uni[T]) {

        def toFuture: Future[T] = f.subscribeAsCompletionStage().toScala

    }

}
