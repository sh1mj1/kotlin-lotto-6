package lotto.model

class Lottoes(
    lottoTicketCount: Int,
    lottoGenerator: LottoesGenerator = LottoesGeneratorImp(lottoTicketCount)
) {
    private val lottoes: List<Lotto> = lottoGenerator.generateLottoes()
    private val lottoesResult: MutableMap<WinningRank, Int> =
        WinningRank.values().associateWith { 0 }.toMutableMap()

    fun calculatedLottoesResult(
        winningNumbers: Set<Int>,
        bonusNumber: Int
    ): Map<WinningRank, Int> {
        lottoes.forEach {
            val numMatchCount = it.calculateMatchingCount(winningNumbers)
            val bonusNumberMatch = it.containBonusNumber(bonusNumber)
            val result = it.calculateLottoRank(numMatchCount, bonusNumberMatch)

            lottoesResult[result] = (lottoesResult[result] ?: 0) + 1
        }
        return lottoesResult
    }

    override fun toString(): String {
        return lottoes.joinToString("\n") {
            it.toString()
        }
    }
}