package com.expenses.expensesentrance.common;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
public interface ContextMapper<C, I, O> {

    O map(C context, I input);

    default List<O> map(C context, Collection<? extends I> input) {
        Function<I, O> map = i -> map(context, i);
        return input.stream()
                .map(map)
                .collect(Collectors.toList());
    }
}
