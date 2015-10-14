# Criteria Query Demo

## Selecting 1 column

Code:

```java
String[] columnNames = {"firstName"};
List<Map<String, Object>> results1 = employeeRepository.findAllSelectingColumns(columnNames);
```

Value of `results1` is:

```
[
  {firstName=FirstName1},
  {firstName=FirstName2},
  {firstName=FirstName3}
]
```

## Selecting 3 columns

Code:

```java
String[] columnNames = {"id", "firstName", "lastName"};
List<Map<String, Object>> results3 = employeeRepository.findAllSelectingColumns(columnNames);
```

Value of `results3` is:

```
[
  {firstName=FirstName1, lastName=LastName1, id=1},
  {firstName=FirstName2, lastName=LastName2, id=2},
  {firstName=FirstName3, lastName=LastName3, id=3}
]
```
