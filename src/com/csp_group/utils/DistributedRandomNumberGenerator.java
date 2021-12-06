package com.csp_group.utils;

import java.util.HashMap;
import java.util.Map;

public class DistributedRandomNumberGenerator {

    private Map<Integer, Double> distribution;
    private double distSum;

    public DistributedRandomNumberGenerator() {
        distribution = new HashMap<>();
    }

    public void addNumber(int value, double distribution) {
        if (this.distribution.get(value) != null) {
            distSum -= this.distribution.get(value);
        }
        this.distribution.put(value, distribution);
        distSum += distribution;
    }

    public int getDistributedRandomNumber() {
        double rand = Math.random();
        double ratio = 1.0f / distSum;
        double tempDist = 0;
        for (Integer i : distribution.keySet()) {
            tempDist += distribution.get(i);
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return 0;
    }
    
    public static int randomValue(double[] distribution) {
    	double rand = Math.random();
        double ratio = 1.0f / sumArray(distribution);
        double tempDist = 0;
        for (int i = 0; i < distribution.length; i++) {
            tempDist += distribution[i];
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return 0;
    }
    
    public static int randomValue(int[] distribution) {
    	double rand = Math.random();
        double ratio = 1.0f / sumArray(distribution);
        double tempDist = 0;
        for (int i = 0; i < distribution.length; i++) {
            tempDist += distribution[i];
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return 0;
    }
    
    
    private static double sumArray(double[] array){
    	double total = 0;
    	for(double val : array) {
    		total += val;
    	}
    	return total;
    }
    
    private static double sumArray(int[] array){
    	double total = 0;
    	for(int val : array) {
    		total += val;
    	}
    	return total;
    }
    
    public static void main(String[] args) {
        DistributedRandomNumberGenerator drng = new DistributedRandomNumberGenerator();
        drng.addNumber(1, 4);
        drng.addNumber(2, 6);
        drng.addNumber(3, 10);

        int testCount = 1000000;

        HashMap<Integer, Double> test = new HashMap<>();

        for (int i = 0; i < testCount; i++) {
            int random = drng.getDistributedRandomNumber();
            test.put(random, (test.get(random) == null) ? (1d / testCount) : test.get(random) + 1d / testCount);
        }
        
        int[] probs = {4,6,10,20,40};
        HashMap<Integer, Double> test_2 = new HashMap<>();
        
        for (int i = 0; i < testCount; i++) {
        	int index = randomValue(probs);
        	
        	test_2.put(index, (test_2.get(index) == null) ? (1d / testCount) : test_2.get(index) + 1d / testCount);
        }

        System.out.println(test.toString());
        System.out.println(test_2.toString());
    }
    
}