package lotto

import lotto.controller.LottoGame
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    LottoGame(
        inputView = InputView(),
        outputView = OutputView()
    ).start()
}
