package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.BoardIndexConverter;
import cleancode.minesweeper.tobe.position.CellPosition;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

    public static final Scanner SCANNER = new Scanner(System.in);
    @Override
    public String getUserInput() {
        return SCANNER.nextLine();
    }

    @Override
    public CellPosition getCellPositionFromUser() {
        String userInput = SCANNER.nextLine();
        int colIndex = boardIndexConverter.getSelectedColIndex(userInput);
        int rowIndex = boardIndexConverter.getSelectedRowIndex(userInput);
        return CellPosition.of(rowIndex, colIndex);
    }
}
