# rent-a-car

https://documenter.getpostman.com/view/12329197/T1LPDmq7

## Account

### URL : api/account/secured/all

Method : GET

Return all Accounts

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 213,
                  "username": "defaultAdmin",
                  "password": "$2a$10$keaaC19X3bLBjJGVAyZCTOH2bFC8IoTcIW5WA3J3F6UP3NIP2aO2.",
                  "email": "",
                  "name": "",
                  "role": "ADMIN"
              },
              {
                  "id": 215,
                  "username": "example",
                  "password": "$2a$10$sSzTAXpTjuxLM630bzySA.EW0y/uKzKBwX.3S4SiNbAeRdRcTQVXe",
                  "email": "emailexample",
                  "name": "nameexample",
                  "role": "CUSTOMER"
              }
          ]

#### Error Responses

Code : 403 Forbidden

Content : 
{
    "timestamp": "2020-08-14T21:15:59.432+0000",
    "status": 403,
    "error": "Forbidden",
    "message": "Forbidden",
    "path": "/api/account/secured/all"
}
______________________________________________
### URL : api/account/secured/:pk

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Account in the database.

Return the Account with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : {
               "id": 211,
               "username": "username1",
               "password": "$2a$10$AcGFjVatpukH4kVTc7P6GOCeLLmUrykU0/35Lv.CMeCvVSqpb4nRi",
               "email": "email1@",
               "name": "name1",
               "role": "ADMIN"
           }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-15T12:22:53.867+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Account not found with id 2110",
    "path": "/api/account/secured/2110"
}

OR

Code : 403 Forbidden

Content : 
{
    "timestamp": "2020-08-15T12:23:50.178+0000",
    "status": 403,
    "error": "Forbidden",
    "message": "Forbidden",
    "path": "/api/account/secured/2110"
}
______________________________________________
### URL : api/account/secured/getMyAccount

Method : GET

Return the Account of currently logged in user

Auth required : YES

#### Success Responses

Code : 200 OK

Content : {
              "id": 213,
              "username": "defaultAdmin",
              "password": "$2a$10$keaaC19X3bLBjJGVAyZCTOH2bFC8IoTcIW5WA3J3F6UP3NIP2aO2.",
              "email": "",
              "name": "",
              "role": "ADMIN"
          }
______________________________________________
### URL : api/account/secured/addAccount

Method : POST

Create the Account for ADMIN

Auth required : YES

Permissions required : User is ADMIN

Data : { "username": string, "password": string, "email": string, "name": string }

#### Success Responses

Code : 200 OK

Content : {
              "id": 225,
              "username": "admin1",
              "password": "$2a$10$LJzzcFL8mNJkGVrrh8hgUeG71T/fzh5uAJoazg3XMfbXifjTiaKL.",
              "email": "email@",
              "name": "name",
              "role": "ADMIN"
          }

#### Error Responses

Code : 400 Bad Request

Content : 
{
    "timestamp": "2020-08-14T20:47:36.584+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Username already exist",
    "path": "/api/account/secured/addAccount"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/account/addAccount

Method : POST

Create the Account for CUSTOMER

Data : { "username": string, "password": string, "email": string, "name": string }

#### Success Responses

Code : 200 OK

Content : {
              "id": 226,
              "username": "username2",
              "password": "$2a$10$f0G.vnbYAn2ApQKYzM2NSeabRuSrSH7Yy1tm4paxi1R4LBiCHIbWG",
              "email": "email2@",
              "name": "name2",
              "role": "CUSTOMER"
          }

#### Error Responses

Code : 400 Bad Request

Content : 
{
    "timestamp": "2020-08-14T20:55:00.569+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Username already exist",
    "path": "/api/account/addAccount"
}
______________________________________________
### URL : api/account/secured/:pk

URL Parameters : pk=[integer] where pk is the ID of the Account in the database.

Method : DELETE

Delete the Account with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

#### Error Responses

Code : 403 Forbidden

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:12:16.294+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Account not found with id 214",
    "path": "/api/account/secured/214"
}
______________________________________________
### URL : api/account/secured/:pk

Method : PUT

URL Parameters : pk=[integer] where pk is the ID of the Account in the database.

Update the Account with specified ID

Auth required : YES

Permissions required : User is ADMIN

Data : { "username": string, "password": string, "email": string, "name": string, "role": string }

#### Success Responses

Code : 200 OK

