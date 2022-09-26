# Insta Reporting 

## Pre Requisites

### Installing Elastic
1. `wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -`
2. `echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-7.x.list`
3. `sudo apt update`
4. `sudo apt install elasticsearch`
5. `sudo nano /etc/elasticsearch/elasticsearch.yml`. Change the network.host to following
6. `. . .
   network.host: localhost
   . . .`
7. `sudo systemctl start elasticsearch`
8. `sudo systemctl enable elasticsearch`
9. `curl -X GET "localhost:9200"`
10. The above command should give you the information for the elastic search - 
11. `Output
    {
    "name" : "ElasticSearch",
    "cluster_name" : "elasticsearch",
    "cluster_uuid" : "SMYhVWRiTwS1dF0pQ-h7SQ",
    "version" : {
    "number" : "7.6.1",
    "build_flavor" : "default",
    "build_type" : "deb",
    "build_hash" : "aa751e09be0a5072e8570670309b1f12348f023b",
    "build_date" : "2020-02-29T00:15:25.529771Z",
    "build_snapshot" : false,
    "lucene_version" : "8.4.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
    },
    "tagline" : "You Know, for Search"
    }`

### Installing Kibana
1. `sudo apt install kibana`
2. `sudo systemctl enable kibana
   sudo systemctl start kibana`
3. Enter `http://localhost:5601/` - should be able to access Kibana Dashboard
4. Instructions here - https://www.digitalocean.com/community/tutorials/how-to-install-elasticsearch-logstash-and-kibana-elastic-stack-on-ubuntu-18-04

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
