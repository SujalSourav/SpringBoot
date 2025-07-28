package com.example.firstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean // yeh bana kee bta rhe hai spring ko ki yeh manager hai aur isko mongotransaction implement krega
	// MongoDatabaseFactory -- issi ke help see db ya session(transaction context) ke sth connection bnega aur yeh interface hai
	// isse ke help see database se connection bnega
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory)
	{
		return new MongoTransactionManager(dbFactory);
	}

}

// PlatformTransactionManager --- manager hai jo handle krta hai tranasction ko , interface hai
// MongoTransactionManager --- yeh implement krega platformtransactionmanager ko jaise ki roll back, session close tc yeh sb handle krega