Content : {
              "id": 215,
              "username": "put",
              "password": "$2a$10$DylRDVjr5zRvZ5G2lHHR9eytbuIedBG0U9CWUA0RlCPEOOKRciKwa",
              "email": "email@",
              "name": "name",
              "role": "CUSTOMER"
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:22:37.690+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Account not found with id 2150",
    "path": "/api/account/secured/2150"
}

OR

Code : 400 Bad Request

Content : 
{
    "timestamp": "2020-08-16T12:17:32.517+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Username already exist",
    "path": "/api/account/secured/215"
}

Code : 403 Forbidden
______________________________________________
### URL : api/account/secured/:pk/bookings

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Account in the database.

Return Bookings for the Account with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 224,
                  "pickUpDate": "2020-11-11",
                  "dropOffDate": "2020-11-11",
                  "price": 32,
                  "carId": 216,
                  "accountId": 213
              }
          ]

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:26:01.448+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Account not found with id 212",
    "path": "/api/account/secured/212/bookings"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/account/secured/myBookings

Method : GET

Return Bookings for currently logged in user

Auth required : YES

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 224,
                  "pickUpDate": "2020-11-11",
                  "dropOffDate": "2020-11-11",
                  "price": 32,
                  "carId": 216,
                  "accountId": 213
              }
          ]
______________________________________________
### URL : api/account/secured/getByUsername/:username

Method : GET

URL Parameters : username=[string] where username is the username of the Account in the database.

Return the Account with specified username

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : {
              "id": 215,
              "username": "put",
              "password": "$2a$10$DylRDVjr5zRvZ5G2lHHR9eytbuIedBG0U9CWUA0RlCPEOOKRciKwa",
              "email": "email@",
              "name": "name",
              "role": "CUSTOMER"
          }

#### Error Responses

Code : 404 Not Found

Content :
{
    "timestamp": "2020-08-14T21:31:32.073+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Account not found with username example",
    "path": "/api/account/secured/getByUsername/example"
}

OR

Code : 403 Forbidden
______________________________________________
## Booking

### URL : api/booking/secured/all

Method : GET

Return all Bookings

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 224,
                  "pickUpDate": "2020-11-11",
                  "dropOffDate": "2020-11-11",
                  "price": 32,
                  "carId": 216,
                  "accountId": 213
              },
              {
                  "id": 232,
                  "pickUpDate": "2020-11-13",
                  "dropOffDate": "2020-11-13",
                  "price": 32,
                  "carId": 216,
                  "accountId": 215
              }
          ]

#### Error Responses

Code : 403 Forbidden
______________________________________________
### URL : api/booking/secured/:pk

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Booking in the database.

Return the Booking with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : {
              "id": 232,
              "pickUpDate": "2020-11-13",
              "dropOffDate": "2020-11-13",
              "price": 32,
              "carId": 216,
              "accountId": 215
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:31:06.862+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Booking not found with id 223",
    "path": "/api/booking/secured/223"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/booking/secured/add

Method : POST

Create the Booking

Auth required : YES

Data : { "pickUpDate": LocalDate, "dropOffDate": LocalDate, "carId": Integer }

#### Success Responses

Code : 200 OK

Content : {
              "id": 232,
              "pickUpDate": "2020-11-13",
              "dropOffDate": "2020-11-13",
              "price": 32,
              "carId": 216,
              "accountId": 215
          }

#### Error Responses

Code : 400 Bad Request

Content : 
{
    "timestamp": "2020-08-14T22:28:51.958+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "pickUpDate must be before dropOffDate",
    "path": "/api/booking/secured/add"
}

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:27:57.891+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Resource not found",
    "path": "/api/booking/secured/add"
}

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:28:18.873+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Currently not possible",
    "path": "/api/booking/secured/add"
}
______________________________________________
### URL : api/booking/secured/:pk

URL Parameters : pk=[integer] where pk is the ID of the Booking in the database.

Method : DELETE

Delete the Booking with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

#### Error Responses

Code : 403 Forbidden

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:43:22.025+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Booking not found with id 223",
    "path": "/api/booking/secured/223"
}
______________________________________________
### URL :api/booking/secured/:pk

Method : PUT

URL Parameters : pk=[integer] where pk is the ID of the Booking in the database.

Update the Booking

Auth required : YES

Permissions required : User is ADMIN

Data : { "pickUpDate": LocalDate, "dropOffDate": LocalDate, "carId": Integer, "accountId": Integer }

#### Success Responses

Code : 200 OK

