package com.jenison.batchdemo.processing;

import com.jenison.batchdemo.model.Users;
import com.jenison.batchdemo.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<Users> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void write(List<? extends Users> items) throws Exception {
        System.out.println("processed "+items.toString());
        userRepository.<Users>saveAll((Iterable<Users>) items);
    }
}
