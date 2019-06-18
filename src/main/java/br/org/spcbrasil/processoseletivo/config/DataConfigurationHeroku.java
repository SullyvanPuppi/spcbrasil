package br.org.spcbrasil.processoseletivo.config;

import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 18 de jun de 2019
 *
 */
@Configuration
@Profile("Heroku")
public class DataConfigurationHeroku {
	
	@Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        String dbUrl = System.getenv("jdbc:mysql://174.142.130.65:3306/sullyvan_spcbrasil");
        String username = System.getenv("sullyvan_spcbras");
        String password = System.getenv("bq7fc070779hHhpP6xwp1e2T");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return adapter;
	}

}
