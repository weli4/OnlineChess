package chess.entity;

public enum PieceType {
    PAWN("pawn"), ROOK("rook"), KNIGHT("knight"), BISHOP("bishop"), QUEEN("queen"), KING("king");

    private String typeValue;
    PieceType(String typeValue) {
        this.typeValue = typeValue;
    }
}
