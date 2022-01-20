package multipledatasources

class Book {

    String name

    static constraints = {
        name maxSize: 255
    }

    static mapping = {
        datasource 'books'
    }
}
