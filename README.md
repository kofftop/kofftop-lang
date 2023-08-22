# kofftop

Язык программирования для kofftop чата. Создан под грибами, медитацией в лесу, депрессией, нехваткой времени, и другими
вещами о которых не стоит здесь распространяться.

Чтобы узреть суть©, читайте нижеследующие абзацы по порядку.

## Концепт

Язык создавался так, чтобы скрипт можно было выполнить за один проход по символам. Отсюда ограничения и слегка
~~наркоманская~~ особенная философия языка.

1. Каждый первый идентификатор в строке является вызовом функции
2. Остальные идентификаторы и литералы в строке являются ее аргументами

## Метки скриптов и пользовательских функций

Любой скрипт должен начинаться с `begin`, а пользовательские функции с `args`. Это нужно не для красоты, а для того,
чтобы сообщение было корректно распознано как скрипт или функция. Без этих меток интерпретация не начнется и мы не
увидим сообщения о возникших ошибках соответственно. Если бы язык интерпретировал все сообщения подряд, мы получали бы
много нерелевантных ошибок.

## Идентификаторы

Имена функций и переменных всегда должны начинаться с буквы. После первой буквы можно использовать цифры. Остальные
символы запрещены. При использовании имен функций и переменных, регистр не имеет значения. Это было сделано чтобы
скрипты было удобнее набивать с телефона в чате.

## Литералы

Строковые литералы в Kofftop не требуют обрамления кавычками, и начинаются сразу после пробела после имени функции.
Соответственно язык очень сильно ограничивает использование литералов строк, и по умолчанию их возможно использовать
только в функциях `echo` и `reply`. Все остальные функции принимают имена, числовые или логические литералы. Если
передать в другую функцию строковый литерал, он будет засчитан только до первого пробела.

### Функции `num`, `str` и `bool`

Задают числовую, строковую и логическую переменные соответственно. Всегда принимают литерал, однако поддерживают
интерполяцию через `$`. Если нужен сам символ `$`, используйте `\$`.

```
begin

num x 1
str s lol, x=$x
bool logic true
```

### Функции `echo` и `reply`

Выводят текст и отвечают на сообщения соответственно. Всегда принимают сырой строковый литерал, однако также
поддерживают интерполяцию.

```
begin

echo Hello World
reply lol kek, my Telegram name is $firstName
```

## Предопределенные переменные из Telegram API

Для удобства, каждый скрипт уже содержит заданные заранее переменные, которыми вы можете оперировать. Вот они:

```
begin
echo $text $id $messageId $username $firstName $lastName
```

Это ваш текст, идентификатор, идентификатор сообщения, никнейм, имя и фамилия соответственно.

Если вы отвечаете на чье-то сообщение, в скрипте также будут доступны переменные относящиеся к оригинальному сообщению:

```
begin
echo $originalId $originalMessageId $originalUsername $originalFirstName $originalLastName
echo Вы также можете получить идентификатор чужого файла: $originalFileId
echo Или оригинальный текст: $originalText
```

## Функции `ban` и `unban`

Функция `ban` всегда оперирует идентификатором пользователя и минутами. Функция `unban` просто принимает идентификатор:

```
begin
num minutes 5

ban originalId minutes
echo You banned, $originalFirstName!

unban originalId
echo Now you are free, $originalFirstName!
```

Чтобы никого не банили слишком жестоко, максимальное время бана ограничивается.

## Математические функции

### `sum`

Складывает два аргумента, например

```
begin
num x 5
num y 2
sum result x y
```

Здесь сумма `x` и `y` будет помещена в переменную `result`, вместо которой может быть любое другое имя.
Функция `sum` также может складывать строки, списки и диапазоны.

### `sub`

Работает аналогично функции `sum`, вычитает из первого аргумента второй.

```
begin
num x 5
num y 2
sub result x y
```

Точно также можно вычитать из строки подстроку, список из списка или элемент из списка.

### `mul` и `div`

Функции умножения и деления, работают аналогично вышеописанным. Могут оперировать строкой и числом, повторяя или дробя
строку на кусочки.

### `sqr`, `sqrt` и `abs`

Возведение в квадрат, квадратный корень и модуль соответственно

