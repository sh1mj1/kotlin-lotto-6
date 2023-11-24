package lotto.controller

import lotto.model.LottoSeller
import lotto.model.Lottoes
import lotto.model.ProfitCalculator
import lotto.model.WinningRank
import lotto.view.InputView
import lotto.view.OutputView

class GameController {

    private val inputView = InputView()
    private val outputView = OutputView()
    private val validator = InputValidator()
    private lateinit var lottoes: Lottoes
    private lateinit var lottoSeller: LottoSeller

    private var payment = 0
    private var lottoTicketCounts = 0

    fun start() {
        buyLottoes()
        showLottoInformation()

        val winningNumbers = getValidWinningNumbers()
        val bonusNumber = getValidBonusNumber(winningNumbers)

        val winningResult = lottoes.calculatedLottoesResult(winningNumbers, bonusNumber)
        showLottoResult(winningResult)

        val profitCalculator = ProfitCalculator(winningResult, payment)
        outputView.totalProfitRate(profitCalculator.getProfitRate())
    }

    private fun buyLottoes() {
        var isValid: Boolean
        do {
            outputView.purchasePrompt()
            payment = inputView.purchaseAmount()
            isValid = validator.validatePurchaseAmount(payment)
            lottoSeller = LottoSeller(payment)
            lottoTicketCounts = lottoSeller.calculatedLottoTicketCount()
            lottoes = Lottoes(lottoTicketCounts)
        } while (!isValid)
    }

    private fun showLottoInformation() {
        outputView.lottoNumbersPrompt(lottoTicketCounts)
        outputView.lottoNumbers(lottoes)
    }

    private fun getValidWinningNumbers(): Set<Int> {
        var isValid: Boolean
        var winningNumbers: List<Int>
        do {
            outputView.inputLottoNumbersPrompt()
            winningNumbers = inputView.lottoNumbers()
            isValid = validator.validateLottoNumbers(winningNumbers)
        } while (!isValid)
        return winningNumbers.toSet()
    }

    private fun getValidBonusNumber(winningNumbers: Set<Int>): Int {
        var isValid: Boolean
        var bonusNumber: Int
        do {
            outputView.inputBonusNumberPrompt()
            bonusNumber = inputView.bonusLottoNumber()
            isValid = validator.validateBonusNumber(bonusNumber, winningNumbers)
        } while (!isValid)
        return bonusNumber
    }


    private fun showLottoResult(winningResult: Map<WinningRank, Int>) {
        outputView.winningResultPrompt()
        outputView.winningResult(winningResult)
    }
}