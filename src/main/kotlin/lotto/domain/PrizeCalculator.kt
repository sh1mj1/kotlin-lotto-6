package lotto.domain

import lotto.domain.data.Lotto
import lotto.domain.data.Prize
import lotto.domain.data.WinningLotto

class PrizeCalculator {

    fun calculatePrize(lotto: Lotto, winningLotto: WinningLotto): Prize {
        val matchingNumberCount = lotto.countMatchingNumber(winningLotto.lotto)
        val hasBonusNumber = lotto.hasBonusNumber(winningLotto.bonusNumber)

        val conditions = listOf(
            (matchingNumberCount == 6) to Prize.FIRST,
            (matchingNumberCount == 5 && hasBonusNumber) to Prize.SECOND,
            (matchingNumberCount == 5) to Prize.THIRD,
            (matchingNumberCount == 4) to Prize.FOURTH,
            (matchingNumberCount == 3) to Prize.FIFTH,
            (matchingNumberCount == 0) to Prize.FAILURE
        )

        return conditions.firstOrNull { it.first }?.second ?: Prize.FAILURE
    }

}