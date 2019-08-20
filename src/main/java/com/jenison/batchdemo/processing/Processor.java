package com.jenison.batchdemo.processing;

import com.jenison.batchdemo.model.Users;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;

@Component
public class Processor implements ItemProcessor<Users, Users> {

    private HashMap<String,String> deptMap=new HashMap<>();
    public Processor(){
        deptMap.put("001","Hardware");
        deptMap.put("002","Software");
    }
    @Override
    public Users process(Users item) throws Exception {
        System.out.println("processed "+item.toString());
        String dept = item.getDept();
        String actualDept = deptMap.get(dept);
        item.setDept(actualDept);
        return item;
    }
}
