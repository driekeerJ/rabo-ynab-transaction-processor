package com.expenses.expensesentrance.common.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Data {

    private final ProcessedTransactionData data;

    private final int numberOfRecordsReceived;

    private final int numberOfRecordsProcessed;

    private final long numberOfRecordsMatched;

    private final int numberOfRecordsDuplicated;
}
