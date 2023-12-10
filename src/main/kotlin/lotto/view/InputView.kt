package lotto.view

import camp.nextstep.edu.missionutils.Console.readLine

class InputView {

    fun readPayment(): Int {
        println("구입금액을 입력해주세요.")
        val payment = readLine().toIntOrNull()
        requireNotNull(payment) { "숫자로 입력해주세요." }
        return payment
    }

    fun readWinningNumbers(): List<String> {
        println("당첨 번호를 입력해 주세요.")
        val input = readLine()
        require(input.isNotBlank()) { "당첨 번호를 제대로 입력해주세요. (공백을 입력하셨습니다.)" }
        require(onlyNumberOrDelimiter(input)) { "당첨 번호를 제대로 입력해주세요. (구분자는 , 입니다.)" }
        return input.trim().split(NUMBER_DELIMITER).map { it.trim() }
    }

    fun readBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        val number = readLine().toIntOrNull()
        requireNotNull(number) { "숫자로 입력해주세요." }
        return number
    }

    private fun onlyNumberOrDelimiter(input: String): Boolean = regex.matches(input)

    companion object {
        const val NUMBER_DELIMITER = ","
        private val regex = """^[0-9,]+$""".toRegex()
    }
}