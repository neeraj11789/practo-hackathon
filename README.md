# Insta Reporting 

## Pre Requisites

### Installing Elastic

### Installing Kibana

### Preparing Data

1. Creating the index for the required entity. Eg
``PUT /test-00001``
```
   {
   "mappings": {
   "properties": {
   "mr_no": {
   "type":   "keyword"
   },
   "visit_id": {
   "type":   "keyword"
   },
   "visit_type": {
   "type":   "keyword"
   },
   "status": {
   "type":   "keyword"
   },
   "doctor":{
   "properties": {
   "id": {
   "type": "keyword"
   },
   "name":{
   "type": "keyword"
   },
   "specialization":{
   "type": "keyword"
   }
   }
   },
   "department":{
   "properties": {
   "id": {
   "type": "keyword"
   },
   "name":{
   "type": "keyword"
   }
   }
   },
   "center":{
   "properties": {
   "id": {
   "type": "keyword"
   },
   "name":{
   "type": "keyword"
   }
   }
   },
   "patient":{
   "properties": {
   "id": {
   "type": "keyword"
   },
   "name":{
   "type": "keyword"
   },
   "age":{
   "type": "long"
   }
   }
   },
   "registration_datetime": {
   "type":   "date",
   "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
   }
   }
   }
   }
``
2. Make the curl Call for the API to load the date for past one month
