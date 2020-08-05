# Test tasks

## Task 1
- [x] Вытащить 10 json'ов отсюда https://randus.org/api.php.
- [x] Написать два метода которые выводят в файлы отсортированные элементы:
- [x] по name
- [x] по date 

##### args для запуска: 
* **task1name** - для запроса списка из 10 объектов и их вывод в файл в отсортированном **по имени** виде
* **task1date** - для запроса списка из 10 объектов и их вывод в файл в отсортированном **по дате** виде
* **task1both** - для вызова двух предыдущих методов вместе на одном наборе данных

З.Ы. - файлы с результатом будут лежать в **./build/tmp/\*.json**

## Task 2
- [x] Написать функцию которая на вход принимает List<String> и выводит в консоль элементы и количество повторений без учета регистра, отсортированные в лексикографическом порядке. 
- [x] Элементы должны быть выведены с большой буквы(первая большая, остальные маленькие) для символов латинского алфавита

##### args для запуска: 
* **task2**

### Команда для запуска:
* **Windows** - *.\gradlew.bat bootRun -Pargs=<your_args>*
* **Linux/Mac** - *gradlew bootRun -Pargs=<your_args>*

#### Пример команды:
    .\gradlew.bat bootRun -Pargs=task1both
    
    
## Task 3

- [x] Найти клиентов, у которых нет почты. Вывести его ФИО. 

```select * from client where email = '' or email is null;```

- [x] Найти самый дорогой продукт. Вывести его название и цену.

```select * from product where price = (select max(p.price) from product p);```

- [x] Найти продукт, который еще не куплен клиентами. Вывести его название.

```select * from product p left join client_and_product cp on p.id = cp.product_id where cp.id is null;```
