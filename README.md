##### Test task from EasyBot
***


###  RESTful back-end computer store app

#### General Information:
Backend of a store selling computer equipment and components on Java Spring Boot (Conceptual db architecture for custom product fields without hardcoding entities, without dto an mappers).


##### [Specification](https://go2.sytes.net/api/public/dl/On7KuTIx?inline=true)
The following functionality needs to be implemented:
- Adding a product;
- Editing the product;
- View all existing products by type;
- View product by ID.
###### [Demo Postman Collection](https://github.com/alrepin/pcstore/blob/main/pcstore.postman_collection.json)
##### Architectural Features:
<details>
  <summary>Database Schema</summary>
<img src="https://github.com/alrepin/pcstore/blob/main/data/db.png?raw=true" />
</details>  


##### Deployment Instructions:
<details>
  <summary>Docker image building</summary>

```
docker build -t backend-pcstore-easybot .
```
</details>

##### Running in Docker Compose version v2:
`docker compose up -d`
<details>
  <summary>docker-compose.yml listing</summary>

```
version: "3.3"
services:
backend-pcstore-easybot:
image: backend-pcstore-easybot:latest
      container_name: backend-pcstore-easybot
      ports:
        - "8080:8080"
      restart: 'no'
```
</details>


***
