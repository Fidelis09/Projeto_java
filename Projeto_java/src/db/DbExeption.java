package db;


public class DbExeption  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DbExeption(String msg) {
        super(msg);
    }

    public DbExeption(String msg, Throwable cause) {
        super(msg, cause);
    }

}
