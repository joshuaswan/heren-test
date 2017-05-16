package com.heren.his.data;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;


public class SearchDataTest {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(SearchDataTest.class));
    private String[] billNumber;
    private String[][] multipleString;
    private SearchData searchData;

    @Before
    public void defineTest() {
        LOGGER.info(String.valueOf(SearchDataTest.class));
        billNumber = new String[500];
        multipleString = new String[1][1];
        searchData = new SearchData();
    }

    @Test
    public void testSingleData() throws Exception {
        int loopNumber = 0;
        try {
            loopNumber = searchData.singleData(billNumber, billNumber.length, "select distinct t.recipe_no from outp_order_costs t where " +
                    "t.patient_id in (select p.patient_id from pat_master_index p WHERE p.birth_place_code='532625') AND " +
                    "ordered_date >  to_date('2015-11-09' ,'yyyy-mm-dd') and settle_flag = 0 order by t.recipe_no", "recipe_no");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < loopNumber; ++i) {
            LOGGER.info(billNumber[i]);
        }
    }

    @Test
    public void testMultipleData() throws Exception {
        int loopNumber = 0;
        String[] date = {"recipe_no"};
        try {
            loopNumber = searchData.multipleData(multipleString, 1, 1, "select distinct t.recipe_no from outp_order_costs t where " +
                    "t.patient_id in (select p.patient_id from pat_master_index p WHERE p.birth_place_code='532625') AND " +
                    "ordered_date >  to_date('2015-11-09' ,'yyyy-mm-dd') and settle_flag = 0 order by t.recipe_no", date);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < loopNumber; ++i) {
            LOGGER.info(billNumber[i]);
        }
    }

    @Test
    public void testGetToday() throws Exception {
        LOGGER.info(searchData.getToday());
    }
}