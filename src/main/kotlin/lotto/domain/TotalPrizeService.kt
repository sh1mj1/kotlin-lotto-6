package lotto.domain

import lotto.domain.data.Lotteries
import lotto.domain.data.Prize
import lotto.domain.data.WinningLotto

class TotalPrizeService(private val prizeCalculator: PrizeCalculator) {
    fun calculateTotalPrizes(lotteries: Lotteries, winningLotto: WinningLotto): Map<Prize, Int> {
        val totalPrizes = Prize.values().associateWith { 0 }.toMutableMap()
        lotteries.lottoes.forEach {
            val prize = prizeCalculator.calculatePrize(it, winningLotto)
            totalPrizes.merge(prize, 1, Integer::sum)
        }
        return totalPrizes
    }
}