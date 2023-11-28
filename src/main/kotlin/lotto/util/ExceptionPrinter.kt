package lotto.util


fun <T> executeSafelyAndPrintException(block: () -> T): Boolean {
    try {
        block()
    } catch (e: IllegalArgumentException) {
        println(e.message)
        return false
    }
    return true
}
