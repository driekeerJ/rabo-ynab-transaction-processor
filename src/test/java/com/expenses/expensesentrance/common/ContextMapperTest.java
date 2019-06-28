package com.expenses.expensesentrance.common;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class ContextMapperTest {
    private static final String CONTEXT = "CONTEXT";

    private static final String INPUT = "INPUT";

    @Test
    public void map() {
        final ContextMapper<String, String, String> sut = new ContextMapper<String, String, String>() {
            @Override
            public String map(final String context, final String input) {
                return context + " " + input;
            }
        };

        final String output = sut.map(CONTEXT, INPUT);
        assertThat(output).isEqualTo("CONTEXT INPUT");

        final List<String> outputs = sut.map(CONTEXT, singletonList(INPUT));
        assertThat(outputs).hasSize(1);
        assertThat(outputs.get(0)).isEqualTo("CONTEXT INPUT");
    }

}
