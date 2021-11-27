# Billing-System
Clevertec's test task

Develop a console application that implements the functionality of generating a check in a store.<br />
You can run program with commands:

```
javac -sourcepath ./src -d out/production/project src/com/roland/AppRunner.java
java -classpath ./out/production/project com/roland/AppRunner <parameter_set>
```

where the parameter set in format: itemId-quantity (id of product and its quantity).<br />
For example:
```
java -classpath ./out/production/project com/roland/AppRunner 3-1 2-5 5-1 card-1234 
```
should generate and print to console a check that contains the name of the product with id = 3 
in the amount of 1,<br /> 
id = 2 in the amount of 5, etc. Card-1234 means, 
that discount card with id = 1234 was used.<br />
So, the task is print check to console, that contains a list of items, 
their quantity, their price and total sum<br />
including discount by card(if used).
