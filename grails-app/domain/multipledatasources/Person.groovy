package multipledatasources

class Person {

    String name

    static constraints = {
        name maxSize: 100
    }
}
