package de.ungerts.mutiny.scala

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni

import scala.concurrent.Future
import scala.compat.java8.FutureConverters._

object MutinyConverters {

    implicit class FutureImprovements[T](val f: Future[T]) {

        def toUni: Uni[T] = Uni.createFrom().completionStage(f.toJava)

        def toMulti: Multi[T] = Multi.createFrom().completionStage(f.toJava)

    }

    implicit class UniImprovements[T](val f: Uni[T]) {

        def toFuture: Future[T] = f.subscribeAsCompletionStage().toScala

        def toScalaUni: de.ungerts.mutiny.scaladsl.Uni[T] = new de.ungerts.mutiny.scaladsl.Uni(f)

    }

}