Content : {
             "id": 224,
             "pickUpDate": "2020-11-11",
             "dropOffDate": "2020-11-11",
             "price": 32,
             "carId": 216,
             "accountId": 213
         }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:40:32.255+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Booking not found with id 2240",
    "path": "/api/booking/secured/2240"
}

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:41:48.929+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 2160",
    "path": "/api/booking/secured/224"
}

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:42:04.066+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Account not found with id 2130",
    "path": "/api/booking/secured/224"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/booking/secured/:pk/car

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Booking in the database.

Return the Car for the Booking with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : {
              "id": 216,
              "make": "make1",
              "model": "model1",
              "imageUrl": "httpp/ff4444/ffffff2",
              "pricePerDay": 32
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:46:11.840+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Booking not found with id 2240",
    "path": "/api/booking/secured/2240/car"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/booking/secured/:pk/account

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Booking in the database.

Return the Account for the Booking with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : {
              "id": 215,
              "username": "put",
              "password": "$2a$10$DylRDVjr5zRvZ5G2lHHR9eytbuIedBG0U9CWUA0RlCPEOOKRciKwa",
              "email": "email@",
              "name": "name",
              "role": "CUSTOMER"
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:47:49.232+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Booking not found with id 224",
    "path": "/api/booking/secured/224/account"
}

OR

Code : 403 Forbidden
______________________________________________
## Car
### URL : api/car/all

Method : GET

Return all Cars

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 216,
                  "make": "make1",
                  "model": "model1",
                  "imageUrl": "httpp/ff4444/ffffff2",
                  "pricePerDay": 32
              },
              {
                  "id": 218,
                  "make": "make22",
                  "model": "model22",
                  "imageUrl": "httpp/ff4444/ffffff2",
                  "pricePerDay": 2
              }
          ]
______________________________________________
### URL : api/car/:pk

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Car in the database.

Return the Car with specified ID

#### Success Responses

Code : 200 OK

Content : {
              "id": 227,
              "make": "make11",
              "model": "model11",
              "imageUrl": "httpp/ff4444/ffffff2",
              "pricePerDay": 30
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:43:18.833+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 217",
    "path": "/api/car/217"
}
______________________________________________
### URL : api/car/secured/add

Method : POST

Create the Car

Auth required : YES

Permissions required : User is ADMIN

Data : { "make": string, "model": string, "imageUrl": string, "pricePerDay": float }

#### Success Responses

Code : 200 OK

Content : {
              "id": 227,
              "make": "make11",
              "model": "model11",
              "imageUrl": "httpp/ff4444/ffffff2",
              "pricePerDay": 30
          }

#### Error Responses

Code : 403 Forbidden
______________________________________________
### URL : api/car/secured/:pk

URL Parameters : pk=[integer] where pk is the ID of the Car in the database.

Method : DELETE

Delete the Car with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

#### Error Responses

Code : 403 Forbidden

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:45:01.355+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 2180",
    "path": "/api/car/secured/2180"
}
______________________________________________
### URL :api/car/secured/:pk

Method : PUT

URL Parameters : pk=[integer] where pk is the ID of the Car in the database.

Update the Car with specified ID

Auth required : YES

Permissions required : User is ADMIN

Data : { "make": string, "model": string, "imageUrl": string, "pricePerDay": float }

#### Success Responses

Code : 200 OK

Content : {
              "id": 227,
              "make": "make22",
              "model": "model22",
              "imageUrl": "httpp/ff4444/ffffff2",
              "pricePerDay": 2
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:47:10.924+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 219",
    "path": "/api/car/secured/219"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/car/secured/:pk/bookings

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Car in the database.

Return Bookings for the Car with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 224,
                  "pickUpDate": "2020-11-11",
                  "dropOffDate": "2020-11-11",
                  "price": 32,
                  "carId": 216,
                  "accountId": 213
              }
          ]

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:50:18.445+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 2160",
    "path": "/api/car/secured/2160/bookings"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/car/:pk/dates

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Car in the database.

Return Dates for the Car with specified ID

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 221,
                  "dateFrom": "2020-11-10",
                  "dateTo": "2020-11-11",
                  "carId": 216
              }
          ]

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T21:51:35.231+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 2160",
    "path": "/api/car/2160/dates"
}
______________________________________________
### URL : api/car/filter/make/:make

Method : GET

URL Parameters : make=[string] where make is the make of the Car.

Return Cars with specified make

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 216,
                  "make": "make1",
                  "model": "model1",
                  "imageUrl": "httpp/ff4444/ffffff2",
                  "pricePerDay": 32
              }
          ]
______________________________________________
### URL : api/car/filter/:make/:model

