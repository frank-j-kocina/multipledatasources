package multipledatasources

import grails.buildtestdata.BuildDataTest
import grails.buildtestdata.mixin.Build
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

@Build([Book, Person])
class PersonControllerSpec extends Specification implements ControllerUnitTest<PersonController>, BuildDataTest {

    void "index"() {
        given:
        Book.build()
        Person.build()
        new Book(name: 'Joe').save(failOnError: true)
        new Person(name: 'Joe').save(failOnError: true)

        when:
        controller.index()

        then:
        // 2 in test, 1 in controller
        Person.count() == 3
        Book.count() == 3
    }
}
