# core-interview-assignment
Copper Core team interview assignment

In this exercise you will be asked to implement the new BalanceService.  


## Scala
This service will connect with the external apis (located in com.example.api): `TradeApi` and `ClientApi` and expose following methods:  
    - 1st Stage: `def getClientTotalTradeVolume(clientId: String): Future[Double]`    
    - 2nd Stage: `def createTradeReport(country: String): Future[TradeReport]`  

Note that the current project is set up to use scala 2.13.  
In case of using a different version of scala, you may need to adapt the project files.   
 - `sbt build` will build the project   
 - `sbt test` will run the tests


## Java
This service will connect with the external apis (located in com.example.api): `TradeApi` and `ClientApi` and expose following methods:  
  - 1st Stage: `Double getClientTotalTradeVolume(String clientId)`   
  - 2nd Stage:`Future<TradeReport> createTradeReport(String country)`  


- `gradle javaCompile` will build the project  
- `gradle test` will run the tests