package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoesTest {
    private lateinit var expectedLottoes: Map<WinningRank, Int>

    @Test
    fun `구매한 모든 로또의 당첨 결과를 Map 의 형태로 리턴한다`() {
        // given
        val lottoes = Lottoes(6000)
        val winningNumbers = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        lottoes.lottoes = mutableListOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(1, 2, 3, 4, 5, 7)),
            Lotto(listOf(1, 2, 3, 11, 12, 14)),
            Lotto(listOf(11, 22, 33, 2, 1, 3)),
            Lotto(listOf(11, 22, 33, 17, 27, 37)),
            Lotto(listOf(11, 22, 33, 8, 27, 9))
        )

        // when
        val result = lottoes.calculatedLottoesResult(winningNumbers, bonusNumber)
        expectedLottoes = mapOf(
            WinningRank.FIRST to 1,
            WinningRank.SECOND to 1,
            WinningRank.THIRD to 0,
            WinningRank.FOURTH to 0,
            WinningRank.FIFTH to 2,
            WinningRank.FAILURE to 2
        )

        // then
        assertThat(result).isEqualTo(expectedLottoes)
    }
}