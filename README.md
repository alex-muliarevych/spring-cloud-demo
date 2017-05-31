# spring-cloud-demo

Configurations are accessable via config server at http://localhost:8888/config-server/master.

List of servers registered by EUREKA service (started at http://localhost:8761/):

1) HUCKSTER-SERVICE -> lvl1701006.lohika.com:huckster-service:8087, contains H2 DB with huckster info, can be disabled or enabled via 'enabled-ban-list' parameter in configurations.

2) RATE-SERVICE	-> lvl1701006.lohika.com:rate-service:8082, simple buisness logic to check if input by realtor appratment information is valid in accordance to minimum ratio, defined by 'rate' parameter in configurations.

3) REALTOR-SERVICE	-> lvl1701006.lohika.com:realtor-service:8080, enable register of new realtor and adding new offer.

4) STORAGE-SERVICE	-> lvl1701006.lohika.com:storage-service:8089,  DB service, where manipulations with OwnerInfo, HomeOffer and AppartmentInfo can be done.