package lotto.domain

import lotto.domain.data.BonusNumber
import lotto.domain.data.Lotto
import lotto.domain.data.Prize
import lotto.domain.data.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PrizeCalculatorTest {

    private lateinit var prizeCalculator: PrizeCalculator

    @BeforeEach
    fun setUp() {
        prizeCalculator = PrizeCalculator()
    }

    @Test
    fun `로또 번호와 당첨 번호를 비교해서 1등 상금 얻기`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 6)), BonusNumber(7))

        val result = prizeCalculator.calculatePrize(lotto, winningLotto)
        assertThat(result).isEqualTo(Prize.FIRST)
    }

    @Test
    fun `로또 번호와 당첨 번호를 비교해서 2등 상금 얻기`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 7)), BonusNumber(6))
        // 5개 일치, 보너스번호 일치.
        val result = prizeCalculator.calculatePrize(lotto, winningLotto)
        assertThat(result).isEqualTo(Prize.SECOND)
    }

    @Test
    fun `로또 번호와 당첨 번호를 비교해서 3등 상금 얻기`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 5, 7)), BonusNumber(16))
        // 5개 일치, 보너스번호 일치 안 함
        val result = prizeCalculator.calculatePrize(lotto, winningLotto)
        assertThat(result).isEqualTo(Prize.THIRD)
    }

    @Test
    fun `로또 번호와 당첨 번호를 비교해서 4등 상금 얻기`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 4, 15, 7)), BonusNumber(16))
        // 4개 일치, 보너스번호 일치 상관 없음.
        val result = prizeCalculator.calculatePrize(lotto, winningLotto)
        assertThat(result).isEqualTo(Prize.FOURTH)
    }

    @Test
    fun `로또 번호와 당첨 번호를 비교해서 5등 상금 얻기`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = WinningLotto(Lotto(listOf(1, 2, 3, 14, 15, 17)), BonusNumber(16))

        val result = prizeCalculator.calculatePrize(lotto, winningLotto)
        assertThat(result).isEqualTo(Prize.FIFTH)
    }

    @Test
    fun `로또 번호와 당첨 번호를 비교해서 꽝 상금 얻기`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningLotto = WinningLotto(Lotto(listOf(11, 12, 13, 14, 15, 17)), BonusNumber(6))

        val result = prizeCalculator.calculatePrize(lotto, winningLotto)
        assertThat(result).isEqualTo(Prize.FAILURE)
    }
}