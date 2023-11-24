package lotto.model

class Lottoes(lottoTicketCount: Int) {
    private var lottoGenerator: LottoGenerator = LottoGenerator()

    var lottoes: MutableList<Lotto> = mutableListOf()
    private var lottoesResult: MutableMap<WinningRank, Int> =
        WinningRank.values().associateWith { 0 }.toMutableMap()

    init {
        for (i in 1..lottoTicketCount) {
            lottoes.add(lottoGenerator.generateLotto())
        }
    }

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