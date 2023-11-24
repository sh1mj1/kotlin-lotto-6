package lotto.model

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange
import lotto.config.GameConfig.END_NUMBER
import lotto.config.GameConfig.LOTTO_COUNTS
import lotto.config.GameConfig.START_NUMBER

class LottoesGeneratorImp(private val lottoTicketCount: Int): LottoesGenerator {
    override fun generateLottoes(): List<Lotto> {
        val lottoes = mutableListOf<Lotto>()
        for (i in 0 until lottoTicketCount) {
            lottoes.add(
                Lotto(pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, LOTTO_COUNTS).sorted().toList())
            )
        }
        return lottoes
    }
}