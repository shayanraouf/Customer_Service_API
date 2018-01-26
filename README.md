# Java Spring SQL App
This is a simple Java Spring Framework Application that utilizes SQL DB.
This is meant to be used as a skeleton to get a head start in a
Java Spring Framework Application with SQL DB configuration that you want to build.
The model used for this project is a Customer class and basic customer related action and data.

**Table of Contents**
- [Endpoints](#endpoints)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Getting Started](#getting-started)
## Endpoints
| Location | URL | Swagger UI |
|:--------:|-----|-----|
| Production | http://localhost:3000/swagger-ui.html#! | [LINK](http://localhost:3000/swagger-ui.html#!) |

## Prerequisites

* Download and install SQL
* Run this script

CREATE DATABASE customer_db;

use customer_db;
CREATE TABLE customers (
	UUID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255),
	PostalCode varchar(255),
    Country varchar(255)
);

## Installation

## Getting Started