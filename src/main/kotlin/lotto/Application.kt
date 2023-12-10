package lotto

import lotto.controller.LottoGame
import lotto.domain.*
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    LottoGame(
        inputView = InputView(),
        outputView = OutputView(),
        lottoSeller = LottoSeller(),
        lottoMachine = LottoMachine(),
        totalPrizeService = TotalPrizeService(PrizeCalculator()),
        profitRate = ProfitRate()
    ).start()
}
