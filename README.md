1. Removed- .idea убрать из репозитория
2. Delete - public static Util getInstance() { - этот метод не надо
3. Delete - private static Properties getProps() throws IOException { - это не надо
4. Connected to the database - getConnection() {- в этом методе надо реализовать подключение к БД.
5. Private field - UserServiceImpl - заприватить поля No static - private static final
6. Connection conn - не делать статичным
