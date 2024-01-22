# BackTask

Тестовое задание на Backend направление

## Требования

- Java 17.0.7
- Apache Maven

## Описание

**Endpoints:**

<details open>
 <summary>
 <code>POST</code> <code><b>/api/v1/intervals/merge</b></code>
 </summary>

##### Параметры

> | name      |  type     | data type               | description                                             |
> |-----------|-----------|-------------------------|---------------------------------------------------------|
> | kind      |  required | Enum [digits, letters]  | Тип данных в передаваемых интервалах - цифры или буквы  |

##### Запрос `digits`

> ```shell
>  curl --location 'http://localhost:8080/api/v1/intervals/merge?kind=digits' \
>       --header 'Content-Type: application/json' \
>       --data '[
>                 [1, 4],
>                 [3, 6],
>                 [8, 10]
>               ]'
> ```

##### Ответ

> ```json
>  [[1, 6], [8, 10]]
> ```

##### Запрос `letters`

> ```shell
>  curl --location 'http://localhost:8080/api/v1/intervals/merge?kind=letters' \
>       --header 'Content-Type: application/json' \
>       --data '[
>                 ["a", "f"],
>                 ["d", "j"],
>                 ["r", "z"]
>               ]'
> ```

##### Ответ

> ```json
>  [["a", "j"], ["r", "z"]]
> ```

</details>

------------------------------------------------------------------------------------------

<details open>
 <summary>
 <code>GET</code> <code><b>/api/v1/intervals/min</b></code>
 </summary>

##### Параметры

> | name      |  type     | data type               | description                                                      |
> |-----------|-----------|-------------------------|------------------------------------------------------------------|
> | kind      |  required | Enum [digits, letters]  | Тип данных получаемого минимального интервала - цифры или буквы  |

##### Запрос `digits`

> ```shell
>   curl --location 'http://localhost:8080/api/v1/intervals/min?kind=digits' \
>        --data ''
> ```

##### Ответ

> ```json
>  [1, 6]
> ```

##### Запрос `letters`

> ```shell
>   curl --location 'http://localhost:8080/api/v1/intervals/min?kind=letters' \
>        --data ''
> ```

##### Ответ

> ```json
>  ["a", "j"]
> ```

</details>

------------------------------------------------------------------------------------------

## Сборка

1) В командной строке переходим в папку проекта
2) Запускаем команду `mvn package`
3) Ждём сообщения `BUILD SUCCESS`
4) Запускаем программу `java -jar target/BackTask-0.0.1-SNAPSHOT.jar`

Эндпоинты доступны по `http://localhost:8080`

Консоль H2 по `http://localhost:8080/h2`

- url: `jdbc:h2:mem:back_task`
- username: `user`
- password: `user`