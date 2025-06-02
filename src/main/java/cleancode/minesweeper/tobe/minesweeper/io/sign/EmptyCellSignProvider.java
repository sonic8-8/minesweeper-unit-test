package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;

public class EmptyCellSignProvider implements CellSignProvidable {

    private static final String EMPTY_SIGN = "■";

    @Override
    public String provide(CellSnapshot snapshot) {
        return EMPTY_SIGN;
    }

    @Override
    public boolean supports(CellSnapshot snapshot) {
        return snapshot.isSameStatus(CellSnapshotStatus.EMPTY);
    }
}
