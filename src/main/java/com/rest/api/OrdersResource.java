package com.rest.api;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class OrdersResource {
	//Set a maximum radius from the delivery personnel
	static final float MAX_DISTANCE = 5.0f;
	
    public static List<Orders> ordersRepository = new ArrayList<Orders>();

    //Calculate the great circle distance to get an accurate approximation
    //between source and destination using the haversine formula
    public static Float haversine(Float srclat,Float srclon,Float deslat, Float deslon){
	float x1 = (float) Math.toRadians(srclat);
	float y1 = (float) Math.toRadians(srclon);
	float x2 = (float) Math.toRadians(deslat);
	float y2 = (float) Math.toRadians(deslon);

	  double a = Math.pow(Math.sin((x2-x1)/2), 2)
              + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((y2-y1)/2), 2);

    float distance = (float) (2 *6378.137 * Math.asin(Math.min(1, Math.sqrt(a))));
	return distance;
}
 
 //This request will return a list of all the orders in the system
@GET
@Path("getorders")
@Produces({ MediaType.APPLICATION_JSON })
public Response getOrders()  {
    GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(
            ordersRepository) {};
    return Response.ok(entity).build();
}

//This request will return all the orders available into system
//since no db is available
@POST
@Path("postorders")
@Consumes({ MediaType.APPLICATION_JSON })
public Response addOrders(List<Orders> orders) {
	for(Orders o: orders) {
		ordersRepository.add(o);
	}
	   return Response.ok().build();
}

//This request will return all the possible combinations
//available to the delivery personnel
@POST
@Path("findorders")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public String findOrders(Location personnel) {
		List<Orders> list=new ArrayList<Orders>();
		List<Orders> outputList=new ArrayList<Orders>();
		List<ArrayList<Orders>> finallist=new ArrayList<ArrayList<Orders>>();
		
		int len=ordersRepository.size();
		
		//First loop through all the available orders and calculate
		//the distance between them and the delivery personnel 
		
		List<Integer> closest=new ArrayList<>();
		while(len>0) {
		float dis=haversine(personnel.getLatitude(),
				personnel.getLongitude(),
				ordersRepository.get(len-1).getSource_latitude(),
				ordersRepository.get(len-1).getSource_longitude());
	
		//If they are closer than MAX_DISTANCE store the orders in
		//a separate list
		if(dis<MAX_DISTANCE) {
			list.add(ordersRepository.get(len-1));
			closest.add(len-1);
		}
		len--;
		}
		
		//For each of the members of this new list check if the destination
		//is less than MAX_DISTANCE specified in order to combine them
		if(!list.isEmpty()) {
			while(!closest.isEmpty()) {
			outputList=new ArrayList<Orders>();
			len=list.size();
			int index=closest.remove(0);
			while(len>0) {
				float dis=haversine(ordersRepository.get(index).getDestination_latitude(),
						ordersRepository.get(index).getDestination_longitude(),
						list.get(len-1).getDestination_latitude(),
						list.get(len-1).getDestination_longitude());
				if(dis<MAX_DISTANCE) {
					outputList.add(list.get(len-1));
				}
				len--;
			}
			//Store solution in an array of arrays for all possible cominations
			finallist.add((ArrayList<Orders>) outputList);
			}
		}
		
		//Convert the solution into json and send it back as a json string response
		StringBuilder strbl=new StringBuilder();
		
		 Gson gson = new Gson();
		 for(int i=0;i<finallist.size();i++) {
		 String outputJson = gson.toJson(finallist.get(i));
		 strbl.append(outputJson);
		 }
	    
	    return strbl.toString();
}

}