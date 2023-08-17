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

Язык очень сильно ограничивает использование сырых литералов (строк и чисел), и по умолчанию их использование запрещено
везде, кроме функций `num`, `str`, `echo` и `reply`, о которых речь пойдет ниже. Все остальные функции могут принимать
только идентификаторы.

### Функции `num`  и `str`

Задают числовую и строковую переменные соответственно. Всегда принимают литерал, однако поддерживают интерполяцию
через `$`. Если нужен сам символ `$`, используйте `\$`.

```
begin

num x 1
str s lol, x=$x
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
echo $id $messageId $username $firstName $lastName
```

Это ваш идентификатор, идентификатор сообщения, никнейм, имя и фамилия соответственно.

Если вы отвечаете на чье-то сообщение, в скрипте также будут доступны переменные относящиеся к оригинальному сообщению:

```
begin
echo $originalId $originalMessageId $originalUsername $originalFirstName $originalLastName
echo Вы также можете получить идентификатор чужого файла: $originalFileId
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

Раздел находится в разработке

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
