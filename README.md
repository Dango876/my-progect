1.
System.err.println("Ошибка подключения к базе данных!"); -  лучше не использовать это в качестве логирования

2.
private final Util util = new Util();
private final Connection conn;

public UserDaoJDBCImpl() {
    this.conn = util.getConnection();
} -  сократить до 1-й строчки YES
