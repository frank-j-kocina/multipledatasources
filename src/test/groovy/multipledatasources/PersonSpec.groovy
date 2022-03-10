package multipledatasources

import grails.buildtestdata.BuildDataTest
import grails.buildtestdata.mixin.Build
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

@Build([Person])
class PersonSpec extends Specification implements DomainUnitTest<Person>, BuildDataTest {

    void 'save new, find, count'() {
        given:
        Person person = new Person(name: 'joe')
        person.save(failOnError: true)

        expect:
        Person.findAllById(person.id) == [person]
        Person.count() == 1
    }

    void 'build, find, count'() {
        given:
        Person person = Person.build()

        expect:
        Person.findAllById(person.id) == [person]
        Person.count() == 1
    }
}
