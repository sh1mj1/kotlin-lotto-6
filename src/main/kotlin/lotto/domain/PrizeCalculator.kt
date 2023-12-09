package lotto.domain

import lotto.domain.data.Prize
import lotto.domain.data.WinningLotto

class PrizeCalculator {

    fun calculatePrize(lotto: Lotto, winningLotto: WinningLotto): Prize {
        val matchingNumberCount = lotto.countMatchingNumber(winningLotto.lotto)
        val hasBonusNumber = lotto.hasBonusNumber(winningLotto.bonusNumber)

        return Prize.values().firstOrNull {
            it.matchingCount == matchingNumberCount
                    && (it.bonusNumberMatch == hasBonusNumber || it.bonusNumberMatch.not())
        } ?: Prize.FAILURE
    }
}