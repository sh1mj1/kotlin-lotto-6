package lotto.domain

class LottoSeller {
    fun buyLottoTicket(payment: Int): Int {
        require(payment > 0) { "금액은 양의 정수여야 한다." }
        require(payment % LOTTO_PRICE == 0) { "금액은 $LOTTO_PRICE 단위로 입력해야 한다" }
        return payment / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}