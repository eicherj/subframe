/*
 * SUBFRAME - Simple Java Benchmarking Framework
 * Copyright (C) 2012 - 2016 Fabian Prasser and contributors
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.linearbits.subframe.analyzer.buffered;

import de.linearbits.subframe.analyzer.Analyzer;

/**
 * A buffered analyzer that computes the arithmetic mean
 * @author Fabian Prasser
 */
public class BufferedArithmeticMeanAnalyzer extends BufferedAnalyzer{

    /**
     * Constructs a default instance. Backed by an array list with size 10 and a 1.5 growth rate
     */
    public BufferedArithmeticMeanAnalyzer(){
        super(Analyzer.ARITHMETIC_MEAN);
    }

    /**
     * Constructs an instance backed by an array list with given initial size and a 1.5 growth rate
     * @param size
     */
    public BufferedArithmeticMeanAnalyzer(int size){
        super(Analyzer.ARITHMETIC_MEAN, size);
    }

    /**
     * Constructs an instance backed by an array list with given initial size and given growth rate
     * @param initialSize
     * @param growthRate
     */
    public BufferedArithmeticMeanAnalyzer(int initialSize, double growthRate){
        super(Analyzer.ARITHMETIC_MEAN, initialSize, growthRate);
    }
    
    /**
     * Clone constructor
     * @param label
     * @param size
     * @param count
     * @param growthRate
     */
    public BufferedArithmeticMeanAnalyzer(String label, int size, int count, double growthRate) {
        super(label, size, count, growthRate);
    }
    
    @Override
    public String getValue() {
        if (count==0) throw new RuntimeException("No values specified!");
        double result = 0d;
        for (int i=0; i<count; i++){
            result += values[i];
        }
        return String.valueOf(result / (double)count);
    }

    @Override
    public Analyzer<Double> newInstance() {
        return new BufferedArithmeticMeanAnalyzer(super.getLabel(), super.values.length, 0, super.growthRate);
    }
}
