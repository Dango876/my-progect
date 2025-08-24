1. private static final String url - константы пишутся В_ВЕРХНЕМ_РЕГИСТРЕ_ЧЕРЕЗ_НИЖНЕЕ_ПОДЧЕРКИВАНИЕ - YES
2. sql запросы или сразу пиши в параметры метода или вынеси в константы - YES
3. private void executeUpdate(String sql) { -  этот метод убери - YES
4. ролбэки должны быть только на DML операциях - YES
5. if (transaction != null) transaction.rollback(); -  не пиши в одну строчку - YES
6. System.out.println("User " + name + " добавлен в базу"); 
6. - засоряет код. Если надо для отладки - убирай перед тем как пушить - YES