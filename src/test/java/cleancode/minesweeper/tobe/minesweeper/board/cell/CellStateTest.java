package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellStateTest {

    @DisplayName("셀에 깃발을 꽂을 수 있다.")
    @Test
    void flag() {
        // given
        CellState cellState = CellState.initialize();

        // when
        cellState.flag();

        // then
        assertThat(cellState.isFlagged()).isTrue();
    }

    @DisplayName("셀을 열 수 있다.")
    @Test
    void open() {
        // given
        CellState cellState = CellState.initialize();

        // when
        cellState.open();

        // then
        assertThat(cellState.isOpened()).isTrue();
    }
}
