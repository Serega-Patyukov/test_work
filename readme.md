Есть БД, в ней 2 сущности: сотрудники и их гаджеты.
Гаджеты могут быть: ноутбук, стационарный комп, планшет.
У пользователя есть ФИО, телефон, email.
У гаджетов - проц, диагональ монитора, кол-во оперативы, марка, модель.
У одного пользователя может быть несколько гаджетов.

Необходимо написать сервис (REST API),
который позволяет делать все CRUD операции с пользователями и их гаджетами.

http://localhost:27015/swagger-ui/index.html# - тут описание api

http://localhost:27015/actuator/logfile - тут логи