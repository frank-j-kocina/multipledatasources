package multipledatasources

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class PersonControllerSpec extends Specification implements ControllerUnitTest<PersonController>, DataTest {

    void "index"() {
        given:
        mockDomains(Person, Book)

        when:
        controller.index()

        then:
        Person.count() == 1
        Book.count() == 1
    }
}
