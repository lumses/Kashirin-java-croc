packeage ru.croc.task7

public class IllegalMoveException extends Exception{
    private ChessPosition a;
    private ChessPosition b;

    public IllegalMoveException(ChessPosition a, ChessPosition b){
        this.a = a;
        this.b = b;
    }

    @Override
    public String getMessage() {
        return "конь так не ходит: " + p1 + " -> " + p2;
    }
}