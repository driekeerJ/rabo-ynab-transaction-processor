//package com.expenses.expensesentrance.client;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.expenses.expensesentrance.client.mapper.FromDataDtoMapper;
//import com.expenses.expensesentrance.client.mapper.ToTransactionsDtoMapper;
//import com.expenses.expensesentrance.client.model.DataDto;
//import com.expenses.expensesentrance.client.model.TransactionsDto;
//import com.expenses.expensesentrance.common.model.Data;
//import com.expenses.expensesentrance.common.model.Transaction;
//
//@RunWith(MockitoJUnitRunner.class)
//public class YnabClientTest {
//
//    @Mock
//    private FromDataDtoMapper fromDataDtoMapper;
//
//    @Mock
//    private ToTransactionsDtoMapper toTransactionsDtoMapper;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private YnabClient sut;
//
//    @Mock
//    private List<Transaction> transactionList;
//
//    @Mock
//    private TransactionsDto transactionsDto;
//
//    @Mock
//    private DataDto dataDto;
//
//    @Mock
//    private Data data;
//
//    @Before
//    public void setup() {
//        when(toTransactionsDtoMapper.map("ACCOUNT" ,transactionList)).thenReturn(transactionsDto);
//        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), any(Class.class))).thenReturn(dataDto);
//        when(fromDataDtoMapper.map(dataDto)).thenReturn(data);
//    }
//
//    @Test
//    public void processTransactions() {
//        final Data result = sut.processTransactions(transactionList, "TOKEN", "BUDGET", "ACCOUNT");
//        assertThat(result).isEqualTo(data);
//    }
//}
