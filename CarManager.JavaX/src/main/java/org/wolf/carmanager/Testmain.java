package org.wolf.carmanager;

import org.wolf.carmanager.model.CarPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wolf on 2014/11/28.
 */
public class Testmain {
    public static void main(String[] args) {
        List<String > list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        int start = 0;
        int end = 0;
        int count = list.size();
        while (!(start == count)) {
            end += 6;
            if (end >= count ) {
                end = count;
            }
            List<String> tmplist = list.subList(start, end);
            for(String item:tmplist)
                System.out.println(item);
            start = end ;
        }
    }
}
