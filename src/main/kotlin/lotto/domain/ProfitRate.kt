package lotto.domain

import lotto.domain.data.Prize

class ProfitRate {

    fun calculatedProfitRate(totalPrizes: Map<Prize, Int>, payment: Int): Float {
        val totalProfit = calculatedProfit(totalPrizes)
        return totalProfit / payment
    }

    private fun calculatedProfit(totalPrizes: Map<Prize, Int>): Float =
        totalPrizes.entries.sumOf { (prize, count) -> prize.prize * count }.toFloat()
}