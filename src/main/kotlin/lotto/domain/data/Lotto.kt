package lotto.domain.data

data class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "로또의 숫자는 6자리입니다." }

        require(numbers.size == numbers.toSet().size) { "로또에 중복된 숫자가 있습니다." }

        numbers.forEach {
            require(it in 1..45) { "각 로또 번호는 1~45 사이입니다." }
        }
    }

    fun notHaveBonusNumber(bonusNumber: BonusNumber): Boolean = numbers.all { it != bonusNumber.number }

    fun countMatchingNumber(lotto: Lotto): Int = numbers.count { lotto.numbers.contains(it) }
}
