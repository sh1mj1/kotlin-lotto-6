package lotto.model

class LottoSeller(private val payment: Int) {
    fun calculatedLottoTicketCount() = payment / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1000
    }
}