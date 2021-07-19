Сигнатура команды запуска:
java -Dhiber="hibernate.cfg.xml" -Dconfig=<название свойства и путь к конфигу свойств> -Dlog4j.configurationFile=<название свойства и путь к конфигу логгера> -jar <название приложения> <№ Лабораторной> <данные>


<данные вводятся последовательно через пробел, массивы данных вводятся через запятую>
 11 21,22 33 45 50


LAB1:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab1 get_schemas

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab1 get_tables

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab1 get_role_tables

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab1 get_tables_type



LAB2:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab2 save Nina dis 310 other

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab2 get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab2 update 1 den

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab2 delete 2



LAB3:

strategy1:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy1 save sasha 100 101 102 109


java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy1 get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy1 update 1 tuu

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy1 delete 1


strategy2:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy2 save sasha 100 101 102 109

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy2 get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy2 update 1 Fifa

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy2 delete 1

strategy3:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy3 save sasha 100 101 102 109

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy3 get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy3 update 1 Ura

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy3 delete 1


strategy4:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy4 save sasha 100 101 102 109

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy4 get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy4 update 1 Fifa

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab3 strategy4 delete 1


LAB4:

Set:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 set save hiho 0.001 tefal

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 set get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 set update 1 Rico

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 set delete 1


List:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 list save gis ins44

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 list get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 list update 1 titos

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 list delete 2


Map:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 map save hilo 0.002 tefale

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 map get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 map update 1 Aran

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab4 map delete 2


LAB5:

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab5 save 3 43 13 33 24,25

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab5 get_by_id 1

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab5 update 1 26,27 42

java -Dhiber="hibernate.cfg.xml" -Dconfig="enviroment.properties" -Dlog4j2.configurationFile="log4j2.properties" -jar hibernate.jar lab5 delete 2 43







