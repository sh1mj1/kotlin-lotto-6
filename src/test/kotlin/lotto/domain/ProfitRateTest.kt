package lotto.domain

import lotto.domain.data.Prize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProfitRateTest {
    private lateinit var profitRate: ProfitRate

    @BeforeEach
    fun setUp() {
        profitRate = ProfitRate()
    }

    @Test
    fun `수익률을 게산한다1`() {
        val totalPrizes = mapOf(
            Prize.FIRST to 0,
            Prize.SECOND to 0,
            Prize.THIRD to 0,
            Prize.FOURTH to 0,
            Prize.FIFTH to 1,
            Prize.FAILURE to 9,
        )
        val payment = 10_000

        val result = profitRate.calculatedProfitRate(totalPrizes, payment)
        assertThat(result).isEqualTo(0.5f)
    }

    @Test
    fun `수익률을 게산한다2`() {
        val totalPrizes = mapOf(
            Prize.FIRST to 1,
            Prize.SECOND to 0,
            Prize.THIRD to 0,
            Prize.FOURTH to 0,
            Prize.FIFTH to 0,
            Prize.FAILURE to 9,
        )
        val payment = 10_000

        val result = profitRate.calculatedProfitRate(totalPrizes, payment)
        assertThat(result).isEqualTo(200000.0f)
    }

    @Test
    fun `수익률을 게산한다3`() {
        val totalPrizes = mapOf(
            Prize.FIRST to 0,
            Prize.SECOND to 0,
            Prize.THIRD to 0,
            Prize.FOURTH to 1,
            Prize.FIFTH to 1,
            Prize.FAILURE to 8,
        )
        val payment = 10_000

        // 55,000 / 10,000 = 5.5
        val result = profitRate.calculatedProfitRate(totalPrizes, payment)
        assertThat(result).isEqualTo(5.5f)
    }
}
