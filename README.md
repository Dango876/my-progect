1. public Session getSession() { - метод должен возвращать SessionFactory (название тоже верни как было) - YES
2. public Connection getConnection() -  убери чтобы не мешалось - YES
3. public void createUsersTable() throws SQLExceptio -  не пробрасывать исключения, обработать на самом нижнем уровне
4. UserDaoHibernateImpl - должна быть 1 SessionFactory на класс, но отдельная Session на каждый метод - YES
5. System.err.println("Ошибка при вставке"); - убирай тоже