package rest.ressources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rest.entities.Employe;

@Path("employes")
@Api
public class GestionEmploye {
	
	public static  List<Employe> employes=new ArrayList<Employe>();

	
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public Response ajouterEmploye(Employe employe) {
		 if(employes.add(employe))
	 		return Response.status(Status.CREATED).entity("Add Successful").build();
		 return Response.status(Status.NOT_FOUND).entity("Echec").build();
	  
		
	}
	@GET
	@Produces("text/plain")
	@ApiOperation(value = "Get all employees")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
	})
	public  Response  displayEmployeesList() {
		
		if(employes.size()!=0)
			return Response.status(Status.FOUND).entity(employes).build();
		else
			return Response.status(Status.NOT_FOUND).entity("").build();
					
	}
	
	@GET
	public Response getEmploye(int cin) {
		for (Employe info:employes) {
	       if(info.getCin()==cin) {
	    	   return  Response.status(Status.FOUND)
						.entity(info)
						.build(); 
	    	
	       }
		}
	       		
			return  Response.status(Status.NOT_FOUND).build();
		
		
	}
	
		
	@PUT
	@Consumes("application/xml")
	@Produces("text/plain")
	public Response updateEmploye(Employe e) {
		int index= this.getIndexByCin(e.getCin());
		if (index!=-1) {
			employes.set(index, e);
			return Response.status(Status.OK).entity("update successful").build();
			
		}
		return Response.status(Status.NOT_FOUND).entity("update unsuccessful").build();
	
	}
	
	@DELETE
	@Path("{id}")
	public boolean deleteEmpl(@PathParam(value = "id") int cin){
		int index= getIndexByCin(cin);
		
		if (index!=-1) {
			employes.remove(index);
			return true;
		}else 
			return false;
			
    }
	
	public int getIndexByCin(int cin) {
		for(Employe emp: employes) {
			if (emp.getCin()==cin)
				return employes.indexOf(emp);
		}
		return -1;
	}
	
		
}
