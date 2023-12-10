package lotto.controller

import lotto.domain.*
import lotto.domain.data.BonusNumber
import lotto.domain.data.Lotteries
import lotto.domain.data.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoGame(
    val inputView: InputView,
    val outputView: OutputView,
    val lottoSeller: LottoSeller,
    val lottoMachine: LottoMachine,
    val totalPrizeService: TotalPrizeService,
    val profitRate: ProfitRate,
) {

    fun start() {
        val payment = payForLotteries()
        val lotteries = buyLotteries(payment)
        outputView.showBoughtLottoes(lotteries)

        showWinningResult(lotteries, winningLotto(), payment)
    }

    private fun payForLotteries(): Int {
        outputView.paymentGuide()
        return inputView.readPayment()
    }

    private fun buyLotteries(payment: Int): Lotteries {
        val lottoTicketCount = lottoSeller.buyLottoTicket(payment)
        return lottoMachine.generateLottoes(lottoTicketCount)
    }

    private fun showWinningResult(
        lotteries: Lotteries,
        winningLotto: WinningLotto,
        payment: Int,
    ) {
        val totalPrizes = totalPrizeService.calculateTotalPrizes(lotteries, winningLotto)
        outputView.showWinningPrizes(totalPrizes)
        outputView.showProfitRate(profitRate.calculatedProfitRate(totalPrizes, payment))
    }

    private fun winningLotto(): WinningLotto = WinningLotto(readWinningNumbers(), readBonusNumber())

    private fun readWinningNumbers(): Lotto {
        outputView.winningNumbersGuide()
        return inputView.readWinningNumbers().toWinningNumbers()
    }

    private fun readBonusNumber(): BonusNumber {
        outputView.bonusNumberGuide()
        return inputView.readBonusNumber().toBonusNumber()
    }

}

fun List<String>.toWinningNumbers(): Lotto {
    val numbers = this.mapNotNull { it.toIntOrNull() }
    require(this.size == numbers.size) { "당첨 번호가 숫자가 아닌 값이 포함되어 있습니다." }
    return Lotto(numbers)
}

fun Int.toBonusNumber(): BonusNumber = BonusNumber(this)