package multipledatasources

import grails.buildtestdata.BuildDataTest
import grails.buildtestdata.mixin.Build
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

@Build([Book])
class BookSpec extends Specification implements DomainUnitTest<Book>, BuildDataTest {

    void 'save new, find, count'() {
        given:
        Book book = new Book(name: 'joe')
        book.save(failOnError: true)

        expect:
        Book.findAllById(book.id) == [book]
        Book.count() == 1
    }

    void 'build, find, count'() {
        given:
        Book book = Book.build()

        expect:
        Book.findAllById(book.id) == [book]
        Book.count() == 1
    }
}