```
begin
num x 4
sqr y x
sqrt j y
abs j j
```

### `inc` и `dec`

Инкремент и декремент соответственно

```
begin
num x 4
inc x x
dec y x
```

## Собственные функции и функция `ret`

Как уже упоминалось выше, используя маркер `args`, вы можете превратить свое сообщение в вызываемую функцию.
Проще всего это будет пояснить непосредственно на примере. Вы хотите сделать функцию, возвращающую квадрат числа, и
отправляете сообщение:

```
args n
mul n n n
ret n
```

Обратите внимание, в этом примере, для того чтобы вернуть значение из собственной функции, вы должны использовать
функцию `ret`.

Бот распознает функцию, сохранит ее в чате как текстовый файл и укажет в сообщении идентификатор этого файла. Обладая
идентификатором функции, вы сможете вызывать ее, определив перед этим через `fun`. Например, идентификатор файла
это `cba2`. Тогда вызвать в обычном скрипте эту функцию вы сможете следующим образом

```
begin
fun sqr cba2
num x 2
cba2 res x
echo $x^2=$res
```

## Списки и диапазоны

### Функция `list`

Создает пустой список произвольных объектов

```
begin
list myCollection
num x
sum newList myCollection x
```

Обратите внимание, вы можете добавить в список новый элемент, породив новый список

### Функции `range` и `until`

Задают закрытый и открытый диапазоны включительно

```
begin
range myClosedRange 1 150
until myOpenRange 1 150
```

В примере выше, первый диапазон будет включать в себя `150`, а второй закончится на `149`

### Функции `at` и `rem`

Получают n-ый элемент (списка или строки) и удаляют соответственно

```
begin
range list 1 10

at el list 2
rem newList list 2
```

### Функция `indices`

Возвращает все индексы коллекции

```
begin
list lst
num n 789.534
sum lst lst n
num n 0.4
sum lst lst n
num n 98000
sum lst lst n

indices indices lst
```

В примере выше мы заполняем коллекцию `lst` тремя произвольными числами, и записываем их индексы в `indices`,
это `0`, `1`, и `2`.

## Условия

Вам желательно понимать [собственные функции](#собственные-функции-и-функция-ret) для понимания этого раздела.

### Функция `ifeq`

Проверяет два объекта на эквивалентность по значению, и, если они равны, выполняет любую функцию, встроенную либо
собственную

```
begin
num x 1
num y 1
ifeq x y echo Внимание! $x = $y
```

### Функции `ifmore`, `ifless`, `ifin`, `ifnotin`

Работают по аналогичному принципу, проверяя является ли первый аргумент больше второго (`ifmore`), меньше
второго (`ifless`), входит ли первый аргумент во второй (`ifin`), или не входит (`ifnotin`).

## Циклы и трансформации списков

Для понимания этого раздела вам необходимо понимать [собственные функции](#собственные-функции-и-функция-ret).

### Функция `each`

Вы можете пройтись циклом по любой коллекции (или строке), используя функцию `each` и любую собственную функцию, при
условии, что собственная функция принимает один элемент:

```
begin
...
...
...
fun handler cbad5
each myList hanlder
```

### Функция `break`

Вы можете преждевременно выйти из each-цикла, если ваша собственная функция вызовет `break`

### Функции `map` и `filter`

Точно также проходят по коллекции (или строке), мутируя и фильтруя соответственно

```
begin
range ids 1 100

fun predicate ....
filter ids ids predicate

fun mapper
map ids ids mapper
```

## Дополнительные функции Telegram API

### Функция `edit`

Редактирует любое собственное сообщение

```
begin
num messageId ...
str newText lol, kek
edit messageId newText
```

### Функция `delete`

Удаляет любое сообщение

```
begin
num messageId ...
delete messageId 
```

## Дополнительные функции работы со строками

### Функции `upper` и `lower`

Выполняют приведение строки к верхнему и нижнему регистру соответственно. Например

```
begin
str aBcDefJjjX
upper ustr str
lower lstr str
```

## Функция `sleep`

Заставляет поток выполнения вашего скрипта уснуть на N секунд

```shell
begin
echo ждите 5 сек
sleep 5
echo лол, дождались
```
