package lotto.view

import camp.nextstep.edu.missionutils.Console.readLine

class InputView {

    fun readPayment(input: String = readLine()): Int {
        val payment = input.toIntOrNull()
        requireNotNull(payment) { "숫자로 입력해주세요." }
        return payment
    }

    fun readWinningNumbers(input: String = readLine()): List<String> {
        require(input.isNotBlank()) { "당첨 번호를 제대로 입력해주세요. (공백을 입력하셨습니다.)" }
        require(onlyNumberOrDelimiter(input)) { "당첨 번호를 제대로 입력해주세요. (구분자는 , 입니다.)" }
        return input.trim().split(NUMBER_DELIMITER).map { it.trim() }
    }

    fun readBonusNumber(input: String = readLine()): Int {
        val number = input.toIntOrNull()
        requireNotNull(number) { "숫자로 입력해주세요." }
        return number
    }

    private fun onlyNumberOrDelimiter(input: String): Boolean = regex.matches(input)

    companion object {
        const val NUMBER_DELIMITER = ","
        private val regex = """^[0-9,]+$""".toRegex()
    }
}