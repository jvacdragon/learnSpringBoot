package com.mockito.mokito_demo.business;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class SomeBusinessMockitoTest {

    @Test
    void findGreatesFromAllData(){
        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int []{23,48,21});

        SomeBusiness someBusiness = new SomeBusiness(dataServiceMock);

        int result = someBusiness.findGreatestFromAllData();
        assertEquals(48,result);
    }
}