package lotto.domain.data

data class BonusNumber(val number: Int) {
    init {
        require(number in 1..45) { "보너스 번호는 1~45 사이입니다." }
    }
}