Method : GET

URL Parameters : make=[string] where make is the make of the Car,
                 model=[string] where model is the model of the Car.

Return Cars with specified make and model

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 216,
                  "make": "make1",
                  "model": "model1",
                  "imageUrl": "httpp/ff4444/ffffff2",
                  "pricePerDay": 32
              }
          ]
______________________________________________
### URL : api/car/filterPrice/:from/:to

Method : GET

URL Parameters : from=[float] where from is min price per day,
                 to=[float] where from is max price per day.

Return Cars with price in specified range

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 216,
                  "make": "make1",
                  "model": "model1",
                  "imageUrl": "httpp/ff4444/ffffff2",
                  "pricePerDay": 32
              },
              {
                  "id": 227,
                  "make": "make22",
                  "model": "model22",
                  "imageUrl": "httpp/ff4444/ffffff2",
                  "pricePerDay": 2
              }
          ]
______________________________________________
## Dates

### URL : api/dates/all

Method : GET

Return all Dates

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 221,
                  "dateFrom": "2020-11-10",
                  "dateTo": "2020-11-11",
                  "carId": 216
              }
          ]
______________________________________________
### URL : api/dates/:pk

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Dates in the database.

Return the Dates with specified ID

#### Success Responses

Code : 200 OK

Content : {
              "id": 221,
              "dateFrom": "2020-11-10",
              "dateTo": "2020-11-11",
              "carId": 216
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:06:03.724+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Dates not found with id 219",
    "path": "/api/dates/219"
}
______________________________________________
### URL : api/dates/secured/add

Method : POST

Create the Dates

Auth required : YES

Permissions required : User is ADMIN

Data : { "dateFrom": LocalDate, "dateTo": LocalDate, "carId": Integer }

#### Success Responses

Code : 200 OK

Content : {
              "id": 228,
              "dateFrom": "2020-11-10",
              "dateTo": "2020-11-14",
              "carId": 216
          }

#### Error Responses

Code : 403 Forbidden

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:10:27.884+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 2160",
    "path": "/api/dates/secured/add"
}

OR

Code : 400 Bad Request
{
    "timestamp": "2020-08-14T22:15:27.018+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "dateFrom must be before dateTo",
    "path": "/api/dates/secured/add"
}
______________________________________________
### URL : api/dates/secured/:pk

URL Parameters : pk=[integer] where pk is the ID of the Dates in the database.

Method : DELETE

Delete the Dates with specified ID

Auth required : YES

Permissions required : User is ADMIN

#### Success Responses

Code : 200 OK

#### Error Responses

Code : 403 Forbidden

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:08:02.113+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Dates not found with id 219",
    "path": "/api/dates/secured/219"
}
______________________________________________
### URL :api/dates/secured/:pk

Method : PUT

URL Parameters : pk=[integer] where pk is the ID of the Dates in the database.

Update the Dates with specified ID

Auth required : YES

Permissions required : User is ADMIN

Data : { "dateFrom": LocalDate, "dateTo": LocalDate, "carId": Integer }

#### Success Responses

Code : 200 OK

Content : {
              "id": 228,
              "dateFrom": "2020-11-12",
              "dateTo": "2020-11-14",
              "carId": 216
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:19:51.764+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Car not found with id 2160",
    "path": "/api/dates/secured/229"
}

OR

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:12:55.088+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Dates not found with id 220",
    "path": "/api/dates/secured/220"
}

OR

Code : 403 Forbidden
______________________________________________
### URL : api/dates/:pk/car

Method : GET

URL Parameters : pk=[integer] where pk is the ID of the Dates in the database.

Return the Car for the Dates with specified ID

#### Success Responses

Code : 200 OK

Content : {
              "id": 216,
              "make": "make1",
              "model": "model1",
              "imageUrl": "httpp/ff4444/ffffff2",
              "pricePerDay": 32
          }

#### Error Responses

Code : 404 Not Found

Content : 
{
    "timestamp": "2020-08-14T22:22:03.060+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Dates not found with id 220",
    "path": "/api/dates/220/car"
}
______________________________________________
### URL : api/dates/filter/:from/:to

Method : GET

URL Parameters : from=[LocalDate], to=[LocalDate].

Return Cars with the Dates in specified range

#### Success Responses

Code : 200 OK

Content : [
              {
                  "id": 216,
                  "make": "make1",
                  "model": "model1",
                  "imageUrl": "httpp/ff4444/ffffff2",
                  "pricePerDay": 32
              }
          ]
______________________________________________