package multipledatasources

import grails.gorm.transactions.ReadOnly

class PersonController {

    @ReadOnly
    def index() {
        Person p = new Person(name: 'Joe')
        Book b = new Book(name: 'Name of the Wind')
        p.save(failOnError: true)
        b.save(failOnError: true)
        render(view: 'index', model: [p: p, b: b])
    }
}
