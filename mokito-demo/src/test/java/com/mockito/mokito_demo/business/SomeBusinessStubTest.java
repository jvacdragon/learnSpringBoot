package com.mockito.mokito_demo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SomeBusinessStubTest {

    @Test
    void test(){
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusiness someBusiness = new SomeBusiness(dataServiceStub);
        someBusiness.findGreatestFromAllData();
        int result = someBusiness.findGreatestFromAllData();
        assertEquals(46,result);
    }
}

class DataServiceStub implements DataService{
    @Override
    public int[] retrieveAllData() {
        return new int[] {23,4,46};
    }
}