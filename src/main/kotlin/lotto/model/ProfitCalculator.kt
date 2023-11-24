package lotto.model

import kotlin.math.round

class ProfitCalculator(private val lottoesResult: Map<WinningRank, Int>, private val payment: Int) {

    fun getProfitRate(): Double {
        val totalProfit = calculateTotalProfit()
        return calculateProfitRate(totalProfit)
    }

    private fun calculateTotalProfit(): Int =
        lottoesResult.entries.sumOf { (rank, count) ->
            rank.prize * count
        }

    private fun calculateProfitRate(totalProfit: Int): Double {
        var profitRate = totalProfit.toDouble() / payment.toDouble() * 100.0
        profitRate = round(profitRate * 10) / 10
        return profitRate
    }
}