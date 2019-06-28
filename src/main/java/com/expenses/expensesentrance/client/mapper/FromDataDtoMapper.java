package com.expenses.expensesentrance.client.mapper;

import org.springframework.stereotype.Component;

import com.expenses.expensesentrance.client.model.DataDto;
import com.expenses.expensesentrance.common.Mapper;
import com.expenses.expensesentrance.common.model.Data;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FromDataDtoMapper implements Mapper<DataDto, Data> {

    private final FromProcessedDataDtoMapper fromProcessedDataDtoMapper;

    @Override
    public Data map(final DataDto input) {
        return Data.builder().data(fromProcessedDataDtoMapper.map(input.getData())).build();
    }
}
