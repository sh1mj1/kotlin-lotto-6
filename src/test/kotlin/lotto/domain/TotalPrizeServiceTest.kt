package lotto.domain

import lotto.domain.data.BonusNumber
import lotto.domain.data.Lotteries
import lotto.domain.data.Prize
import lotto.domain.data.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TotalPrizeServiceTest {
    private lateinit var totalPrizeService: TotalPrizeService

    @BeforeEach
    fun setUp() {
        totalPrizeService = TotalPrizeService(PrizeCalculator())
    }

    @Test
    fun `총 로또 당첨을 얻는다`() {
        val lotteries = Lotteries(
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)), // 1
                Lotto(listOf(1, 2, 3, 4, 5, 7)), // 2
                Lotto(listOf(1, 2, 3, 4, 5, 8)), // 3
                Lotto(listOf(1, 2, 3, 4, 7, 8)), // 4
                Lotto(listOf(1, 2, 3, 4, 8, 9)), // 4
                Lotto(listOf(1, 2, 3, 7, 8, 9)), // 5
                Lotto(listOf(1, 2, 3, 8, 18, 19)), // 5
                Lotto(listOf(1, 2, 25, 27, 39, 41)), // 나머지
                Lotto(listOf(11, 23, 25, 27, 39, 41)),
                Lotto(listOf(1, 2, 7, 27, 39, 41))
            )
        )
        val winningLotto = WinningLotto(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            BonusNumber(7)
        )

        val result = totalPrizeService.calculateTotalPrizes(lotteries, winningLotto)

        assertThat(result).isEqualTo(
            mapOf(
                Prize.FIRST to 1,
                Prize.SECOND to 1,
                Prize.THIRD to 1,
                Prize.FOURTH to 2,
                Prize.FIFTH to 2,
                Prize.FAILURE to 3,
            )
        )
    }
}