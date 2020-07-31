package de.ungerts.mutiny;

import io.smallrye.mutiny.Uni;

import java.util.function.Function;

public class UniProducerUtils {

    private UniProducerUtils() {

    }

    public static <T,R> Uni<R> produceUniFromMapper(Uni<T> uni, Function<? super T, ? extends Uni<? extends R>> mapper) {
        return uni.onItem().produceUni(mapper);
    }
}
