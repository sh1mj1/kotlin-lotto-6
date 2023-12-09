package lotto.view

import lotto.domain.data.Lotteries
import lotto.domain.data.Prize

class OutputView {

    fun showBoughtLottoes(lotteries: Lotteries) {
        println("${lotteries.lottoes.count()}를 구매했습니다.")
        println(lotteries.lottoes.joinToString("\n") {
            it.numbersOutput(LOTTO_NUMBER_START_DELIMITER, LOTTO_NUMBER_END_DELIMITER)
        })
    }

    fun showWinningPrizes(totalPrize: Map<Prize, Int>) {
        showWinningResultHeader()
        println("3개 일치 (5,000) - ${totalPrize[Prize.FIFTH]}개")
        println("4개 일치 (50,000원) - ${totalPrize[Prize.FOURTH]}개")
        println("5개 일치 (1,500,000원) - ${totalPrize[Prize.THIRD]}개")
        println("5개 일치, 보너스 볼 일치 (30,000,000원) - ${totalPrize[Prize.SECOND]}개")
        println("6개 일치 (2,000,000,000원) - ${totalPrize[Prize.FIRST]}개")
    }

    fun showProfitRate(profitRate: Float) {
        val percentage = profitRate * 100
        val formattedPercentage = String.format("%.1f%%", percentage)
        println("총 수익률은 ${formattedPercentage}입니다.")
    }

    private fun showWinningResultHeader() = println(
        """
            당첨 통계
            ---
        """.trimIndent()
    )


    /*
    3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
총 수익률은 62.5%입니다.
     */

    companion object {
        const val LOTTO_NUMBER_START_DELIMITER = '['
        const val LOTTO_NUMBER_END_DELIMITER = ']'
    }
}