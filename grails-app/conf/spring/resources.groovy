import org.grails.transaction.ChainedTransactionManager
import org.springframework.transaction.PlatformTransactionManager

// Place your Spring DSL code here
beans = {
    def platformTransactionManager = ref('transactionManager')
    def platformTransactionManager_books = ref('transactionManager_books')

    def ctm = new ChainedTransactionManager(
            platformTransactionManager as PlatformTransactionManager,
            platformTransactionManager_books as PlatformTransactionManager)
    transactionManager(ctm)
}
