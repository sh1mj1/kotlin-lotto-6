package lotto.view

import lotto.domain.Lotto
import lotto.domain.data.Lotteries
import lotto.domain.data.Prize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OutputViewTest {
    private lateinit var outputview: OutputView

    @BeforeEach
    fun setUp() {
        outputview = OutputView()
    }

    @Test
    fun `로또들 출력 테스트`() {
        val lotteries = Lotteries(
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(1, 3, 5, 7, 9, 11)),
                Lotto(listOf(11, 13, 15, 17, 19, 21))
            )
        )
        outputview.showBoughtLottoes(lotteries)
    }

    @Test
    fun `로또 당첨금 출력 테스트`() {
        val totalPrize = mapOf(
            Prize.FIRST to 1,
            Prize.SECOND to 1,
            Prize.THIRD to 1,
            Prize.FOURTH to 1,
            Prize.FIFTH to 2,
            Prize.FAILURE to 4,
        )
        outputview.showWinningPrizes(totalPrize)
    }


    @Test
    fun `로또 수익률 출력 테스트`() {
        val profitRate = 0.12354f
        outputview.showProfitRate(profitRate)
    }
}