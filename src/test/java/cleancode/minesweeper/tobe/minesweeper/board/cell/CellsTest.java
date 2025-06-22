package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CellsTest {

    @DisplayName("게임 보드의 셀들을 1차원으로 가공하여, 일급 컬렉션인 Cells로 관리할 수 있다.")
    @Test
    void from() {
        // given
        Cell[][] board = new Cell[2][2];

        board[0][0] = new EmptyCell();
        board[0][1] = new EmptyCell();
        board[1][0] = new EmptyCell();
        board[1][1] = new EmptyCell();

        // when
        Cells cells = Cells.from(board);

        // then
        assertThat(cells).isNotNull();
        assertThat(cells.getCells()).hasSize(4);
    }

    @DisplayName("Cells의 모든 셀이 체크됐는지 확인하여, 게임 승리 조건인지 확인할 수 있다.")
    @Test
    void isAllChecked() {
        // given
        Cell[][] board = new Cell[2][2];

        board[0][0] = new EmptyCell();
        board[0][0].open();

        board[0][1] = new EmptyCell();
        board[0][1].open();

        board[1][0] = new EmptyCell();
        board[1][0].open();

        board[1][1] = new EmptyCell();
        board[1][1].open();

        Cells cells = Cells.from(board);

        // when
        boolean isAllChecked = cells.isAllChecked();

        // then
        assertThat(isAllChecked).isTrue();
    }

    @DisplayName("Cells의 셀이 하나라도 체크되지 않았다면, 게임 승리 조건을 만족하지 않는다.")
    @Test
    void isAllCheckedWithUnCheckedCell() {
        // given
        Cell[][] board = new Cell[2][2];

        board[0][0] = new EmptyCell();
        board[0][0].flag();

        board[0][1] = new EmptyCell();
        board[0][1].open();

        board[1][0] = new EmptyCell();
        board[1][0].open();

        board[1][1] = new EmptyCell();
        board[1][1].open();

        Cells cells = Cells.from(board);

        // when
        boolean isAllChecked = cells.isAllChecked();

        // then
        assertThat(isAllChecked).isFalse();
    }
}
