package multipledatasources

import grails.gorm.transactions.Transactional

class PersonController {

    @Transactional
    def index() {
        Person p = new Person(name: 'Joe')
        p.save(failOnError: true)

        Book b = new Book(name: 'Name of the Wind')
        b.save(failOnError: true)

        Long personCount = Person.count()
        Long bookCount = Book.count()

        Map model = [
                p: p,
                b: b,
                personCount: personCount,
                bookCount: bookCount]

        render(view: 'index', model: model)
    }
}
