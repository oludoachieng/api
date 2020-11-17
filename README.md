# api
Consider multiple orders in close proximity, design and implement an optimization algorithm for
batching the orders.

This is an implementation of the api that gets closest orders and batches them together returns all possible batches as response.
Uses the Haversine's formula to find the closest orders.

1. Http POST Method url: ~/api/postorders
Save your test data to the system by adding input coordinates(orders source and destination coordinates) that shall be
fed to the API via the above method.
Example:
[ 
{
"source_latitude":-1.30015,
"source_longitude": 36.7747,
"destination_latitude": 1.0063,
"destination_longitude": 37.2074
},
{
"source_latitude":-1.300176,
"source_longitude": 36.776714,
"destination_latitude": 1.0065,
"destination_longitude": 37.2074
},
{
"source_latitude":-1.30022,
"source_longitude": 36.773,
"destination_latitude": 2.0074,
"destination_longitude": 35.7586
}
]

2. Http GET Method url: ~/api/getorders
To see all orders that have been saved.

3. Http POST Method url: ~/api/findorders
Input is the current location of the delivery personnel and gives as output a list of all
combinations of orders in close proximity.
Example:
{
"latitude": -1.300176,
"longitude": 36.776714
}
