package lotto.controller

import lotto.domain.*
import lotto.domain.data.BonusNumber
import lotto.domain.data.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoGame(
    val inputView: InputView,
    val outputView: OutputView,
) {

    fun start() {
        val lottoSeller = LottoSeller()
        val payment = inputView.readPayment()
        val lottoTicketCount = lottoSeller.buyLottoTicket(payment)

        val lottoMachine = LottoMachine()
        val lotteries = lottoMachine.generateLottoes(lottoTicketCount)
        outputView.showBoughtLottoes(lotteries)

        val winningNumbers = inputView.readWinningNumbers().toWinningNumbers()
        val bonusNumber = inputView.readBonusNumber().toBonusNumber()
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        val totalPrizeService = TotalPrizeService(PrizeCalculator())
        val totalPrizes = totalPrizeService.calculateTotalPrizes(lotteries, winningLotto)

        outputView.showWinningPrizes(totalPrizes)

        val profitRate = ProfitRate()
        outputView.showProfitRate(profitRate.calculatedProfitRate(totalPrizes, payment))
    }

}

fun List<String>.toWinningNumbers(): Lotto {
    val numbers = this.mapNotNull { it.toIntOrNull() }
    require(this.size == numbers.size) { "당첨 번호가 숫자가 아닌 값이 포함되어 있습니다." }
    return Lotto(numbers)
}

fun Int.toBonusNumber(): BonusNumber = BonusNumber(this)