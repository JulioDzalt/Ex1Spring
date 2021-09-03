
# Get all ATMs
curl -X GET \
  http://localhost:8080/location/atm/all \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  

# Get ATMs near
curl -X POST \
  http://localhost:8080/location/ \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
        "gps": "1,1",
        "codigoPostal": "02000",
        "delegacion": "Azcapotzalco",
        "estado": "Ciudad de Mexico"
    }'


# Get ATMs near
curl -X POST \
  http://localhost:8080/location/atm/near \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
        "gps": "",
        "codigoPostal": "",
        "delegacion": "",
        "estado": "Jalisco"
    }'
