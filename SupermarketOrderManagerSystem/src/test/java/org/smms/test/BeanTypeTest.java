package org.smms.test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: javabean用户实体类测试
 * @ClassName: BeanTypeTest
 * @Pacakage_name: org.smms.test
 * @Project_name: smms
 * @Author: lc_co
 * @Date: 2021.10.13 19:23
 * @Version: 1.0
 * @Company: xxxxx
 * @Copyright: (c) 2021 lc_co All rights reserved.
 * @javaVersion: jdk1.8
 */
public class BeanTypeTest{

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().getYear());
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.set(1969,10,1);
        Date time = calendarDate.getTime();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(time);
        System.out.println(format);
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().set(2021,9,1)).toString().split("-")[0]);
    }
}
