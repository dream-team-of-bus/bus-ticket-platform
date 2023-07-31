package com.bus.ticket.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

/**
 * 
 * @author honglixiang
 *
 */
public class StreamUtils {

    public static <T> List<T> filter(Collection<T> col, Predicate<T> p) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().filter(p).collect(Collectors.toList());
    }

    public static <T> Integer filterCount(Collection<T> col, Predicate<T> p) {
        if (CollectionUtils.isEmpty(col)) {
            return 0;
        }
        return (int)col.stream().filter(p).count();
    }

    public static <T, R> List<R> toList(Collection<T> col, Predicate<T> p, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().filter(p).map(mapper).collect(Collectors.toList());
    }

    public static <T, R> List<R> toList(Collection<T> col, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, R> List<R> toListFilterNull(Collection<T> col, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyList();
        }
        return col.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T, R> Set<R> toSet(Collection<T> col, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptySet();
        }
        return col.stream().map(mapper).collect(Collectors.toSet());
    }

    public static <T, K> Map<K, T> toMap(Collection<T> col, Function<T, K> keyMapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.toMap(keyMapper, t -> t));
    }

    public static <T, K, V> Map<K, V> toMap(Collection<T> col, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public static <T> Integer sumInteger(Collection<T> col, ToIntFunction<T> mapper) {
        return col.stream().mapToInt(mapper).sum();
    }

    public static <T> Long sumLong(Collection<T> col, ToLongFunction<T> mapper) {
        return col.stream().mapToLong(mapper).sum();
    }

    public static <T> Double sumDouble(Collection<T> col, ToDoubleFunction<T> mapper) {
        return col.stream().mapToDouble(mapper).sum();
    }

    public static <T> Long sumLong(Collection<T> col, Predicate<T> p, ToLongFunction<T> mapper) {
        return col.stream().filter(p).mapToLong(mapper).sum();
    }

    public static <T, K> Map<K, List<T>> group(Collection<T> col, Function<T, K> function) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.groupingBy(function));
    }

    public static <T, K> Map<K, Long> groupCount(Collection<T> col, Function<T, K> function) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().collect(Collectors.groupingBy(function, Collectors.counting()));
    }

    public static <T, K> Map<K, List<T>> filterGroup(Collection<T> col, Predicate<T> p, Function<T, K> function) {
        if (CollectionUtils.isEmpty(col)) {
            return Collections.emptyMap();
        }
        return col.stream().filter(p).collect(Collectors.groupingBy(function));
    }

    public static <T> T getFirst(T[] col, Predicate<? super T> p) {
        if (col == null || col.length == 0) {
            return null;
        }
        for (T c : col) {
            if (p.test(c)) {
                return c;
            }
        }
        return null;
    }

    public static <T> T getFirst(Collection<T> col, Predicate<? super T> p) {
        if (CollectionUtils.isEmpty(col)) {
            return null;
        }
        for (T c : col) {
            if (p.test(c)) {
                return c;
            }
        }
        return null;
    }
}
