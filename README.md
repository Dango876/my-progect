1. private static SessionFactory sessionFactory - не надо делать статичным - YES
2. логику в статичном блоке перенести в метод getession и тоже не делать его статичным - YES
3. проверять внутри методоа sessionFactory на null - YES
4. throws HibernateException  - не пробрасывать исключения YES
5. public static void close() - это не надо вообще YES
6. UserServiceImpl - заприватить поля YES
7. сделать 1 SessionFactory на класс YES
8. sql запросы или вынести в константы или сразу писать в параметры метода YES
