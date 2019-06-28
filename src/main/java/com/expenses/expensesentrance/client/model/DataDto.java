package com.expenses.expensesentrance.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDto {

    private ProcessedDataDto data;

}
