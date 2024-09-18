package com.mockito.mokito_demo.business;

public class SomeBusiness {
    private DataService dataService;

    public SomeBusiness(DataService dataService){
        super();
        this.dataService = dataService;
    }

    public int findGreatestFromAllData(){
        int data[] = dataService.retrieveAllData();
        int greatestValue = Integer.MIN_VALUE;
        for(int value : data){
            if(value>greatestValue) greatestValue=value;
        }
        return greatestValue;
    }
}

interface DataService{
    int[] retrieveAllData();
}
