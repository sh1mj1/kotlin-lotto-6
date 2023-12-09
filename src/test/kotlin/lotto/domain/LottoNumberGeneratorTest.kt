package lotto.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertDoesNotThrow

class LottoNumberGeneratorTest {

    private lateinit var lottoNumberGenerator: LottoNumberGenerator

    @BeforeEach
    fun setUp() {
        lottoNumberGenerator = LottoNumberGenerator()
    }

    @RepeatedTest(1000)
    fun `정상적인 랜덤 로또 번호 생성 테스트`() {
        assertDoesNotThrow { lottoNumberGenerator.generated() }
    }

}