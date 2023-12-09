package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoSellerTest {
    private val lottoSeller = LottoSeller()

    @Test
    fun `1,000 원 단위로 지불하지 않으면 예외`() {
        val payment = 1333
        val exception = assertThrows<IllegalArgumentException> {
            lottoSeller.buyLottoTicket(payment)
        }
        assertThat(exception.message).isEqualTo("금액은 1000 단위로 입력해야 한다")
    }

    @ParameterizedTest
    @CsvSource("5000, 5", "10000, 10")
    fun `로또 티켓 구매해서 개수 얻기`(payment: Int, lottoTicketCount: Int) {
        val result = lottoSeller.buyLottoTicket(payment)
        assertThat(result).isEqualTo(lottoTicketCount)
    }
}