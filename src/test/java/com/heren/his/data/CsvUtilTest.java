package com.heren.his.data;

import org.junit.Test;


/**
 * Created by joshua on 2016/3/28.
 */
public class CsvUtilTest {

    @Test
    public void testGetList() throws Exception {

        CsvUtil csvUtil = new CsvUtil("src\\test\\resources\\patient_master.csv");
        String result = csvUtil.getCol(1);
        String[] output = result.split(",");
        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i]);
        }
    }
}