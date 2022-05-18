import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VendorTest {
    @Test
    void 다른_로또가_있으면_총_가격에_포함하여_구매할_수_있다() {
        Lotto manual = new Lotto();
        manual.add(new LottoNumbers("1, 2, 3, 4, 5, 6", Application.SEPARATOR));
        Lotto lotto = new Vendor().buy(Vendor.LOTTO_PRICE, manual);

        assertThat(lotto).isInstanceOf(Lotto.class);
        assertThat(lotto).contains(
                new LottoNumbers(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );
    }

    @Test
    void 로또를_구매할_수_있다() {
        assertThat(new Vendor().buy(Vendor.LOTTO_PRICE, new Lotto())).isInstanceOf(Lotto.class);
    }

    @Test
    void 가격_미만이면_로또를_구매할_수_없다() {
        assertThatThrownBy(() -> new Vendor().buy(Vendor.LOTTO_PRICE - 1, new Lotto())).isInstanceOf(RuntimeException.class);
    }
}
