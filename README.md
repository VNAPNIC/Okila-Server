# Okila-Server
The Okila server project uses the Spring boot framework and uses the Kotlin language

Format Response

```json
 //The response successfully format
 {
  "timestamp": "2021-06-28T06:10:11.786+00:00",
  "code": 200,
  "message": "",
  "error": "",
  "token": "",
  "refreshToken": "",
  "data": Any
 }

 //The response failed format
 {
  "timestamp": "2021-06-28T06:10:11.786+00:00",
  "code": 200,
  "message": "",
  "error": "",
  "errorBody": Any
 }
```