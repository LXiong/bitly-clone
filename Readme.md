# Bitly-Clone

## Introduction
As a Java web-app, this is a simplied clone to the famous Bitly (https://bitly.com) service.
The main function is:
> Given an input url, create a 'shortened' url that will redirect a user to the  original content.

## Purpose
To demostracte my java programming level and software engineering abaility, since this is originally a task from a technical test.

The following de-faco standard technologies were used for demostration purpose:
* [Spring Framework] - for dependency injection and restful controller
* [Hibernate] - for ORM
* [Log4j2] - for logging
* [JUnit] - for unit testing
* [JMock] - for mocking at unit testing

## Installation
1. deploy the bitly-clone.war to the servlet container
2. create the config file: file:${catalina.home}/conf/bitly.properties

properties:
* bitly.domainUrl - the base url of the bitly-clone war deployed
* bitly.hashMethod - Hash method for shortening the url, see https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html#getInstance(java.lang.String)


example:
```
bitly.domainUrl=https://bitly-clone
bitly.hashMethod=MD5
```

## Restful API
### list all shortened urls
```
method: GET
path: /list
requestbody: none
responsebody: json list to show all shortened urls
```
example response:
```
[
{
  "originalUrl" : "http://apple.com/123/123",
  "shortenUrl": "https://bitly-clone.com/4999af664c462fb0c4edfc2171da1f9b"
},
{
  "originalUrl" : "http://test.test.test.test.test.com",
  "shortenUrl": "https://bitly-clone.com/4999af67812k2fb0c4e12ee671da1fff"
},

```


### create a shortened url
```
method: POST
path: /
requestbody: raw string of the original url to be inputed
responsebody: json object contains the shortened url along with the original url
```
example response:
```
{
  "originalUrl" : "http://apple.com/123/123",
  "shortenUrl": "https://bitly-clone.com/4999af664c462fb0c4edfc2171da1f9b"
}
```

### redirect url
```
method: GET
path: /{shortened url}
responseheader: http 301 status would response with location equals the original url 
```

