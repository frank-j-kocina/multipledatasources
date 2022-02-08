package multipledatasources

class PersonController {

    def index() {
        Long personCount = Person.count()
        Long bookCount = Book.count()

        Person p = new Person(name: 'Joe')
        p.save(failOnError: true)

        Book b = new Book(name: 'Name of the Wind')
        b.save(failOnError: true)

        Map model = [p: p,
                     b: b,
                     personCount: personCount,
                     bookCount: bookCount]

        render(view: 'index', model: model)
    }
}
