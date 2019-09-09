package com.spooldir.custom.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class DirPoolStarters {

    @Autowired
    RestTemplate restTemplate;
    @PostMapping(path = "/connect")
    public ResponseEntity<String> startConnector(){
        String json="{\n" +
                "    \"name\": \"CsvSpoolDir\",\n" +
                "    \"config\": {\n" +
                "        \"connector.class\": \"com.github.jcustenborder.kafka.connect.spooldir.SpoolDirCsvSourceConnector\",\n" +
                "        \"input.path\":\"C:\\\\Users\\\\grje9001\\\\Downloads\\\\kafka_2.12-2.3.0\\\\plugins\\\\jcustenborder-kafka-connect-spooldir-1.0.41\\\\etc\",\n" +
                "        \"error.path\":\"C:\\\\tmp\\\\error\",\n" +
                "        \"input.file.pattern\":\"csv-spooldir-source.csv\",\n" +
                "        \"finished.path\":\"C:\\\\tmp\",\n" +
                "        \"halt.on.error\":\"false\",\n" +
                "        \"topic\":\"jenisonspooltesting\",\n" +
                "        \"key.schema\":\"{\\\"name\\\":\\\"com.example.users.UserKey\\\",\\\"type\\\":\\\"STRUCT\\\",\\\"isOptional\\\":false,\\\"fieldSchemas\\\":{\\\"id\\\":{\\\"type\\\":\\\"INT64\\\",\\\"isOptional\\\":false}}}\",\n" +
                "        \"value.schema\":\"{\\\"name\\\":\\\"com.example.users.User\\\",\\\"type\\\":\\\"STRUCT\\\",\\\"isOptional\\\":false,\\\"fieldSchemas\\\":{\\\"id\\\":{\\\"type\\\":\\\"INT64\\\",\\\"isOptional\\\":false},\\\"first_name\\\":{\\\"type\\\":\\\"STRING\\\",\\\"isOptional\\\":true},\\\"last_name\\\":{\\\"type\\\":\\\"STRING\\\",\\\"isOptional\\\":true},\\\"email\\\":{\\\"type\\\":\\\"STRING\\\",\\\"isOptional\\\":true},\\\"gender\\\":{\\\"type\\\":\\\"STRING\\\",\\\"isOptional\\\":true},\\\"ip_address\\\":{\\\"type\\\":\\\"STRING\\\",\\\"isOptional\\\":true},\\\"last_login\\\":{\\\"name\\\":\\\"org.apache.kafka.connect.data.Timestamp\\\",\\\"type\\\":\\\"INT64\\\",\\\"version\\\":1,\\\"isOptional\\\":true},\\\"account_balance\\\":{\\\"name\\\":\\\"org.apache.kafka.connect.data.Decimal\\\",\\\"type\\\":\\\"BYTES\\\",\\\"version\\\":1,\\\"parameters\\\":{\\\"scale\\\":\\\"2\\\"},\\\"isOptional\\\":true},\\\"country\\\":{\\\"type\\\":\\\"STRING\\\",\\\"isOptional\\\":true},\\\"favorite_color\\\":{\\\"type\\\":\\\"STRING\\\",\\\"isOptional\\\":true}}}\",\n" +
                "        \"csv.first.row.as.header\":\"true\"\n" +
                "    }\n" +
                "}";
        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ArrayList<MediaType> list=new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        header.setAccept(list);
        HttpEntity<String> entity=new HttpEntity<String>(json,header);

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8083/connectors", entity, String.class);
        if(response.getStatusCode()== HttpStatus.CREATED){
            return ResponseEntity.ok("connection successful");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed to connect");
        }

    }
}
