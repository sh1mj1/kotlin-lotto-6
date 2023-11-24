package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ProfitCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideLottoesResultAndPayment")
    fun `로또 결과와 지불 금액으로 수익률을 계산한다`(
        lottoesResult: Map<WinningRank, Int>,
        payment: Int,
        expectedProfitRate: Double
    ) {
        // when
        val profitCalculator = ProfitCalculator(lottoesResult, payment)
        val result = profitCalculator.getProfitRate()

        // then
        assertThat(result).isEqualTo(expectedProfitRate)
    }

    companion object {
        @JvmStatic
        fun provideLottoesResultAndPayment(): Stream<Arguments> = Stream.of(
            Arguments.of(
                mapOf(WinningRank.FIFTH to 2, WinningRank.FAILURE to 1),
                5_000,
                200.0
            ),
            Arguments.of(
                mapOf(WinningRank.FIRST to 1),
                1_000,
                200_000_000.0
            ),
            Arguments.of(
                mapOf(WinningRank.FAILURE to 10),
                10_000,
                0.0
            ),
        )
    }
}