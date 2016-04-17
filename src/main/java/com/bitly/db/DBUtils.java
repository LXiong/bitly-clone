package com.bitly.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class DBUtils {
 private static final Logger logger = LogManager.getLogger(DBUtils.class);

 @Autowired
 private DataSource dataSource;
 
 @PostConstruct
 public void initialize(){
  try {
   Connection connection = dataSource.getConnection();
   Statement statement = connection.createStatement();
   statement.execute("DROP TABLE IF EXISTS SHORTEN");
   statement.executeUpdate(
     "CREATE TABLE SHORTEN(" +
     "ORIGINAL_URL VARCHAR Primary key, " +
     "SHORTEN_URL VARCHAR not null, " +
     "CREATE_TIME TIMESTAMP not null)"
     );
   statement.close();
   connection.close();
  }
  catch (SQLException e) {
      logger.error("database init error!", e);
  }
 }
}