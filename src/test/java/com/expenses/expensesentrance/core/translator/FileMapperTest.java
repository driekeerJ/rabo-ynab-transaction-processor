package com.expenses.expensesentrance.core.translator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

public class FileMapperTest {

    private static final FileMapper sut = new FileMapper();

    private static MockMultipartFile mockMultipartFile;

    @Before
    public void setup() {


    }

    @Test
    public void mapFile_oneRecord() throws IOException {
        final String bankSpecificTransaction =
                "\"IBAN/BBAN\",\"Munt\",\"BIC\",\"Volgnr\",\"Datum\",\"Rentedatum\",\"Bedrag\",\"Saldo na trn\",\"Tegenrekening IBAN/BBAN\",\"Naam tegenpartij\",\"Naam uiteindelij"
                        + "ke partij\",\"Naam initi�rende partij\",\"BIC tegenpartij\",\"Code\",\"Batch ID\",\"Transactiereferentie\",\"Machtigingskenmerk\",\"Incassant ID\",\"Beta"
                        + "lingskenmerk\",\"Omschrijving-1\",\"Omschrijving-2\",\"Omschrijving-3\",\"Reden retour\",\"Oorspr bedrag\",\"Oorspr munt\",\"Koers\"\n"
                        + "\"NL49RABO0123456789\",\"EUR\",\"RABONL2U\",\"000000000000007395\",\"2019-03-01\",\"2019-03-01\",\"-1193,02\",\"+2810,80\",\"NL96ABNA123456789\",\"AEGON "
                        + "NEDERLAND NV\",\"\",\"\",\"ABNANL2A\",\"ei\",\"\",\"fdsafdsa/\",\"fdsaFDF\",\"NL98ZZZ987654321\",\"\",\"AEGON \",\" \",\"\",\"\",\"\",\"\",\"\"\n"
                        + "\"NL49RABO0123456789\",\"EUR\",\"RABONL2U\",\"000000000000007396\",\"2019-03-01\",\"2019-03-01\",\"-1,50\",\"+2809,30\",\"\",\"MEANDER PARKEREN "
                        + "AMERSFOORT\",\"\",\"\",\"\",\"bc\",\"\",\"\",\"\",\"\",\"\",\"Betaalautomaat\",\" \",\"\",\"\",\"\",\"\",\"\"";

        final MockMultipartFile mockMultipartFile = new MockMultipartFile("data", "filename.txt", "text/plain", bankSpecificTransaction.getBytes());

        final List<Map<String, String>> result = sut.mapFile(mockMultipartFile);
        assertThat(result).hasSize(2);
    }

    @Test(expected = NullPointerException.class)
    public void mapFile_zeroRecord() throws IOException {
        final MockMultipartFile mockMultipartFile = new MockMultipartFile("data", "filename.txt", "text/plain", "".getBytes());

        sut.mapFile(mockMultipartFile);
    }

}
