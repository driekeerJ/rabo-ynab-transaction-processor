package com.expenses.expensesentrance.common;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Mapper<I,O> {
    O map(I input);

    default List<O> map(Collection<? extends I> input) {
        return input.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
