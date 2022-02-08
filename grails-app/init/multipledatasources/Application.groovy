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

    @Bean
    GrailsOpenSessionInViewInterceptor openSessionInViewInterceptor_books() {
        HibernateDatastore hibernateDatastore = applicationContext.getBean('hibernateDatastore') as HibernateDatastore
        GrailsOpenSessionInViewInterceptor openSessionInViewInterceptor = new GrailsOpenSessionInViewInterceptor()
        HibernateDatastore hibernateDatastore_books = hibernateDatastore.getDatastoreForConnection('books')
        openSessionInViewInterceptor.hibernateDatastore = hibernateDatastore_books
        return openSessionInViewInterceptor
    }
}
