package lotto.domain

import lotto.domain.data.Lotto
import lotto.domain.data.PrizeCondition
import lotto.domain.data.WinningLotto

class PrizeCalculator {

    fun calculatePrize(lotto: Lotto, winningLotto: WinningLotto): PrizeCondition {
        val matchingNumberCount = lotto.countMatchingNumber(winningLotto.lotto)
        val hasBonusNumber = lotto.hasBonusNumber(winningLotto.bonusNumber)

        val conditions = listOf(
            (matchingNumberCount == 6) to PrizeCondition.FIRST,
            (matchingNumberCount == 5 && hasBonusNumber) to PrizeCondition.SECOND,
            (matchingNumberCount == 5) to PrizeCondition.THIRD,
            (matchingNumberCount == 4) to PrizeCondition.FOURTH,
            (matchingNumberCount == 3) to PrizeCondition.FIFTH,
            (matchingNumberCount == 0) to PrizeCondition.FAILURE
        )

        return conditions.firstOrNull { it.first }?.second ?: PrizeCondition.FAILURE
    }

}