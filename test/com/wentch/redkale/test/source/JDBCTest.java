/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wentch.redkale.test.source;

import com.wentch.redkale.source.DataSource;
import com.wentch.redkale.source.DataSourceFactory;

/**
 *
 * @author zhangjx
 */
public class JDBCTest {

    public static void main(String[] args) throws Exception {
        System.setProperty("persist.type", "jdbc");
        DataSource source = DataSourceFactory.create();  //耗时：37415
        int count = 1000;
        LoginTestRecord last = null;
        long s = System.currentTimeMillis();
        int c = 0;
        try {
            for (int i = 0; i < count; i++) {
                LoginTestRecord record = new LoginTestRecord();
                record.setSessionid(Long.toHexString(System.nanoTime()));
                record.setLoginagent("win7");
                record.setLogintime(System.currentTimeMillis());
                record.setLoginip("127.0.0.1");
                record.setUserid(i);
                source.insert(record);
                last = record;
                c = i;
            }
        } catch (Exception e) {
            System.out.println("异常了: " + c);
            e.printStackTrace();
        }
        long e = System.currentTimeMillis() - s;
        System.out.println("耗时：" + e);
    }
}