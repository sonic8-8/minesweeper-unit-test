package cleancode.minesweeper.tobe.minesweeper.board.position;

import java.util.List;

public interface LandMinePositionSelector {
    List<CellPosition> extractLandMinePositions(CellPositions cellPositions, int landMineCount);
}
