package multipledatasources

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.DefaultTransactionDefinition
import spock.lang.Specification

@Integration
// @Rollback - works with or without
class MultipleRollbackIntegrationSpec extends Specification {

    @Autowired
    @Qualifier('transactionManager')
    PlatformTransactionManager transactionManager_default

    @Autowired
    @Qualifier('transactionManager_books')
    PlatformTransactionManager transactionManager_books

    private TransactionStatus defaultTransactionStatus
    private TransactionStatus booksTransactionStatus

    void setup() {
        defaultTransactionStatus = transactionManager_default.getTransaction(new DefaultTransactionDefinition())
        booksTransactionStatus = transactionManager_books.getTransaction(new DefaultTransactionDefinition())
    }

    void cleanup() {
        transactionManager_default.rollback(defaultTransactionStatus)
        transactionManager_books.rollback(booksTransactionStatus)
    }

    void 'count, save, count'() {
        expect:
        !Person.count()
        !Book.count()

        when:
        new Person(name: 'Joe').save(failOnError: true)
        new Book(name: 'Joe').save(failOnError: true)

        then:
        Person.count()
        Book.count()
    }

    void 'find, save, find'() {
        expect:
        !Person.findByName('Joe')
        !Book.findByName('Joe')

        when:
        new Person(name: 'Joe').save(failOnError: true)
        new Book(name: 'Joe').save(failOnError: true)

        then:
        Person.findByName('Joe')
        Book.findByName('Joe')
    }

    void 'count, build, count'() {
        expect:
        !Person.count()
        !Book.count()

        when:
        Person.build(name: 'Joe')
        Book.build(name: 'Joe')

        then:
        Person.count()
        Book.count()
    }

    void 'find, build, find'() {
        expect:
        !Person.findByName('Joe')
        !Book.findByName('Joe')

        when:
        Person.build(name: 'Joe')
        Book.build(name: 'Joe')

        then:
        Person.findByName('Joe')
        Book.findByName('Joe')
    }
}
