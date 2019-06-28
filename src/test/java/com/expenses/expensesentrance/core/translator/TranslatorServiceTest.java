package com.expenses.expensesentrance.core.translator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.expenses.expensesentrance.common.model.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class TranslatorServiceTest {

    @Mock
    private FileMapper fileMapper;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TranslatorService sut;

    @Mock
    private List<Map<String, String>> list;

    @Mock
    private List<Transaction> transactions;

    @Test
    public void translate() throws IOException {

        when(fileMapper.mapFile(any(MultipartFile.class))).thenReturn(list);
        when(transactionMapper.map(list)).thenReturn(transactions);

        final List<Transaction> result = sut.translate(new MockMultipartFile("data", "filename.txt", "text/plain", "iets".getBytes()));

        assertThat(result).isEqualTo(transactions);
    }
}
