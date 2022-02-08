package multipledatasources

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.grails.orm.hibernate.HibernateDatastore
import org.grails.orm.hibernate5.support.GrailsOpenSessionInViewInterceptor
import org.springframework.context.annotation.Bean

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    // https://github.com/grails/grails-core/issues/10383
    // https://github.com/grails/grails-data-mapping/blob/v5.0.12/grails-datastore-gorm-hibernate5/src/main/groovy/grails/orm/bootstrap/HibernateDatastoreSpringInitializer.groovy#L314
    // https://github.com/grails/gorm-hibernate5/blob/v6.1.12/grails-datastore-gorm-hibernate5/src/main/groovy/grails/orm/bootstrap/HibernateDatastoreSpringInitializer.groovy#L203
    // https://stackoverflow.com/questions/42443260/using-opensessioninviewinterceptor-in-java-config-not-webxml
    @Bean
    GrailsOpenSessionInViewInterceptor openSessionInViewInterceptor_books() {
        HibernateDatastore hibernateDatastore = applicationContext.getBean('hibernateDatastore') as HibernateDatastore
        GrailsOpenSessionInViewInterceptor openSessionInViewInterceptor = new GrailsOpenSessionInViewInterceptor()
        HibernateDatastore hibernateDatastore_books = hibernateDatastore.getDatastoreForConnection('books')
        openSessionInViewInterceptor.hibernateDatastore = hibernateDatastore_books
        return openSessionInViewInterceptor
    }
}
