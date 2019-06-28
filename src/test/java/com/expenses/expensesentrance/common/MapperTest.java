package com.expenses.expensesentrance.common;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class MapperTest {
    private final Mapper<Object, Object> mapper = input -> input;

    @Test
    public void testMapAll() {
        final List<Object> input = asList(new Object(), new Object(), new Object());
        final List<Object> output = mapper.map(input);
        assertThat(input).isEqualTo(output);
    }

}
