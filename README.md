Web scraping is the process of extracting data from websites programmatically. It involves making HTTP requests to web pages, parsing the HTML content of those pages, and then extracting and transforming the desired data for various purposes. Web scraping is used in a wide range of applications, including data collection, data analysis, price comparison, content aggregation, and more.

## 🔧 Technologies & Tools

- Programming Languages: Java - 11
- Backend Frameworks: Spring Boot
- Version Control: Git



## API Reference

#### Get all items into .Csv file

```http
  POST /websuctionmachine/SuctionTOCSVl
```

```json

{
    "filename":"Fidel_Castro.csv",
    "url":"https://en.wikipedia.org/wiki/Fidel_Castro"
}

```
#### Get All data as Json

```http
  POST websuctionmachine/SuctionByAll
```

```json

{
    "url":"https://en.wikipedia.org/wiki/Fidel_Castro"
}

```
#### Get All data as Json by tag

```http
  POST websuctionmachine/SuctionByTag
```

```json

{
    "url":"https://en.wikipedia.org/wiki/Queen_Victoria",
    "fields":"p"
}

```
