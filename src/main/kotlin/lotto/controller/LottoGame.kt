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
        val payment = inputView.readPayment()
        val lotteries = buyLotteries(payment)
        outputView.showBoughtLottoes(lotteries)

        showWinningResult(lotteries, winningLotto(), payment)
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

    private fun winningLotto(): WinningLotto {
        val winningNumbers = inputView.readWinningNumbers().toWinningNumbers()
        val bonusNumber = inputView.readBonusNumber().toBonusNumber()
        return WinningLotto(winningNumbers, bonusNumber)
    }

}

fun List<String>.toWinningNumbers(): Lotto {
    val numbers = this.mapNotNull { it.toIntOrNull() }
    require(this.size == numbers.size) { "당첨 번호가 숫자가 아닌 값이 포함되어 있습니다." }
    return Lotto(numbers)
}

fun Int.toBonusNumber(): BonusNumber = BonusNumber(this)